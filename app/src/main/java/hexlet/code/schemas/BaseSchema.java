package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> rules;

    public BaseSchema() {
        this.rules = new HashMap<>();
    }

    public boolean isValid(T value) {
        for (Predicate<T> rule : this.rules.values()) {
            if (!rule.test(value)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    public boolean isValidValue(Object value) {
        return isValid((T) value);
    }

    protected void addRule(String ruleName, Predicate<T> rule) {
        rules.put(ruleName, rule);
    }
}
