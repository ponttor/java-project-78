package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

class MapSchemaTest {
    @Test
    void mapSchemaIsValid() {
        MapSchema mapSchema = new MapSchema();

        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));

        Map<String, String> validMap = new HashMap<>();
        validMap.put("key1", "value1");
        validMap.put("key2", "value2");
        assertTrue(mapSchema.isValid(validMap));
    }

    @Test
    void requiredMapSchemaValidation() {
        MapSchema mapSchema = new MapSchema();

        assertFalse(mapSchema.required().isValid(null));
        assertTrue(mapSchema.required().isValid(new HashMap<>()));
    }

    @Test
    void sizeofMapSchemaValidation() {
        MapSchema mapSchema = new MapSchema();

        Map<String, String> validMap = new HashMap<>();
        validMap.put("key1", "value1");
        validMap.put("key2", "value2");

        assertFalse(mapSchema.sizeof(3).isValid(validMap));
        assertFalse(mapSchema.sizeof(3).isValid(new HashMap<>()));

        validMap.put("key3", "value3");
        assertTrue(mapSchema.sizeof(3).isValid(validMap));
    }

    @Test
    void combinedMapSchemaValidation() {
        MapSchema mapSchema = new MapSchema();

        Map<String, String> validMap = new HashMap<>();
        validMap.put("key1", "value1");
        validMap.put("key2", "value2");

        assertTrue(mapSchema.required().sizeof(2).isValid(validMap));
        assertFalse(mapSchema.required().sizeof(2).isValid(null));
        assertFalse(mapSchema.required().sizeof(12).isValid(validMap));
    }

    @Test
    void lastSizeofValidationWins() {
        MapSchema mapSchema = new MapSchema();

        Map<String, String> validMap = new HashMap<>();
        validMap.put("key1", "value1");

        assertTrue(mapSchema.sizeof(100).sizeof(1).isValid(validMap));
        assertFalse(mapSchema.sizeof(1).sizeof(200).isValid(validMap));
    }

    @Test
    void shapeMapSchemaValidation() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        Map<String, String> validMap = new HashMap<>();
        validMap.put("firstName", "John");
        validMap.put("lastName", "Smith");

        Map<String, String> nullValueMap = new HashMap<>();
        nullValueMap.put("firstName", "John");
        nullValueMap.put("lastName", null);

        Map<String, String> shortValueMap = new HashMap<>();
        shortValueMap.put("firstName", "Anna");
        shortValueMap.put("lastName", "B");

        assertTrue(mapSchema.shape(schemas).isValid(validMap));
        assertFalse(mapSchema.shape(schemas).isValid(nullValueMap));
        assertFalse(mapSchema.shape(schemas).isValid(shortValueMap));
    }

    @Test
    void combinedShapeMapSchemaValidation() {
        Validator validator = new Validator();
        MapSchema mapSchema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        Map<String, String> validMap = new HashMap<>();
        validMap.put("firstName", "John");
        validMap.put("lastName", "Smith");

        Map<String, String> invalidMap = new HashMap<>();
        invalidMap.put("firstName", "John");
        invalidMap.put("lastName", "B");

        assertTrue(mapSchema.required().sizeof(2).shape(schemas).isValid(validMap));
        assertFalse(mapSchema.required().sizeof(2).shape(schemas).isValid(null));
        assertFalse(mapSchema.required().sizeof(2).shape(schemas).isValid(invalidMap));
    }
}
