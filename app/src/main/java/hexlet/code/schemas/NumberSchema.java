package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        setRequired();
        return this;
    }

    public NumberSchema positive() {
        addRule("positive", value -> value > 0);
        return this;
    }

    public NumberSchema range(int left, int right) {
        addRule("range", value -> value >= left && value <= right);
        return this;
    }
}
