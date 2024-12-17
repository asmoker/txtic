group = "top.threep.plugin"
version = "0.9.3"

plugins {
    id("java")
    id("org.jetbrains.intellij.platform") version "2.1.0"
}
repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation("ws.vinta:pangu:1.1.0")

    intellijPlatform {
        intellijIdeaCommunity("2022.3.1", useInstaller = false)
        bundledPlugin("com.intellij.java")

        pluginVerifier()
        zipSigner()
        instrumentationTools()
    }
}
intellijPlatform {
    pluginConfiguration {
        id = "top.threep.plugin.txtic"
        name = "txtic"
        version = "0.9.3"
        ideaVersion {
            sinceBuild = "222"
            untilBuild = "243.*"
        }
    }
    publishing {
        host = "https://plugins.jetbrains.com"
        token.set(System.getenv("PUBLISH_TOKEN"))
        channels = listOf("default")
    }
}


tasks {
    withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.encoding = "UTF-8"
    }
}