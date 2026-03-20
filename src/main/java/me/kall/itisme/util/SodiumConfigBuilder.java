package me.kall.itisme.util;

import me.kall.itisme.ItIsMe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.embeddedt.embeddium.api.options.control.ControlValueFormatter;
import org.embeddedt.embeddium.api.options.control.SliderControl;
import org.embeddedt.embeddium.api.options.control.TickBoxControl;
import org.embeddedt.embeddium.api.options.structure.Option;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpact;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.impl.gui.EmbeddiumOptions;
import org.embeddedt.embeddium.impl.gui.options.storage.EmbeddiumOptionsStorage;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class SodiumConfigBuilder {
    private static final EmbeddiumOptionsStorage PLACEHOLDER = new EmbeddiumOptionsStorage();

    public static OptionImpl<EmbeddiumOptions, Integer> intOption(String idPath, Component optionName, Component optionTooltip, int min, int max, int interval, BiConsumer<EmbeddiumOptions, Integer> setter, Function<EmbeddiumOptions, Integer> getter, OptionImpact optionImpact) {
        return OptionImpl.createBuilder(Integer.TYPE, PLACEHOLDER)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, idPath))
                .setName(optionName)
                .setControl(option -> new SliderControl(option, min, max, interval, ControlValueFormatter.number()))
                .setBinding(setter, getter)
                .setImpact(optionImpact)
                .setTooltip(optionTooltip)
                .build();
    }

    public static OptionImpl<EmbeddiumOptions, Boolean> booleanOption(String idPath, Component optionName, Component optionTooltip, BiConsumer<EmbeddiumOptions, Boolean> setter, Function<EmbeddiumOptions, Boolean> getter, OptionImpact optionImpact) {
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
