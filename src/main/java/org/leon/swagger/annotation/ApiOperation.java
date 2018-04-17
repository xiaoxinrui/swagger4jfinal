package org.leon.swagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法注解 api operation
 * @since 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiOperation {
    String url() default "";
    String tag() default "";
    Class<?> response() default Void.class;
    String httpMethod() default "";
    String description() default "";
    String consumes() default "";
}
