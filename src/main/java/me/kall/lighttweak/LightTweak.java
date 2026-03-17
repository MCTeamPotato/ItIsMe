package me.kall.lighttweak;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(LightTweak.MOD_ID)
public final class LightTweak {
    public static final String MOD_ID = "lighttweak";

    public LightTweak(IEventBus modBus, Dist dist, ModContainer container) {
        if (!dist.isClient()) return;
        container.registerConfig(ModConfig.Type.CLIENT, LightTweakConfig.LIGHT_TWEAK_CONFIG);
    }
}
