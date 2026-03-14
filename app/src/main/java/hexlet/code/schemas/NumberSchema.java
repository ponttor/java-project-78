package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        addRule("required", value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addRule("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int left, int right) {
        addRule("range", value -> value == null || (value >= left && value <= right));
        return this;
    }
}
