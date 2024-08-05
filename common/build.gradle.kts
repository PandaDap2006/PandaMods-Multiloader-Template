// gradle.properties
val modId: String by extra

val minecraftVersion: String by extra
val fabricLoaderVersion: String by extra

val parchmentVersion: String by extra
val parchmentMinecraftVersion: String by extra

val supportedModLoaders: String by extra

architectury {
	common(supportedModLoaders.split(","))
	platformSetupLoomIde()
}

loom.accessWidenerPath.set(file("src/main/resources/${modId}.accesswidener"))

dependencies {
    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${fabricLoaderVersion}")
}
