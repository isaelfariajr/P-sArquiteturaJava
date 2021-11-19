package br.com.uniciv.minhastarefas.config;

import org.apache.tomcat.jni.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

//Classe criada para carreagar as mensagens do arquivo messages.properties
@Configuration
public class IntenocionalizacaoConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource msgSource =
                new ReloadableResourceBundleMessageSource();

        msgSource.setBasename("classpath:messages");
        msgSource.setDefaultEncoding("UTF-8");
        msgSource.setDefaultLocale(Locale.getDefault());
        return msgSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean() {

        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }
}
