package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        setRequired();
        addRule("required", value -> !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addRule("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addRule("contains", value -> value.contains(substring));
        return this;
    }
}
