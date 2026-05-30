package me.kall.itisme;

import me.kall.itisme.config.ItIsMeConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(ItIsMe.MOD_ID)
public final class ItIsMe {
    public static final String MOD_ID = "itisme";

    public ItIsMe(IEventBus modBus, Dist dist, ModContainer container) {
        if (!dist.isClient()) return;
        container.registerConfig(ModConfig.Type.CLIENT, ItIsMeConfig.LIGHT_TWEAK_CONFIG);
    }
}
