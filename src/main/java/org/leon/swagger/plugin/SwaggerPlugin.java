package org.leon.swagger.plugin;

import com.jfinal.plugin.IPlugin;
import org.leon.swagger.core.Generator;

import java.util.Properties;

/**
 * SwaggerPlugin 注册为Jfinal插件<br>
 * 目前支持配置文件加载
 * @since 1.0.0
 */
public class SwaggerPlugin implements IPlugin {

    private Generator generator;

    public SwaggerPlugin() {
        generator = new Generator(new Properties());
    }

    public SwaggerPlugin(Properties p) {
        generator = new Generator(p);
    }

    public SwaggerPlugin(String properties) {
        generator = new Generator(properties);
    }

    @Override
    public boolean start() {
        return generator.initSwagger();
    }

    @Override
    public boolean stop() {
        return true;
    }
}
