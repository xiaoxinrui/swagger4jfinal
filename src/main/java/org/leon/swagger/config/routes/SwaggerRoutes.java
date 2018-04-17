package org.leon.swagger.config.routes;

import com.jfinal.config.Routes;
import org.leon.swagger.controller.SwaggerController;

/**
 * SwaggerRoutes
 * @since 1.0.0
 */
public class SwaggerRoutes extends Routes {

    @Override
    public void config() {
        add("/swagger", SwaggerController.class);
    }

}
