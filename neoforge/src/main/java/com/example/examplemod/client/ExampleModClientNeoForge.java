package com.example.examplemod.client;

import com.example.examplemod.ExampleMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = ExampleMod.MOD_ID, dist = Dist.CLIENT)
public class ExampleModClientNeoForge {
    public ExampleModClientNeoForge() {
		ExampleModClient.init();
    }
}
