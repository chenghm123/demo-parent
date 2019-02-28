package com.accelerator.demo.standalone.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;

public class Jackson2JsonTest {

    private static final String userString = "{\"id\":null,\"username\":\"admin\",\"password\":\"admin\"}";
    private static final String userStringWithDefaultType = "[\"com.accelerator.demo.standalone.redis.test.Jackson2JsonTest$UserInfo\",{\"id\":null,\"username\":\"admin\",\"password\":\"admin\"}]";

    /** 直接使用 ObjectMapping **/
    @Test
    public void serialize() throws JsonProcessingException {
        CustomSerializer serializer = new CustomSerializer(Object.class);
        String s = serializer.serialize(createUser());
        Assert.assertEquals(s, userString);
    }

    /** 设置了 DefaultTyping 的 ObjectMapper **/
    @Test
    public void serializeWithDefaultType() throws JsonProcessingException {
        CustomSerializer serializer = new CustomSerializer(Object.class);
        serializer.setObjectMapper(createObjectMapping());
        String s = serializer.serialize(createUser());
        Assert.assertEquals(s, userStringWithDefaultType);
    }

    /** 直接使用 ObjectMapping 的反序列化，需要给定序列化的类 **/
    @Test
    public void deserialize() throws IOException {
        CustomSerializer<UserInfo> serializer = new CustomSerializer(UserInfo.class);
        UserInfo userInfo = serializer.deserialize(userString);
        System.out.println(userInfo);
        Assert.assertEquals(userInfo, createUser());
    }

    /** 使用设置了 DefaultTyping 的 ObjectMapping 来反序列化，可以使用 Object 达到想要的效果 **/
    @Test
    public void deserializeWithDefaultTyping() throws IOException {
        CustomSerializer serializer = new CustomSerializer(Object.class);
        serializer.setObjectMapper(createObjectMapping());
        UserInfo userInfo = (UserInfo) serializer.deserialize(userStringWithDefaultType);
        System.out.println(userInfo);
        Assert.assertEquals(userInfo, createUser());
    }

    private ObjectMapper createObjectMapping() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return objectMapper;
    }

    private UserInfo createUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("admin");
        userInfo.setPassword("admin");
        return userInfo;
    }

    private static class CustomSerializer<T> {
        private final JavaType javaType;
        private ObjectMapper objectMapper = new ObjectMapper();

        public CustomSerializer(JavaType javaType) {
            this.javaType = javaType;
        }

        public CustomSerializer(Class clazz) {
            this.javaType = TypeFactory.defaultInstance().constructType(clazz);
        }

        public void setObjectMapper(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        public String serialize(Object o) throws JsonProcessingException {
            return this.objectMapper.writeValueAsString(o);
        }

        public T deserialize(String s) throws IOException {
            return this.objectMapper.readValue(s, this.javaType);
        }
    }

    private static class UserInfo implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id;
        private String username;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserInfo userInfo = (UserInfo) o;

            if (id != null ? !id.equals(userInfo.id) : userInfo.id != null) return false;
            if (username != null ? !username.equals(userInfo.username) : userInfo.username != null) return false;
            if (password != null ? !password.equals(userInfo.password) : userInfo.password != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (username != null ? username.hashCode() : 0);
            result = 31 * result + (password != null ? password.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "id='" + id + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
