// gradle.properties
val modId: String by project

val minecraftVersion: String by project
val neoForgeVersion: String by project

val parchmentVersion: String by project
val parchmentMinecraftVersion: String by project

architectury {
    platformSetupLoomIde()
    neoForge()
}

configurations {
    getByName("developmentNeoForge").extendsFrom(configurations["common"])
}

loom {
    accessWidenerPath.set(project(":common").loom.accessWidenerPath)
}

dependencies {
    neoForge("net.neoforged:neoforge:${neoForgeVersion}")

    "common"(project(":common", "namedElements")) { isTransitive = false }
    "shadowCommon"(project(":common", "transformProductionNeoForge")) { isTransitive = false }
}

tasks {
    base.archivesName.set(base.archivesName.get() + "-NeoForge")

    shadowJar {
		exclude("fabric.mod.json")
    }

    remapJar {
		injectAccessWidener = true
		atAccessWideners.add(loom.accessWidenerPath.get().asFile.name)
    }

    jar.get().archiveClassifier.set("dev")

    sourcesJar {
        val commonSources = project(":common").tasks.sourcesJar
        dependsOn(commonSources)
        from(commonSources.get().archiveFile.map { zipTree(it) })
    }
}