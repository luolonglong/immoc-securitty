package com.imooc.security.app;

import com.imooc.security.core.ImoocSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 实现了BeanPostProcessor接口就可以
 * 在Spring所有的Bean初始化之前和之后都要经过接口中的两个方法
 * @author Gatsby.Luo
 * @date 2018-11-09
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /** 改掉  signUpUrl */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (StringUtils.equals(beanName, "imoocSocialSecurityConfigurer")) {
            ImoocSpringSocialConfigurer imoocSpringSocialConfigurer = (ImoocSpringSocialConfigurer) bean;
            imoocSpringSocialConfigurer.signupUrl("/social/signUp");
            return imoocSpringSocialConfigurer;
        }

        return bean;
    }
}
