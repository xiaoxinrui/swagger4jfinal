package org.leon.swagger.exception;

/**
 * 自定义初始化异常
 * @since 1.0.0
 */
public class InitializationException extends RuntimeException {

    public InitializationException(Throwable cause) {
        super(cause);
    }

    public InitializationException(String message) {
        super(message);
    }

}
