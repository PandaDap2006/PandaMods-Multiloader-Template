package com.example.examplemod;

import com.example.examplemod.client.ExampleModClientForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;

@Mod(ExampleMod.MOD_ID)
public class ExampleModForge {
	private static ExampleModForge INSTANCE;

    public ExampleModForge() {
		ExampleModForge.INSTANCE = this;

		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ExampleModClientForge::new);
    }

	public static ExampleModForge getINSTANCE() {
		return INSTANCE;
	}
}
