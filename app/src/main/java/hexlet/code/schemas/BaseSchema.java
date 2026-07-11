package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> rules;
    private boolean required;

    public BaseSchema() {
        this.rules = new HashMap<>();
        this.required = false;
    }

    public final boolean isValid(T value) {
        if (value == null) {
            return !required;
        }

        for (Predicate<T> rule : this.rules.values()) {
            if (!rule.test(value)) {
                return false;
            }
        }
        return true;
    }

    protected final void addRule(String ruleName, Predicate<T> rule) {
        rules.put(ruleName, rule);
    }

    protected final void setRequired() {
        required = true;
    }
}
