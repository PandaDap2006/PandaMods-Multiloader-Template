import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
	java
	idea

    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.7-SNAPSHOT" apply false

	id("com.github.johnrengelman.shadow") version "8.1.1" apply false
	id("systems.manifold.manifold-gradle-plugin") version "0.0.2-alpha"
}

fun writeBuildGradlePredefine(AvailableVersion: List<String>, versionIndex: Int) {
	val sb = StringBuilder()

	sb.append("# DON'T TOUCH THIS FILE, This is handled by the build script\n")

	for ((index, s) in AvailableVersion.withIndex()) {
		val versionString = s.replace(".", "_")
		sb.append("MC_${versionString}=${index}\n")
		ext.set("MC_${versionString}", index.toString())

		if (versionIndex == index) {
			sb.append("MC_VER=${index}\n")
			ext.set("MC_VER", index.toString())
		}
	}

	File(projectDir, "build.properties").writeText(sb.toString())
}

project.gradle.extra.properties.forEach { prop ->
	ext.set(prop.key, prop.value)
}

writeBuildGradlePredefine(project.properties["availableVersions"] as List<String>, project.properties["versionIndex"] as Int)

// gradle.properties
val projectArchivesName: String by project
val projectGroup: String by project

val modId: String by project
val modVersion: String by project
val javaVersion: String by project
val modName: String by project
val modDescription: String by project
val modAuthor: String by project

val minecraftVersion: String by project

val parchmentVersion: String by project
val parchmentMinecraftVersion: String by project

val manifoldVersion: String by project

architectury.minecraft = minecraftVersion

subprojects {
    apply(plugin = "dev.architectury.loom")
	apply(plugin = "com.github.johnrengelman.shadow")
	apply(plugin = "maven-publish")

    val loom = project.extensions.getByName<LoomGradleExtensionAPI>("loom")
    loom.silentMojangMappingsLicense()

    repositories {
        mavenCentral()
        mavenLocal()

        maven("https://maven.parchmentmc.org")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.neoforged.net/releases/")
    }

    @Suppress("UnstableApiUsage")
    dependencies {
        "minecraft"("com.mojang:minecraft:${minecraftVersion}")
        "mappings"(loom.layered{
            officialMojangMappings()
            parchment("org.parchmentmc.data:parchment-${parchmentMinecraftVersion}:${parchmentVersion}@zip")
        })

        compileOnly("org.jetbrains:annotations:24.1.0")
		annotationProcessor("systems.manifold:manifold-preprocessor:${manifoldVersion}")
    }
}

allprojects {
    apply(plugin = "java")
	apply(plugin = "idea")

    apply(plugin = "architectury-plugin")
    apply(plugin = "maven-publish")

	base.archivesName.set(projectArchivesName)
    version = "${modVersion}-${minecraftVersion}"
    group = projectGroup

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(JavaLanguageVersion.of(javaVersion).asInt())
		options.compilerArgs.add("-Xplugin:Manifold")
    }

	tasks {
		jar {
			manifest {
				attributes(mapOf(
					"Specification-Title" to modName,
					"Specification-Vendor" to modAuthor,
					"Specification-Version" to modVersion,
					"Implementation-Title" to name,
					"Implementation-Vendor" to modAuthor,
					"Implementation-Version" to archiveVersion
				))
			}
		}

		processResources {
			inputs.property("version", version)

			filesMatching(listOf("META-INF/mods.toml", "META-INF/neoforge.mods.toml", "pack.mcmeta", "fabric.mod.json")) {
				expand(mapOf(
					"minecraftVersion" to minecraftVersion,

					"modId" to modId,
					"modVersion" to modVersion,
					"modName" to modName,
					"modDescription" to modDescription,
					"modAuthor" to modAuthor
				))
			}
		}
	}

    java.withSourcesJar()
}

