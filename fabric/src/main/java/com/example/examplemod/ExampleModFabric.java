package com.example.examplemod;

import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
	private static ExampleModFabric INSTANCE;

	@Override
	public void onInitialize() {
		ExampleModFabric.INSTANCE = this;
	}

	public static ExampleModFabric getINSTANCE() {
		return INSTANCE;
	}
}
