package com.example.examplemod.client;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.client.ExampleModClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
public class ExampleModClientForge {
	@SuppressWarnings("FieldMayBeFinal")
	private static ExampleModClientForge INSTANCE;

    public ExampleModClientForge() {
		ExampleModClientForge.INSTANCE = this;
    }

	public static ExampleModClientForge getINSTANCE() {
		return INSTANCE;
	}
}
