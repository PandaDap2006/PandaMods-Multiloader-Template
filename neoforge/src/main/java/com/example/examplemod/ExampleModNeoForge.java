package com.example.examplemod;

import net.neoforged.fml.common.Mod;

@Mod(ExampleMod.MOD_ID)
public class ExampleModNeoForge {
	@SuppressWarnings("FieldMayBeFinal")
	private static ExampleModNeoForge INSTANCE;

    public ExampleModNeoForge() {
		ExampleModNeoForge.INSTANCE = this;
    }

	public static ExampleModNeoForge getINSTANCE() {
		return INSTANCE;
	}
}
