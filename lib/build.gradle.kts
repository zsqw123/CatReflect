plugins {
    id("java-library")
    id("kotlin")
    id("com.vanniktech.maven.publish")
}

group = "io.github.zsqw123"
version = "1.0.4"

dependencies {
}

mavenPublish {
    sonatypeHost = com.vanniktech.maven.publish.SonatypeHost.S01
}