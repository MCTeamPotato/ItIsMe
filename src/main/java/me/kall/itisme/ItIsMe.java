package me.kall.itisme;

import me.kall.duplicationless.util.Mods;
import me.kall.itisme.config.ItIsMeConfig;
import me.kall.itisme.integration.SodiumIntegration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

@Mod(ItIsMe.MOD_ID)
public final class ItIsMe {
    public static final String MOD_ID = "itisme";

    public ItIsMe(FMLJavaModLoadingContext context) {
        if (!FMLLoader.getDist().isClient()) return;
        context.registerConfig(ModConfig.Type.CLIENT, ItIsMeConfig.LIGHT_TWEAK_CONFIG);

        if (Mods.isLoaded("embeddium")) SodiumIntegration.register();
    }
}
