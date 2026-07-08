package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addRule("required", value -> value != null);
        return this;
    }


    public MapSchema sizeof(int size) {
        addRule("sizeof", value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addRule("shape", value -> {
            if (value == null) {
                return true;
            }

            for (Map.Entry<String, ? extends BaseSchema<?>> entry : schemas.entrySet()) {
                var fieldValue = value.get(entry.getKey());
                @SuppressWarnings("unchecked")
                var schema = (BaseSchema<Object>) entry.getValue();
                if (!schema.isValid(fieldValue)) {
                    return false;
                }
            }

            return true;
        });
        return this;
    }
}
