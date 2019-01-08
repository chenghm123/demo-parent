package com.accelerator.demo.standalone.validator;

import com.accelerator.demo.standalone.validator.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ValidatorTest {

    @Resource
    private Validator validator;

    @Test
    public void test() {
        Set<ConstraintViolation<User>> validates = validator.validate(new User(), Group.class);
        for (ConstraintViolation<User> validate : validates) {
            System.out.println(validate);
        }
    }

}
