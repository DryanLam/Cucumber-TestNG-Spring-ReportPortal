package com.sample.dl.bdd.cucumber.UI.configuration;

import com.sample.dl.contexts.annotations.IgnoreScan;
import com.sample.dl.contexts.annotations.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@IgnoreScan
@Scope("cucumber-glue")
public class PageObjectBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private WebDriver driver;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(PageObject.class)) {
            PageFactory.initElements(driver, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
