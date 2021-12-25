plugins {
    id("java-library")
    id("kotlin")
}

dependencies {
//    implementation("io.github.zsqw123:cat-reflect:1.0.0")
    implementation(project(":lib"))
}