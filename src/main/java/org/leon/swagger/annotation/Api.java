package org.leon.swagger.annotation;

import java.lang.annotation.*;

/**
 * 类注解 api
 * @since 1.0.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Api {
    String tag() default "";
    String description() default "";
}
