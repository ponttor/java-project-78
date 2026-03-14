import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NumberSchemaTest {
    @Test
    void numberSchemaIsValid() {
        NumberSchema numberSchema = new NumberSchema();

        assert numberSchema.isValid(null);
        assert numberSchema.isValid(1);
    }

    @Test
    void requiredNumberSchemaValidation() {
        NumberSchema numberSchema = new NumberSchema();

        assertFalse(numberSchema.required().isValid(null));
        assert numberSchema.required().isValid(10);
    }

    @Test
    void positiveNumberSchemaValidation() {
         NumberSchema numberSchema = new NumberSchema();

         assertFalse(numberSchema.positive().isValid(-1));
         assertFalse(numberSchema.positive().isValid(0));
         assert numberSchema.positive().isValid(10);
     }

    @Test
    void rangeNumberSchemaValidation() {
        NumberSchema numberSchema = new NumberSchema();

        assertFalse(numberSchema.range(5, 10).isValid(4));
        assertFalse(numberSchema.range(5, 10).isValid(11));
        assert numberSchema.range(5, 10).isValid(7);
    }

    @Test
    void combinedNumberSchemaValidation() {
        NumberSchema numberSchema = new NumberSchema();

        assert numberSchema.required().positive().range(5, 10).isValid(7);
        assertFalse(numberSchema.required().positive().range(5, 10).isValid(null));
        assertFalse(numberSchema.required().positive().range(5, 10).isValid(-7));
        assertFalse(numberSchema.required().positive().range(5, 10).isValid(11));
    }

    @Test
    void lastRangeValidationWins() {
        NumberSchema numberSchema = new NumberSchema();

        assert numberSchema.range(100, 200).range(5, 10).isValid(7);
        assertFalse(numberSchema.range(5, 10).range(100, 200).isValid(7));
    }
}
