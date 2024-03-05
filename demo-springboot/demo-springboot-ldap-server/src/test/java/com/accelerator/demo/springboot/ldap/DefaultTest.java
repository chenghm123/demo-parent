package com.accelerator.demo.springboot.ldap;

import org.junit.jupiter.api.Test;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;
import java.util.Map;

public class DefaultTest {

    public static LdapTemplate getLdapTemplate() {
        LdapTemplate template = null;
        try {
            LdapContextSource contextSource = new LdapContextSource();

            String url = "ldap://127.0.0.1:10389";
            String base = "dc=jboss,dc=org";
            String userDn = "uid=admin,ou=system";
            String password = "secret";

            contextSource.setUrl(url);
            contextSource.setBase(base);
            contextSource.setUserDn(userDn);
            contextSource.setPassword(password);
            contextSource.setPooled(false);
            contextSource.afterPropertiesSet(); // important
            template = new LdapTemplate(contextSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return template;
    }

    @Test
    public void test() {
        LdapTemplate template = getLdapTemplate();

        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectClass", "organizationalPerson"));
        filter.and(new EqualsFilter("sn", "duke"));
        String fileCatalog = "ou=Users";
        //                        Dn rdns = new Dn(new Rdn(fileCatalog));


        //查询所有内部人员
        List<Map> users = template.search(fileCatalog, filter.encode(), new AttributesMapper<Map>() {
            @Override
            public Map mapFromAttributes(Attributes attributes) throws NamingException {
                return null;
            }
        });
        for (Map user : users) {
            System.out.println(user);
        }
    }

}
