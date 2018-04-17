package org.leon.swagger.core;

import org.apache.commons.lang3.StringUtils;
import org.leon.swagger.model.Swagger;
import org.leon.swagger.model.component.Info;
import org.leon.swagger.model.constant.Scheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Swagger Transformer 改造器
 * @since 1.0.0
 */
public class Transformer {

    private static Swagger swagger = Swagger.getInstance();

    // ================ default baisc value =================
    private static String swaggerVersion = "2.0";
    private static String infoDescription = "This is default Transformer description.",
                          infoVersion = "1.0.0",
                          infoTitle = "Default Transformer";
    private static String host = "127.0.0.1:8080";
    private static String basePath = "/";
    private static List<String> schemes = new ArrayList<>();

    /**
     * 获取Swagger单例对象
     * @return Swagger
     */
    public static Swagger getSwagger() {
        return swagger;
    }

    /**
     * 设置Swagger信息
     * @param p 配置文件
     */
    public static void setSwagger(Properties p) {
        setSwaggerBasicInfo(p);
        setPathsAndTags();
    }

    /**
     * 获取Swagger基础信息
     * @param p 配置文件
     */
    private static void getSwaggerBasicInfo(Properties p) {
        for (String key : p.stringPropertyNames()) {
            if ("swagger".equalsIgnoreCase(key)) {
                swaggerVersion = p.getProperty(key);
            }
            if ("host".equalsIgnoreCase(key)) {
                host = p.getProperty(key);
            }
            if ("basePath".equalsIgnoreCase(key)) {
                basePath = p.getProperty(key);
            }
            if ("schemes".equalsIgnoreCase(key)) {
                String value = p.getProperty(key);
                if (StringUtils.isEmpty(value)) {
                    schemes.add(Scheme.HTTP);
                } else if (value.contains(",")) {
                    schemes = Arrays.asList(value.split(","));
                } else {
                    schemes.add(value);
                }
            }
            if ("info.description".equalsIgnoreCase(key)) {
                infoDescription = p.getProperty(key);
            }
            if ("info.version".equalsIgnoreCase(key)) {
                infoVersion = p.getProperty(key);
            }
            if ("info.title".equalsIgnoreCase(key)) {
                infoTitle = p.getProperty(key);
            }
        }
    }

    /**
     * 设置Swagger基础信息
     */
    private static void setSwaggerBasicInfo(Properties p) {
        getSwaggerBasicInfo(p);

        swagger.setSwagger(swaggerVersion);
        swagger.setInfo(new Info(infoDescription, infoVersion, infoTitle));
        swagger.setHost(host);
        swagger.setBasePath(basePath);
        swagger.setSchemes(schemes);
    }

    /**
     * 生成Swagger Paths和Tags
     */
    private static void setPathsAndTags() {
        Core.createPaths();
        Core.createTags();
    }
}
