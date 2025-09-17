package com.example.demo.bpp;

import com.example.demo.annotations.RandomEmployeeDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.util.Random;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class RandomEmployeeDtoAnnotation implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        for (Field field : bean.getClass().getDeclaredFields()){
            if (field.isAnnotationPresent(RandomEmployeeDto.class)
                    && field.getType().getName().equals(EmployeeDto.class.getName())){
                field.setAccessible(true);
                try {
                    field.set(bean, Random.getRandomEmployeeDto());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bean;
    }
}
