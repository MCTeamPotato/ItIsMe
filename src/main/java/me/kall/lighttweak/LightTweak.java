package me.kall.lighttweak;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(LightTweak.MOD_ID)
public final class LightTweak {
    public static final String MOD_ID = "lighttweak";

    public LightTweak(FMLJavaModLoadingContext context) {
        if (!FMLLoader.getDist().isClient()) return;
        context.registerConfig(ModConfig.Type.CLIENT, LightTweakConfig.LIGHT_TWEAK_CONFIG);
    }
}
