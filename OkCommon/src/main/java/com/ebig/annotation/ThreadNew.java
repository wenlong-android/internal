package com.ebig.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ThreadNew {
    /*自动切换为新建普通线程 */
    String user() default "";
    String lib() default "";
    long delay() default 0;
}
