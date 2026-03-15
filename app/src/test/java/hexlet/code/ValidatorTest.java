package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ValidatorTest {

    @Test
    void validatorCreatesStringSchema() {
        Validator validator = new Validator();

        assertInstanceOf(StringSchema.class, validator.string());
    }

    @Test
    void validatorCreatesNumberSchema() {
        Validator validator = new Validator();

        assertInstanceOf(NumberSchema.class, validator.number());
    }

    @Test
    void validatorCreatesMapSchema() {
        Validator validator = new Validator();

        assertInstanceOf(MapSchema.class, validator.map());
    }
}
