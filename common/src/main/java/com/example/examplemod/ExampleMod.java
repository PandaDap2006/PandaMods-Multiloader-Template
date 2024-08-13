package com.example.examplemod;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
    }

	public static ResourceLocation LOCATION(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
