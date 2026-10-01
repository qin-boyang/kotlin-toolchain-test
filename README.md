# Kotlin Toolchain Test

A personal playground for trying the Kotlin Toolchain. I moved my Kotlin practice examples and test files from a Gradle project into this project to experiment with the toolchain and see how it handles building, running, and testing Kotlin code.

The project includes collection exercises, concurrency examples using coroutines, design pattern examples, and HTTP API tests using Ktor. It is for learning and experimentation.

## Project structure

- `src/` — Kotlin examples and application code.
- `test/` — tests using JUnit 5, including Ktor API tests.
- `module.yaml` — module settings and dependencies, replacing the Gradle build configuration.
- `kotlin` / `kotlin.bat` — Kotlin Toolchain wrappers for macOS/Linux and Windows.

## Build

```sh
./kotlin build
```

On Windows, use `kotlin.bat` instead of `./kotlin`.

## Run an example

The project contains several standalone examples with their own `main` functions. Select an example by its JVM main class:

```sh
./kotlin run --main-class=com.mycompany.myproject.concurrency.Main9Kt
```

## Run tests

```sh
./kotlin test
```

To run only the local passing example test:

```sh
./kotlin test --include-test=WorldTest.doTest
```

`WorldTest.shouldFail` intentionally fails as a test runner experiment. The Ktor API tests require internet access and send requests to JSONPlaceholder.

## Migration notes

Moving the Kotlin files did not carry over the Gradle dependencies. Coroutines and the Ktor client dependencies are now declared in `module.yaml`. The test configuration uses JUnit 5, and the migrated API tests use its annotations and assertions.
