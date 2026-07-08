package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.function.Predicate;

public class BaseSchema<T> {
    private final Map<String, Predicate<T>> rules;

    public BaseSchema() {
        this.rules = new HashMap<>();
    }

    public final boolean isValid(T value) {
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
}
