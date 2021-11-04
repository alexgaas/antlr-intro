import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.31"
    antlr
    java
    idea
    application
}

group = "alexgaas"
version = "1.0.0"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

dependencies {
    antlr("org.antlr:antlr4:4.9.2")
    implementation( "org.antlr:antlr4-runtime:4.9")
}

application {
    mainClass.set("MainKt")
}

tasks.generateGrammarSource {
    arguments.add("-visitor")
    outputDirectory = file( "src/main/kotlin/gen")
}

tasks.build {
    dependsOn("generateGrammarSource")
}

tasks.clean {
    delete("src/main/kotlin/gen")
}


