package com.accelerator.demo.standalone.common.mybatis;

import java.util.Date;
import java.util.Objects;

import static org.apache.commons.lang3.time.DateFormatUtils.format;

class RdbmsSpecifics {

    String formatParameter(Object parameter) {
        if (Objects.isNull(parameter)) {
            return "null";
        } else {
            if (parameter instanceof Date) {
                String dateStr = format((Date) parameter, "yyyy-MM-dd HH:mm:ss");
                return "'" + dateStr + "'";
            } else if (parameter instanceof Boolean
                    || parameter instanceof Short
                    || parameter instanceof Integer
                    || parameter instanceof Long
                    || parameter instanceof Float
                    || parameter instanceof Double) {
                return String.valueOf(parameter);
            } else {
                return "'" + parameter + "'";
            }
        }
    }

}
