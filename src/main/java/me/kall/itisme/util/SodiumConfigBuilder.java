package me.kall.itisme.util;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.jellysquid.mods.sodium.client.gui.options.Option;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpact;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;
import me.jellysquid.mods.sodium.client.gui.options.storage.SodiumOptionsStorage;
import me.kall.itisme.ItIsMe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class SodiumConfigBuilder {
    private static final SodiumOptionsStorage PLACEHOLDER = new SodiumOptionsStorage();

    public static OptionImpl<SodiumGameOptions, Integer> intOption(String idPath, Component optionName, Component optionTooltip, int min, int max, int interval, BiConsumer<SodiumGameOptions, Integer> setter, Function<SodiumGameOptions, Integer> getter, OptionImpact optionImpact) {
        return OptionImpl.createBuilder(Integer.TYPE, PLACEHOLDER)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, idPath))
                .setName(optionName)
                .setControl(option -> new SliderControl(option, min, max, interval, ControlValueFormatter.number()))
                .setBinding(setter, getter)
                .setImpact(optionImpact)
                .setTooltip(optionTooltip)
                .build();
    }

    public static OptionImpl<SodiumGameOptions, Boolean> booleanOption(String idPath, Component optionName, Component optionTooltip, BiConsumer<SodiumGameOptions, Boolean> setter, Function<SodiumGameOptions, Boolean> getter, OptionImpact optionImpact) {
        return OptionImpl.createBuilder(Boolean.TYPE, PLACEHOLDER)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, idPath))
                .setName(optionName)
                .setTooltip(optionTooltip)
                .setControl(TickBoxControl::new)
                .setBinding(setter, getter)
                .setImpact(optionImpact)
                .build();
    }

    public static OptionGroup buildGroup(String groupIdPath, Option<?> @NotNull ... options) {
        OptionGroup.Builder builder = OptionGroup.createBuilder().setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, groupIdPath));
        for (Option<?> option : options) {
            builder.add(option);
        }
        return builder.build();
    }
}
