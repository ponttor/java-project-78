package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        addRule("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addNullableRule("positive", value -> value > 0);
        return this;
    }

    public NumberSchema range(int left, int right) {
        addNullableRule("range", value -> value >= left && value <= right);
        return this;
    }
}
