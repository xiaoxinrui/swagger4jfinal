package org.leon.swagger.controller;

import com.jfinal.core.Controller;
import org.leon.swagger.utils.SwaggerJsonUtil;

/**
 * Swagger Controller
 * @since 1.0.0
 */
public class SwaggerController extends Controller {

    /**
     * Swagger UI访问路径
     */
    public void index() {
        render("/WEB-INF/view/swagger/index.html");
    }

    /**
     * swagger json访问路径
     */
    public void api() {
        renderText(SwaggerJsonUtil.getJson());
    }

    /**
     * swagger json下载
     */
    public void downloadJson() {
        SwaggerJsonUtil.writeJson(getRequest(), SwaggerJsonUtil.getJson());
        renderFile(SwaggerJsonUtil.getJsonFile(getRequest()));
    }

}
