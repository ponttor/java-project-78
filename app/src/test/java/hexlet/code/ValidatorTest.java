import hexlet.code.schemas.StringSchema;
import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ValidatorTest {

    @Test
    void validatorCreatesStringSchema() {
        Validator validator = new Validator();

        assertInstanceOf(StringSchema.class, validator.string());
    }
}
