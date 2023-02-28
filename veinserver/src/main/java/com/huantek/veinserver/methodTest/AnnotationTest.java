package com.huantek.veinserver.methodTest;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface AnnotationTest {
    String name();
    enumTest method();
}

enum enumTest{
    hello,world,welcome;
}
