### Hexlet tests and linter status:
[![Actions Status](https://github.com/ponttor/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ponttor/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ponttor_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ponttor_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ponttor_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ponttor_java-project-78)

# Data Validator

`Data Validator` is a Java library for validating data correctness. The project focuses on object-oriented design, extensible architecture, SOLID principles, and a fluent interface for building expressive validation rules.

This project is part of the Hexlet Java Developer program, module 3.

## Goal

The goal of the project is to build a reusable validation library and practice designing clean OO architecture. The implementation is expected to rely on composition, carefully designed class hierarchies, fluent API patterns, and maintainable extension points.

## Description

Most real applications work with external input that must be validated before use. A validation library makes it possible to describe these rules declaratively and apply them consistently across different types of data.

This project implements a validator inspired by libraries like `yup`. It provides a small domain-specific language for describing validation constraints in a compact and readable way.

## Features

- string validation schemas
- number validation schemas
- map validation schemas
- support for nested map structure validation
- fluent interface for declaring rules
- architecture designed for extensibility
- automated testing with JUnit

## Example

```java
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

Validator v = new Validator();

// Strings
StringSchema stringSchema = v.string().required();

stringSchema.isValid("what does the fox say"); // true
stringSchema.isValid(""); // false

// Numbers
NumberSchema numberSchema = v.number().required().positive();

numberSchema.isValid(-10); // false
numberSchema.isValid(10); // true

// Map with shape validation
Map<String, BaseSchema<?>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

MapSchema mapSchema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("firstName", "John");
human1.put("lastName", "Smith");
mapSchema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("firstName", "Anna");
human2.put("lastName", "B");
mapSchema.isValid(human2); // false
```

## DSL Approach

The library API is designed as a small DSL. Instead of manually assembling validator objects, the user describes constraints through chained method calls. This style improves readability and keeps validation rules close to the business meaning of the data.

## Architecture Focus

The core challenge of the project is validator design. The internal structure should remain simple, expressive, and easy to extend without rewriting existing code. Special attention is paid to:

- schema composition
- separation of responsibilities
- fluent API design
- local and global state management
- compliance with SOLID principles

## Testing

Automated tests are a core part of the project. The validator is well suited for test-driven development and refactoring practice. Tests are written with JUnit.

## Requirements

- Java 21 or a compatible JDK
- Gradle Wrapper included in the repository

## Installation

```bash
git clone https://github.com/ponttor/java-project-78.git
cd app
./gradlew installDist
```

## Build and Test

```bash
cd app
./gradlew build
```

```bash
cd app
./gradlew test
```

CI status and checks:

- https://github.com/ponttor/java-project-78/actions
