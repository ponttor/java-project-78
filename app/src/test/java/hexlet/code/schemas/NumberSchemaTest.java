import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    @Test
    void numberSchemaIsValid() {
        NumberSchema numberSchema = new NumberSchema();

        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(1));
    }

    @Test
    void requiredNumberSchemaValidation() {
        NumberSchema numberSchema = new NumberSchema();

        assertFalse(numberSchema.required().isValid(null));
        assertTrue(numberSchema.required().isValid(10));
    }

    @Test
    void positiveNumberSchemaValidation() {
         NumberSchema numberSchema = new NumberSchema();

         assertFalse(numberSchema.positive().isValid(-1));
         assertFalse(numberSchema.positive().isValid(0));
         assertTrue(numberSchema.positive().isValid(10));
     }

    @Test
    void rangeNumberSchemaValidation() {
        NumberSchema numberSchema = new NumberSchema();

        assertFalse(numberSchema.range(5, 10).isValid(4));
        assertFalse(numberSchema.range(5, 10).isValid(11));
        assertTrue(numberSchema.range(5, 10).isValid(7));
    }

    @Test
    void combinedNumberSchemaValidation() {
        NumberSchema numberSchema = new NumberSchema();

        assertTrue(numberSchema.required().positive().range(5, 10).isValid(7));
        assertFalse(numberSchema.required().positive().range(5, 10).isValid(null));
        assertFalse(numberSchema.required().positive().range(5, 10).isValid(-7));
        assertFalse(numberSchema.required().positive().range(5, 10).isValid(11));
    }

    @Test
    void lastRangeValidationWins() {
        NumberSchema numberSchema = new NumberSchema();

        assertTrue(numberSchema.range(100, 200).range(5, 10).isValid(7));
        assertFalse(numberSchema.range(5, 10).range(100, 200).isValid(7));
    }
}
