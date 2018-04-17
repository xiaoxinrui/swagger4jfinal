package org.leon.swagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 单参数注解 api
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {
    String name() default "";
    String description() default "";
    boolean required() default false;
    String dataType() default "";
    String format() default "";
    String defaultValue() default "";
}
