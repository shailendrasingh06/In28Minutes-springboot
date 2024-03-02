package com.in28minutes.rest.webserices.restfulwebservice.controller;

import com.in28minutes.rest.webserices.restfulwebservice.entity.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping( "/hello-world-bean")
    HelloWorldBean sayHelloBean() {
        return new HelloWorldBean("Shailendra");
    }

    @GetMapping( "/hello-world")
    String sayHello() {
        return "Hello Wrold";
    }

    @GetMapping( "/hello-world/path-variable/{name}")
    HelloWorldBean sayHelloPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s ", name));
    }

    @GetMapping( "/hello-world-i18n")
    String sayHelloInternationalize() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.msg", null, "Default Message", locale);
//        return "Hello Wrold";
    }
}
