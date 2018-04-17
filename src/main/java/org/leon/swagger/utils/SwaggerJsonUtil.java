package org.leon.swagger.utils;

import com.alibaba.fastjson.JSON;
import org.leon.swagger.model.Swagger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * SwaggerJson操作类
 * @since 1.0.0
 */
public class SwaggerJsonUtil {

    private static Swagger swagger = Swagger.getInstance();

    /**
     * 获取json生成路径
     * @param request 请求
     * @return String
     */
    private static String getJsonRealPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/WEB-INF/view/swagger/");
    }

    /**
     * 获取Swagger Json
     * @return String
     */
    public static String getJson() {
        // swaggerUI 使用Java的关键字default作为默认值,此处将生成的JSON替换
        return JSON.toJSONString(swagger).replaceAll("\"defaultValue\"", "\"default\"");
    }

    /**
     * 写出Swagger Json文件
     * @param json Swagger格式String
     */
    public static void writeJson(HttpServletRequest request, String json) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(getJsonRealPath(request) + "/swagger.json"),
                                    Charset.forName("utf-8")
                            )
                    );
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取Swagger Json文件
     */
    public static File getJsonFile(HttpServletRequest request) {
        File jsonFile = new File(getJsonRealPath(request) + "/swagger.json");
        if (jsonFile.exists()) {
            return jsonFile;
        }
        return null;
    }


}
