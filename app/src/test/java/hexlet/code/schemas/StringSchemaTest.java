import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StringSchemaTest {
    @Test
    void stringSchemaIsValid() {
        StringSchema stringSchema = new StringSchema();

        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid("uuu"));
    }

    @Test
    void requiredStringSchemaValidation() {
        StringSchema stringSchema = new StringSchema();

        assertFalse(stringSchema.required().isValid(""));
        assertFalse(stringSchema.required().isValid(null));
        assertTrue(stringSchema.required().isValid("uu"));
    }

    @Test
    void minLengthStringSchemaValidation() {
        StringSchema stringSchema = new StringSchema();

        assertFalse(stringSchema.minLength(2).isValid("u"));
        assertFalse(stringSchema.minLength(2).minLength(12).isValid("uuu"));
        assertTrue(stringSchema.minLength(12).minLength(2).isValid("uu"));
        assertTrue(stringSchema.minLength(2).isValid("uu"));
        assertTrue(stringSchema.minLength(2).isValid(null));
    }

    @Test
    void containsStringSchemaValidation() {
        StringSchema stringSchema = new StringSchema();

        assertFalse(stringSchema.contains("hex").isValid("uuu"));
        assertTrue(stringSchema.contains("hex").isValid("hexlet"));
        assertTrue(stringSchema.contains("hex").isValid(null));
        assertTrue(stringSchema.contains("mex").contains("hex").isValid("hexlet"));
        assertFalse(stringSchema.contains("u").contains("hex").isValid("uuu"));
    }

    @Test
    void allStringSchemaValidatesString() {
        StringSchema stringSchema = new StringSchema();

        assertTrue(stringSchema.required().minLength(2).contains("hex").isValid("hexlet"));
        assertFalse(stringSchema.required().minLength(7).contains("hex").isValid("hexlet"));
        assertFalse(stringSchema.required().minLength(2).contains("mex").isValid("hexlet"));
    }

    @Test
    void lastMinLengthValidationWins() {
        StringSchema stringSchema = new StringSchema();

        assertTrue(stringSchema.minLength(10).minLength(4).isValid("Hexlet"));
        assertFalse(stringSchema.minLength(4).minLength(10).isValid("Hexlet"));
    }

    @Test
    void lastContainsValidationWins() {
        StringSchema stringSchema = new StringSchema();

        assertTrue(stringSchema.contains("nope").contains("hex").isValid("hexlet"));
        assertFalse(stringSchema.contains("hex").contains("nope").isValid("hexlet"));
    }
}
