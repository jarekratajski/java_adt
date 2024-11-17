plugins {
    id("java")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)

    }
}
tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
    options.compilerArgs.add("-Werror")
}

//
//tasks.withType<Test>().configureEach {
//    jvmArgs("--enable-preview")
//}
//
tasks.withType<JavaExec>().configureEach {
    jvmArgs("--enable-preview")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}