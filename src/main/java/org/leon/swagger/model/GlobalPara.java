package org.leon.swagger.model;

import org.leon.swagger.model.component.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局参数
 * @since 1.0.0
 */
public class GlobalPara {
    private static List<Path.Parameter> parameterList = new ArrayList<>();

    public static List<Path.Parameter> getParameterList() {
        return parameterList;
    }

    public static void addPara(Path.Parameter parameter) {
        parameterList.add(parameter);
    }
}
