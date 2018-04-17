package org.leon.swagger.model.component;

/**
 * Swagger response
 * @since 1.0.0
 */
public class Response {

    private String description;
    private Schema schema;

    public Response(String description) {
        this.description = description;
    }

    public Response(String description, Schema schema) {
        this.description = description;
        this.schema = schema;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public static class Schema {
        private String type;
        private Items items;

        public Schema(String type, Items items) {
            this.type = type;
            this.items = items;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }
    }

    public static class Items {
        private String $ref;

        public Items(String ref) {
            this.$ref = ref;
        }

        public String get$ref() {
            return $ref;
        }

        public void set$ref(String $ref) {
            this.$ref = $ref;
        }
    }
}
