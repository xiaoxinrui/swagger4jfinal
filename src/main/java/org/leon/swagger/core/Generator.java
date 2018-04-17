package org.leon.swagger.core;

import com.jfinal.kit.PropKit;
import org.leon.swagger.utils.ClassHelper;

import java.util.Properties;

/**
 * Swagger 生成器<br>
 * 目前支持配置文件加载
 * @since 1.0.0
 */
public class Generator {

    private Properties properties;

    public Generator(Properties properties) {
        this.properties = properties;
    }

    public Generator(String properties) {
        this.properties = PropKit.use(properties).getProperties();
    }

    public boolean initSwagger() {
        try {
            ClassHelper.initProp(properties);
            Transformer.setSwagger(properties);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
