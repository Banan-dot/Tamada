package com.urfu.Tamada.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CommandInformation {
    String name();

    String information();

    String detailedInformation() default "нет подробной информации по использванию.";
}
