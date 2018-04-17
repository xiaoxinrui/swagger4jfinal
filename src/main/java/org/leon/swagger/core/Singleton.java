package org.leon.swagger.core;

import com.google.common.collect.Maps;
import org.leon.swagger.model.component.Path;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例对象存放类
 * @since 1.0.0
 */
public class Singleton {

    private Singleton() {}

    private static class HolderClass {
        private final static Map<String, Map<String, Path.ApiMethod>> paths = new HashMap<>();
        private final static Map<String, String> classMap = Maps.newHashMap();
    }

    static Map<String, Map<String, Path.ApiMethod>> getPaths() {
        return HolderClass.paths;
    }

    static Map<String, String> getClassMap() {
        return HolderClass.classMap;
    }

}
