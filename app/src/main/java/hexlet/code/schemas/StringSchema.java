package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addRule("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addNullableRule("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addNullableRule("contains", value -> value.contains(substring));
        return this;
    }
}
