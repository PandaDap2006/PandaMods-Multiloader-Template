package com.example.examplemod.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.ExampleModFabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class ExampleModClientFabric implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ExampleModClient.init();
	}
}
