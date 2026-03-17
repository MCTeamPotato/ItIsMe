package me.kall.itisme.integration;

import com.google.common.collect.ImmutableList;
import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import me.kall.itisme.ItIsMe;
import me.kall.itisme.ItIsMeConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.embeddedt.embeddium.api.OptionGUIConstructionEvent;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SodiumIntegration {
    private static final SodiumOptionsStorage sodiumOpts = new SodiumOptionsStorage();

    private static final Component EMPTY = Component.empty();

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(SodiumIntegration::registerConfig);
    }

    private static void registerConfig(@NotNull OptionGUIConstructionEvent event) {
        OptionGroup playerOutlineGroup = createOutlineGroup(
                "player_outline",
                ItIsMeConfig.PLAYER_OUTLINE_RENDERABLE, ItIsMeConfig.PLAYER_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.PLAYER_OUTLINE_RED, ItIsMeConfig.PLAYER_OUTLINE_RED::set,
                ItIsMeConfig.PLAYER_OUTLINE_GREEN, ItIsMeConfig.PLAYER_OUTLINE_GREEN::set,
                ItIsMeConfig.PLAYER_OUTLINE_BLUE, ItIsMeConfig.PLAYER_OUTLINE_BLUE::set,
                ItIsMeConfig.PLAYER_OUTLINE_ALPHA, ItIsMeConfig.PLAYER_OUTLINE_ALPHA::set
        );

        OptionGroup bossOutlineGroup = createOutlineGroup(
                "boss_outline",
                ItIsMeConfig.BOSS_OUTLINE_RENDERABLE, ItIsMeConfig.BOSS_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.BOSS_OUTLINE_RED, ItIsMeConfig.BOSS_OUTLINE_RED::set,
                ItIsMeConfig.BOSS_OUTLINE_GREEN, ItIsMeConfig.BOSS_OUTLINE_GREEN::set,
                ItIsMeConfig.BOSS_OUTLINE_BLUE, ItIsMeConfig.BOSS_OUTLINE_BLUE::set,
                ItIsMeConfig.BOSS_OUTLINE_ALPHA, ItIsMeConfig.BOSS_OUTLINE_ALPHA::set
        );

        OptionGroup entityOutlineGroup = createOutlineGroup(
                "entity_outline",
                ItIsMeConfig.ENTITY_OUTLINE_RENDERABLE, ItIsMeConfig.ENTITY_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.ENTITY_OUTLINE_RED, ItIsMeConfig.ENTITY_OUTLINE_RED::set,
                ItIsMeConfig.ENTITY_OUTLINE_GREEN, ItIsMeConfig.ENTITY_OUTLINE_GREEN::set,
                ItIsMeConfig.ENTITY_OUTLINE_BLUE, ItIsMeConfig.ENTITY_OUTLINE_BLUE::set,
                ItIsMeConfig.ENTITY_OUTLINE_ALPHA, ItIsMeConfig.ENTITY_OUTLINE_ALPHA::set
        );

        OptionPage entityOutlinePage = new OptionPage(
                OptionIdentifier.create(ItIsMe.MOD_ID, "entiy_outline_page"),
                Component.translatable("config.itisme.entity_outline_page"),
                ImmutableList.of(playerOutlineGroup, bossOutlineGroup, entityOutlineGroup)
        );

        event.addPage(entityOutlinePage);

        OptionGroup playerShadowOutlineGroup = createOutlineGroup(
                "player_shadow_outline",
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RED, ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_GREEN, ItIsMeConfig.PLAYER_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_BLUE, ItIsMeConfig.PLAYER_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_ALPHA, ItIsMeConfig.PLAYER_SHADOW_OUTLINE_ALPHA::set
        );

        OptionGroup bossShadowOutlineGroup = createOutlineGroup(
                "boss_shadow_outline",
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.BOSS_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_RED, ItIsMeConfig.BOSS_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_GREEN, ItIsMeConfig.BOSS_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_BLUE, ItIsMeConfig.BOSS_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_ALPHA, ItIsMeConfig.BOSS_SHADOW_OUTLINE_ALPHA::set
        );

        OptionGroup entityShadowOutlineGroup = createOutlineGroup(
                "entity_shadow_outline",
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA::set
        );

        OptionPage entityShadowOutlinePage = new OptionPage(
                OptionIdentifier.create(ItIsMe.MOD_ID, "entity_shadow_outline_page"),
                Component.translatable("config.itisme.entity_shadow_outline_page"),
                ImmutableList.of(playerShadowOutlineGroup, bossShadowOutlineGroup, entityShadowOutlineGroup)
        );

        event.addPage(entityShadowOutlinePage);
    }

    private static OptionImpl<SodiumGameOptions, Boolean> createBooleanOption(String key, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return OptionImpl.createBuilder(Boolean.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, key))
                .setName(Component.translatable("config.itisme." + key + ".name"))
                .setTooltip(EMPTY)
                .setControl(TickBoxControl::new)
                .setBinding((options, value) -> setter.accept(value), options -> getter.get())
                .setImpact(OptionImpact.LOW)
                .build();
    }

    private static OptionImpl<SodiumGameOptions, Integer> createColorOption(String key, Supplier<Integer> getter, Consumer<Integer> setter) {
        return OptionImpl.createBuilder(Integer.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, key))
                .setName(Component.translatable("config.itisme." + key + ".name"))
                .setTooltip(EMPTY)
                .setControl(option -> new SliderControl(option, 0, 255, 5, ControlValueFormatter.number()))
                .setBinding((options, value) -> setter.accept(value), options -> getter.get())
                .build();
    }

    private static OptionGroup createOutlineGroup(
            String prefix,
            Supplier<Boolean> renderableGetter,
            Consumer<Boolean> renderableSetter,
            Supplier<Integer> rGetter, Consumer<Integer> rSetter,
            Supplier<Integer> gGetter, Consumer<Integer> gSetter,
            Supplier<Integer> bGetter, Consumer<Integer> bSetter,
            Supplier<Integer> aGetter, Consumer<Integer> aSetter
    ) {
        return OptionGroup.createBuilder()
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, prefix + "_group"))
                .add(createBooleanOption(prefix + "_renderable", renderableGetter, renderableSetter))
                .add(createColorOption(prefix + "_red", rGetter, rSetter))
                .add(createColorOption(prefix + "_green", gGetter, gSetter))
                .add(createColorOption(prefix + "_blue", bGetter, bSetter))
                .add(createColorOption(prefix + "_alpha", aGetter, aSetter))
                .build();
    }
}
