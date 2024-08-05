package com.example.examplemod.client;

import com.example.examplemod.ExampleMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = ExampleMod.MOD_ID, dist = Dist.CLIENT)
public class ExampleModClientNeoForge {
	private static ExampleModClientNeoForge INSTANCE;

    public ExampleModClientNeoForge() {
		ExampleModClientNeoForge.INSTANCE = this;
    }

	public static ExampleModClientNeoForge getINSTANCE() {
		return INSTANCE;
	}
}
