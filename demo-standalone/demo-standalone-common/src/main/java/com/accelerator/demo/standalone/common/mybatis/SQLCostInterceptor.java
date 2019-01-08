package com.accelerator.demo.standalone.common.mybatis;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.StringTokenizer;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "queryCursor", args = {MappedStatement.class, Object.class, RowBounds.class}),
})
public class SQLCostInterceptor implements org.apache.ibatis.plugin.Interceptor {

    public static final RdbmsSpecifics DEFAULT_RDBMS_SPECIFICS = new RdbmsSpecifics();

    private RdbmsSpecifics rdbmsSpecifics = DEFAULT_RDBMS_SPECIFICS;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = invocation.getArgs()[1];
        try {
            return invocation.proceed();
        } finally {
            printFullSql(mappedStatement, parameterObject);
        }
    }

    private void printFullSql(MappedStatement mappedStatement, Object parameterObject) {
        Configuration configuration = mappedStatement.getConfiguration();

        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        if (Objects.isNull(parameterMappings)) {
            return;
        }

        List<Object> parameters = new ArrayList<>();
        for (ParameterMapping parameterMapping : parameterMappings) {
            if (parameterMapping.getMode() != ParameterMode.OUT) {
                Object value;
                String propertyName = parameterMapping.getProperty();
                if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
                    value = boundSql.getAdditionalParameter(propertyName);
                } else if (parameterObject == null) {
                    value = null;
                } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                parameters.add(value);
            }
        }

        String fullSql = dumpFullSql(cleanSQL(boundSql.getSql()), parameters);
        mappedStatement.getStatementLog()
                .debug("==>  FullSql: " + fullSql);
    }

    private String dumpFullSql(String sql, List<Object> parameters) {
        StringBuilder fullSql = new StringBuilder();
        int lastPos = 0;
        int Qpos = sql.indexOf('?', lastPos);  // find position of first question mark
        int argIdx = 0;
        String arg;
        while (Qpos != -1) {
            try {
                Object parameter = parameters.get(argIdx);
                arg = rdbmsSpecifics.formatParameter(parameter);
            } catch (Exception e) {
                arg = "?";
            }
            if (arg == null) {
                arg = "?";
            }
            argIdx++;
            fullSql.append(sql, lastPos, Qpos);  // dump segment of sql up to question mark.
            lastPos = Qpos + 1;
            Qpos = sql.indexOf('?', lastPos);
            fullSql.append(arg);
        }
        if (lastPos < sql.length()) {
            fullSql.append(sql.substring(lastPos));  // dump last segment
        }
        return fullSql.toString();
    }

    private String cleanSQL(String original) {
        StringTokenizer whitespaceStripper = new StringTokenizer(original);
        StringBuilder builder = new StringBuilder();
        while (whitespaceStripper.hasMoreTokens()) {
            builder.append(whitespaceStripper.nextToken());
            builder.append(" ");
        }
        return builder.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
