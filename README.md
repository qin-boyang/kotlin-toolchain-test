# Kotlin Toolchain Test

A personal playground for trying the Kotlin Toolchain. I moved my Kotlin practice examples and test files from a Gradle project into this project to experiment with the toolchain and see how it handles building, running, and testing Kotlin code.

The project includes collection exercises, concurrency examples using coroutines, design pattern examples, and HTTP API tests using Ktor. It is for learning and experimentation.

## Kotlin Toolchain vs. Gradle

What I like about this experiment is how clean the project feels. For a small Kotlin playground, the build configuration fits into one short `module.yaml`, and the code lives directly in `src/` and `test/`. There is less setup to navigate before getting to the Kotlin examples.

| Area | This Kotlin Toolchain project | Typical Kotlin JVM Gradle project |
| --- | --- | --- |
| Build configuration | One declarative `module.yaml` for this single module | `build.gradle.kts` and `settings.gradle.kts` |
| Source layout | `src/` and `test/` | `src/main/kotlin/` and `src/test/kotlin/` |
| Dependencies | Maven coordinates under `dependencies` and `test-dependencies` | `implementation(...)` and `testImplementation(...)` in a dependency block |
| JUnit 5 | Built-in default; explicitly selected here with `junit: junit-5` | Test dependencies and JUnit Platform configuration in the build script |
| Wrapper files | `kotlin` and `kotlin.bat` | `gradlew`, `gradlew.bat`, and `gradle/wrapper/` files |
| Build / test commands | `./kotlin build` / `./kotlin test` | `./gradlew build` / `./gradlew test` |

These commands are not exact equivalents: `./kotlin build` compiles the project, while a typical Gradle `build` task also runs tests. With the Kotlin Toolchain, I run `./kotlin test` separately.

Here is the entire module configuration for this playground:

```yaml
product: jvm/app

dependencies:
  - org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3

test-dependencies:
  - io.ktor:ktor-client-core:2.3.13
  - io.ktor:ktor-client-cio:2.3.13

settings:
  junit: junit-5
```

For my current goal—moving practice files over and trying them out—this compact setup makes the project easy to read. The comparison is about configuration and layout; I have not benchmarked build speed. The original Gradle project also included Spring Boot configuration that these standalone examples do not need, so some of the reduction comes from the smaller scope.

For details on translating Gradle configuration, see the official [Kotlin Toolchain migration guide](https://kotlin-toolchain.org/latest/getting-started/migrating-from-gradle/).

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
