package org.leon.swagger.model.component;

/**
 * Swagger info
 * @since 1.0.0
 */
public class Info {

    private String description;
    private String version;
    private String title;
    private String termsOfService;

    public Info(String description, String version, String title) {
        this.description = description;
        this.version = version;
        this.title = title;
    }

    public Info(String description, String version, String title, String termsOfService) {
        this.description = description;
        this.version = version;
        this.title = title;
        this.termsOfService = termsOfService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTermsOfService() {
        return termsOfService;
    }

    public void setTermsOfService(String termsOfService) {
        this.termsOfService = termsOfService;
    }

}
