package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addRule("required", value -> value != null);
        return this;
    }


    public MapSchema sizeof(int size) {
        addNullableRule("sizeof", value -> value.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addNullableRule("shape", value -> {
            for (Map.Entry<String, BaseSchema<T>> entry : schemas.entrySet()) {
                @SuppressWarnings("unchecked")
                T fieldValue = (T) value.get(entry.getKey());
                if (!entry.getValue().isValid(fieldValue)) {
                    return false;
                }
            }

            return true;
        });
        return this;
    }
}
