package com.example.examplemod.client;

import com.mojang.logging.LogUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;


@Environment(EnvType.CLIENT)
public class ExampleModClient {
	private static ExampleModClient INSTANCE;

    public ExampleModClient() {
		ExampleModClient.INSTANCE = this;
    }

	public static ExampleModClient getINSTANCE() {
		return INSTANCE;
	}
}
