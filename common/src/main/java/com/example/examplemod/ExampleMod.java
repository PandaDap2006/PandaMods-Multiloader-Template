package com.example.examplemod;

import com.example.examplemod.client.ExampleModClient;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LogUtils.getLogger();

	private static ExampleMod INSTANCE;

    public ExampleMod() {
		ExampleMod.INSTANCE = this;
    }

	public static ResourceLocation location(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public static ExampleMod getINSTANCE() {
		return INSTANCE;
	}
}
