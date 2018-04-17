package org.leon.swagger.model;

import org.leon.swagger.model.component.Info;
import org.leon.swagger.model.component.Path;
import org.leon.swagger.model.component.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Swagger
 * @since 1.0.0
 */
public class Swagger {

    private String swagger;
    private Info info;
    private String host;
    private String basePath;
    private List<Tag> tags = new ArrayList<>();
    private List<String> schemes = new ArrayList<>();
    private Map<String, Map<String, Path.ApiMethod>> paths = new HashMap<>();

    private Swagger() {}

    private static class HolderClass {
        private final static Swagger instance = new Swagger();
    }

    /**
     * IoDH模式获取Swagger单例
     */
    public static Swagger getInstance() {
        return HolderClass.instance;
    }

    public String getSwagger() {
        return swagger;
    }

    public void setSwagger(String swagger) {
        this.swagger = swagger;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<String> schemes) {
        this.schemes = schemes;
    }

    public Map<String, Map<String, Path.ApiMethod>> getPaths() {
        return paths;
    }

    public void setPaths(Map<String, Map<String, Path.ApiMethod>> paths) {
        this.paths = paths;
    }

    public Swagger addTags(Tag Tag) {
        tags.add(Tag);
        return this;
    }

    public Swagger addScheme(String scheme) {
        schemes.add(scheme);
        return this;
    }
}
