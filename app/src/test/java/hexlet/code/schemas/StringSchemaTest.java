import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;


class StringSchemaTest {
    @Test
    void stringSchemaIsValid() {
        StringSchema stringSchema = new StringSchema();

        assert stringSchema.isValid(null);
        assert stringSchema.isValid("");
        assert stringSchema.isValid("uuu");
    }

    @Test
    void requiredStringSchemaValidation() {
        StringSchema stringSchema = new StringSchema();

        assertFalse(stringSchema.required().isValid(""));
        assertFalse(stringSchema.required().isValid(null));
        assert stringSchema.required().isValid("uu");
    }

    @Test
    void minLengthStringSchemaValidation() {
        StringSchema stringSchema = new StringSchema();

        assertFalse(stringSchema.minLength(2).isValid("u"));
        assertFalse(stringSchema.minLength(2).minLength(12).isValid("uuu"));
        assert stringSchema.minLength(12).minLength(2).isValid("uu");
        assert stringSchema.minLength(2).isValid("uu");
        assert stringSchema.minLength(2).isValid(null);
    }

    @Test
    void containsStringSchemaValidation() {
        StringSchema stringSchema = new StringSchema();

        assertFalse(stringSchema.contains("hex").isValid("uuu"));
        assert stringSchema.contains("hex").isValid("hexlet");
        assert stringSchema.contains("hex").isValid(null);
        assert stringSchema.contains("mex").contains("hex").isValid("hexlet");
        assertFalse(stringSchema.contains("u").contains("hex").isValid("uuu"));
    }

    @Test
    void allStringSchemaValidatesString() {
        StringSchema stringSchema = new StringSchema();

        assert stringSchema.required().minLength(2).contains("hex").isValid("hexlet");
        assertFalse(stringSchema.required().minLength(7).contains("hex").isValid("hexlet"));
        assertFalse(stringSchema.required().minLength(2).contains("mex").isValid("hexlet"));
    }

    @Test
    void lastMinLengthValidationWins() {
        StringSchema stringSchema = new StringSchema();

        assert stringSchema.minLength(10).minLength(4).isValid("Hexlet");
        assertFalse(stringSchema.minLength(4).minLength(10).isValid("Hexlet"));
    }

    @Test
    void lastContainsValidationWins() {
        StringSchema stringSchema = new StringSchema();

        assert stringSchema.contains("nope").contains("hex").isValid("hexlet");
        assertFalse(stringSchema.contains("hex").contains("nope").isValid("hexlet"));
    }
}
