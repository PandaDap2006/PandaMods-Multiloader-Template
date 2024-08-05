// gradle.properties
val modId: String by project

val minecraftVersion: String by project
val forgeVersion: String by project

val parchmentVersion: String by project
val parchmentMinecraftVersion: String by project

architectury {
	platformSetupLoomIde()
	forge()
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)

    forge {
        convertAccessWideners.set(true)
        extraAccessWideners.add(loom.accessWidenerPath.get().asFile.name)

        mixinConfig("${modId}-common.mixins.json")
        mixinConfig("${modId}.mixins.json")
    }
}

configurations {
	create("common")
	create("shadowCommon")
	compileClasspath.get().extendsFrom(configurations["common"])
	runtimeClasspath.get().extendsFrom(configurations["common"])
	getByName("developmentForge").extendsFrom(configurations["common"])
}

dependencies {
    forge("net.minecraftforge:forge:${minecraftVersion}-${forgeVersion}")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionForge")) { isTransitive = false }
}

tasks {
    base.archivesName.set(base.archivesName.get() + "-Forge")

    shadowJar {
        configurations = listOf(project.configurations.getByName("shadowCommon"))
        archiveClassifier.set("dev-shadow")

		exclude("fabric.mod.json")
    }

    remapJar {
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
    }

    jar.get().archiveClassifier.set("dev")

    sourcesJar {
        val commonSources = project(":common").tasks.sourcesJar
        dependsOn(commonSources)
        from(commonSources.get().archiveFile.map { zipTree(it) })
    }
}