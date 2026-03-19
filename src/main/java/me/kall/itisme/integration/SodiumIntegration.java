package me.kall.itisme.integration;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
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
import me.kall.itisme.OutlineColor;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistries;
import org.embeddedt.embeddium.api.OptionGUIConstructionEvent;
import org.embeddedt.embeddium.client.gui.options.OptionIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class SodiumIntegration {
    private static final SodiumOptionsStorage sodiumOpts = new SodiumOptionsStorage();

    private static final int MAX_GROUPS_PER_PAGE = 30;

    private static final Component EMPTY = Component.empty();

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(SodiumIntegration::registerConfig);
    }

    private static void registerConfig(@NotNull OptionGUIConstructionEvent event) {
        OptionGroup generalGroup = OptionGroup.createBuilder()
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, "general_group"))
                .add(createIntOption("outline_renderable_dist", 0, 512, 1,
                        ItIsMeConfig.OUTLINE_RENDERABLE_DIST,
                        ItIsMeConfig.OUTLINE_RENDERABLE_DIST::set))
                .build();

        OptionPage generalPage = new OptionPage(
                OptionIdentifier.create(ItIsMe.MOD_ID, "general_page"),
                Component.translatable("config.itisme.general_page"),
                ImmutableList.of(generalGroup));

        event.addPage(generalPage);

        OptionGroup playerOutlineGroup = createOutlineGroup("player_outline",
                ItIsMeConfig.PLAYER_OUTLINE_RENDERABLE, ItIsMeConfig.PLAYER_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.PLAYER_OUTLINE_RED,        ItIsMeConfig.PLAYER_OUTLINE_RED::set,
                ItIsMeConfig.PLAYER_OUTLINE_GREEN,      ItIsMeConfig.PLAYER_OUTLINE_GREEN::set,
                ItIsMeConfig.PLAYER_OUTLINE_BLUE,       ItIsMeConfig.PLAYER_OUTLINE_BLUE::set,
                ItIsMeConfig.PLAYER_OUTLINE_ALPHA,      ItIsMeConfig.PLAYER_OUTLINE_ALPHA::set);

        OptionGroup bossOutlineGroup = createOutlineGroup("boss_outline",
                ItIsMeConfig.BOSS_OUTLINE_RENDERABLE, ItIsMeConfig.BOSS_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.BOSS_OUTLINE_RED,        ItIsMeConfig.BOSS_OUTLINE_RED::set,
                ItIsMeConfig.BOSS_OUTLINE_GREEN,      ItIsMeConfig.BOSS_OUTLINE_GREEN::set,
                ItIsMeConfig.BOSS_OUTLINE_BLUE,       ItIsMeConfig.BOSS_OUTLINE_BLUE::set,
                ItIsMeConfig.BOSS_OUTLINE_ALPHA,      ItIsMeConfig.BOSS_OUTLINE_ALPHA::set);

        OptionGroup attackableOutlineGroup = createOutlineGroup("attackable_outline",
                ItIsMeConfig.CHANGE_ATTACKABLE_OUTLINE_COLOR, ItIsMeConfig.CHANGE_ATTACKABLE_OUTLINE_COLOR::set,
                ItIsMeConfig.ATTACKABLE_OUTLINE_RED,          ItIsMeConfig.ATTACKABLE_OUTLINE_RED::set,
                ItIsMeConfig.ATTACKABLE_OUTLINE_GREEN,        ItIsMeConfig.ATTACKABLE_OUTLINE_GREEN::set,
                ItIsMeConfig.ATTACKABLE_OUTLINE_BLUE,         ItIsMeConfig.ATTACKABLE_OUTLINE_BLUE::set,
                ItIsMeConfig.ATTACKABLE_OUTLINE_ALPHA,        ItIsMeConfig.ATTACKABLE_OUTLINE_ALPHA::set);

        OptionGroup neutralOutlineGroup = createOutlineGroup("neutral_outline",
                ItIsMeConfig.NEUTRAL_OUTLINE_RENDERABLE, ItIsMeConfig.NEUTRAL_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.NEUTRAL_OUTLINE_RED,        ItIsMeConfig.NEUTRAL_OUTLINE_RED::set,
                ItIsMeConfig.NEUTRAL_OUTLINE_GREEN,      ItIsMeConfig.NEUTRAL_OUTLINE_GREEN::set,
                ItIsMeConfig.NEUTRAL_OUTLINE_BLUE,       ItIsMeConfig.NEUTRAL_OUTLINE_BLUE::set,
                ItIsMeConfig.NEUTRAL_OUTLINE_ALPHA,      ItIsMeConfig.NEUTRAL_OUTLINE_ALPHA::set);

        OptionGroup hostileOutlineGroup = createOutlineGroup("hostile_outline",
                ItIsMeConfig.HOSTILE_OUTLINE_RENDERABLE, ItIsMeConfig.HOSTILE_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.HOSTILE_OUTLINE_RED,        ItIsMeConfig.HOSTILE_OUTLINE_RED::set,
                ItIsMeConfig.HOSTILE_OUTLINE_GREEN,      ItIsMeConfig.HOSTILE_OUTLINE_GREEN::set,
                ItIsMeConfig.HOSTILE_OUTLINE_BLUE,       ItIsMeConfig.HOSTILE_OUTLINE_BLUE::set,
                ItIsMeConfig.HOSTILE_OUTLINE_ALPHA,      ItIsMeConfig.HOSTILE_OUTLINE_ALPHA::set);

        OptionGroup entityOutlineGroup = createOutlineGroup("entity_outline",
                ItIsMeConfig.ENTITY_OUTLINE_RENDERABLE, ItIsMeConfig.ENTITY_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.ENTITY_OUTLINE_RED,        ItIsMeConfig.ENTITY_OUTLINE_RED::set,
                ItIsMeConfig.ENTITY_OUTLINE_GREEN,      ItIsMeConfig.ENTITY_OUTLINE_GREEN::set,
                ItIsMeConfig.ENTITY_OUTLINE_BLUE,       ItIsMeConfig.ENTITY_OUTLINE_BLUE::set,
                ItIsMeConfig.ENTITY_OUTLINE_ALPHA,      ItIsMeConfig.ENTITY_OUTLINE_ALPHA::set);

        OptionPage entityOutlinePage = new OptionPage(
                OptionIdentifier.create(ItIsMe.MOD_ID, "entity_outline_page"),
                Component.translatable("config.itisme.entity_outline_page"),
                ImmutableList.of(playerOutlineGroup, bossOutlineGroup, attackableOutlineGroup,
                        neutralOutlineGroup, hostileOutlineGroup, entityOutlineGroup));

        event.addPage(entityOutlinePage);

        OptionGroup playerShadowOutlineGroup = createShadowOutlineGroup("player_shadow_outline",
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.PLAYER_SHADOW_RADIUS_EXT,         ItIsMeConfig.PLAYER_SHADOW_RADIUS_EXT::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RED,        ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_GREEN,      ItIsMeConfig.PLAYER_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_BLUE,       ItIsMeConfig.PLAYER_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.PLAYER_SHADOW_OUTLINE_ALPHA,      ItIsMeConfig.PLAYER_SHADOW_OUTLINE_ALPHA::set);

        OptionGroup bossShadowOutlineGroup = createShadowOutlineGroup("boss_shadow_outline",
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.BOSS_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.BOSS_SHADOW_RADIUS_EXT,         ItIsMeConfig.BOSS_SHADOW_RADIUS_EXT::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_RED,        ItIsMeConfig.BOSS_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_GREEN,      ItIsMeConfig.BOSS_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_BLUE,       ItIsMeConfig.BOSS_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.BOSS_SHADOW_OUTLINE_ALPHA,      ItIsMeConfig.BOSS_SHADOW_OUTLINE_ALPHA::set);

        OptionGroup attackableShadowOutlineGroup = createOutlineGroup("attackable_shadow_outline",
                ItIsMeConfig.CHANGE_ATTACKABLE_SHADOW_OUTLINE_COLOR, ItIsMeConfig.CHANGE_ATTACKABLE_SHADOW_OUTLINE_COLOR::set,
                ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_RED,          ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_GREEN,        ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_BLUE,         ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_ALPHA,        ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_ALPHA::set);

        OptionGroup neutralShadowOutlineGroup = createShadowOutlineGroup("neutral_shadow_outline",
                ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.NEUTRAL_SHADOW_RADIUS_EXT,         ItIsMeConfig.NEUTRAL_SHADOW_RADIUS_EXT::set,
                ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RED,        ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_GREEN,      ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_BLUE,       ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_ALPHA,      ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_ALPHA::set);

        OptionGroup hostileShadowOutlineGroup = createShadowOutlineGroup("hostile_shadow_outline",
                ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.HOSTILE_SHADOW_RADIUS_EXT,         ItIsMeConfig.HOSTILE_SHADOW_RADIUS_EXT::set,
                ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RED,        ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_GREEN,      ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_BLUE,       ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_ALPHA,      ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_ALPHA::set);

        OptionGroup entityShadowOutlineGroup = createShadowOutlineGroup("entity_shadow_outline",
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RENDERABLE, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RENDERABLE::set,
                ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT,         ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED,        ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN,      ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE,       ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE::set,
                ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA,      ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA::set);

        OptionPage entityShadowOutlinePage = new OptionPage(
                OptionIdentifier.create(ItIsMe.MOD_ID, "entity_shadow_outline_page"),
                Component.translatable("config.itisme.entity_shadow_outline_page"),
                ImmutableList.of(playerShadowOutlineGroup, bossShadowOutlineGroup, attackableShadowOutlineGroup,
                        neutralShadowOutlineGroup, hostileShadowOutlineGroup, entityShadowOutlineGroup));

        event.addPage(entityShadowOutlinePage);

        Collator collator = Collator.getInstance(Minecraft.getInstance().getLanguageManager().getJavaLocale());
        collator.setStrength(Collator.PRIMARY);

        List<Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>>> entities = ForgeRegistries.ENTITY_TYPES
                .getEntries().stream()
                .sorted(Comparator.comparing(e -> e.getValue().getDescription().getString(), collator))
                .toList();

        ImmutableList.Builder<OptionGroup> customEntityOutlineGroups       = new ImmutableList.Builder<>();
        ImmutableList.Builder<OptionGroup> customEntityShadowOutlineGroups = new ImmutableList.Builder<>();

        for (Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>> entry : entities) {
            ResourceLocation id = entry.getKey().location();
            EntityType<?> type = entry.getValue();
            if (type.getCategory().equals(MobCategory.MISC)) continue;
            String parsable = id.toString().replace(":", "");

            customEntityOutlineGroups.add(createCustomEntityOutlineGroup(
                    parsable, type, "",
                    ItIsMeConfig.ENTITY_COLOR_MAP, ItIsMeConfig::saveCustomEntityOutline,
                    null, null,
                    ItIsMeConfig.ENTITY_OUTLINE_RED,   ItIsMeConfig.ENTITY_OUTLINE_GREEN,
                    ItIsMeConfig.ENTITY_OUTLINE_BLUE,  ItIsMeConfig.ENTITY_OUTLINE_ALPHA));

            customEntityShadowOutlineGroups.add(createCustomEntityOutlineGroup(
                    parsable, type, "shadow",
                    ItIsMeConfig.ENTITY_SHADOW_COLOR_MAP, ItIsMeConfig::saveCustomEntityShadowOutline,
                    ItIsMeConfig.ENTITY_SHADOW_RADIUS_MAP, ItIsMeConfig::saveCustomEntityShadowRadius,
                    ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED,   ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN,
                    ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE,  ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA));
        }

        addPagesPaginated(event, "custom_entity_outline_page", Component.translatable("config.itisme.custom_entity_outline_page"), customEntityOutlineGroups.build());
        addPagesPaginated(event, "custom_entity_shadow_outline_page", Component.translatable("config.itisme.custom_entity_shadow_outline_page"), customEntityShadowOutlineGroups.build());
    }

    private static void addPagesPaginated(OptionGUIConstructionEvent event, String basePageId, Component baseTitle, @NotNull List<OptionGroup> groups) {
        int total     = groups.size();
        int pageCount = (total + MAX_GROUPS_PER_PAGE - 1) / MAX_GROUPS_PER_PAGE;

        for (int i = 0; i < pageCount; i++) {
            int from = i * MAX_GROUPS_PER_PAGE;
            int to   = Math.min(from + MAX_GROUPS_PER_PAGE, total);

            Component title = pageCount > 1 ? baseTitle.copy().append(Component.literal(" (" + (i + 1) + "/" + pageCount + ")")) : baseTitle;

            OptionPage page = new OptionPage(
                    OptionIdentifier.create(ItIsMe.MOD_ID, basePageId + (pageCount > 1 ? "_" + (i + 1) : "")),
                    title,
                    ImmutableList.copyOf(groups.subList(from, to)));

            event.addPage(page);
        }
    }

    private static OptionImpl<SodiumGameOptions, Boolean> createBooleanOption(
            String key, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return OptionImpl.createBuilder(Boolean.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, key))
                .setName(Component.translatable("config.itisme." + key + ".name"))
                .setTooltip(EMPTY)
                .setControl(TickBoxControl::new)
                .setBinding((options, value) -> setter.accept(value), options -> getter.get())
                .setImpact(OptionImpact.LOW)
                .build();
    }

    private static OptionImpl<SodiumGameOptions, Integer> createColorOption(
            String key, Supplier<Integer> getter, Consumer<Integer> setter) {
        return OptionImpl.createBuilder(Integer.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, key))
                .setName(Component.translatable("config.itisme." + key + ".name"))
                .setTooltip(EMPTY)
                .setControl(option -> new SliderControl(option, 0, 255, 5, ControlValueFormatter.number()))
                .setBinding((options, value) -> setter.accept(value), options -> getter.get())
                .setImpact(OptionImpact.LOW)
                .build();
    }

    private static OptionImpl<SodiumGameOptions, Integer> createIntOption(
            String key, int min, int max, int step,
            Supplier<Integer> getter, Consumer<Integer> setter) {
        return OptionImpl.createBuilder(Integer.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, key))
                .setName(Component.translatable("config.itisme." + key + ".name"))
                .setTooltip(EMPTY)
                .setControl(option -> new SliderControl(option, min, max, step, ControlValueFormatter.number()))
                .setBinding((options, value) -> setter.accept(value), options -> getter.get())
                .setImpact(OptionImpact.LOW)
                .build();
    }

    private static OptionImpl<SodiumGameOptions, Integer> createDoubleAsIntOption(
            String key, int min, int max, int step,
            Supplier<Double> getter, Consumer<Double> setter) {
        return OptionImpl.createBuilder(Integer.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, key))
                .setName(Component.translatable("config.itisme." + key + ".name"))
                .setTooltip(EMPTY)
                .setControl(option -> new SliderControl(option, min, max, step, ControlValueFormatter.number()))
                .setBinding(
                        (options, value) -> setter.accept(value / 100.0),
                        options -> (int) Math.round(getter.get() * 100))
                .setImpact(OptionImpact.LOW)
                .build();
    }

    private static OptionGroup createOutlineGroup(
            String prefix,
            Supplier<Boolean> renderableGetter, Consumer<Boolean> renderableSetter,
            Supplier<Integer> rGetter, Consumer<Integer> rSetter,
            Supplier<Integer> gGetter, Consumer<Integer> gSetter,
            Supplier<Integer> bGetter, Consumer<Integer> bSetter,
            Supplier<Integer> aGetter, Consumer<Integer> aSetter) {
        return OptionGroup.createBuilder()
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, prefix + "_group"))
                .add(createBooleanOption(prefix + "_renderable", renderableGetter, renderableSetter))
                .add(createColorOption(prefix + "_red",   rGetter, rSetter))
                .add(createColorOption(prefix + "_green", gGetter, gSetter))
                .add(createColorOption(prefix + "_blue",  bGetter, bSetter))
                .add(createColorOption(prefix + "_alpha", aGetter, aSetter))
                .build();
    }

    private static OptionGroup createShadowOutlineGroup(
            String prefix,
            Supplier<Boolean> renderableGetter, Consumer<Boolean> renderableSetter,
            Supplier<Double>  radiusGetter, Consumer<Double>  radiusSetter,
            Supplier<Integer> rGetter, Consumer<Integer> rSetter,
            Supplier<Integer> gGetter, Consumer<Integer> gSetter,
            Supplier<Integer> bGetter, Consumer<Integer> bSetter,
            Supplier<Integer> aGetter, Consumer<Integer> aSetter) {
        return OptionGroup.createBuilder()
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, prefix + "_group"))
                .add(createBooleanOption(prefix + "_renderable", renderableGetter, renderableSetter))
                .add(createDoubleAsIntOption(prefix + "_radius_multiplier", 0, 1000, 5, radiusGetter, radiusSetter))
                .add(createColorOption(prefix + "_red",   rGetter, rSetter))
                .add(createColorOption(prefix + "_green", gGetter, gSetter))
                .add(createColorOption(prefix + "_blue",  bGetter, bSetter))
                .add(createColorOption(prefix + "_alpha", aGetter, aSetter))
                .build();
    }

    private static OptionGroup createCustomEntityOutlineGroup(
            String parsable, @NotNull EntityType<?> entityType, @NotNull String suffix,
            Map<ResourceLocation, OutlineColor> colorMap, Runnable saveColorAction,
            @Nullable Object2DoubleMap<ResourceLocation> radiusMap,
            @Nullable Runnable saveRadiusAction,
            Supplier<Integer> defaultR, Supplier<Integer> defaultG,
            Supplier<Integer> defaultB, Supplier<Integer> defaultA) {

        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, parsable);
        String s = suffix.isEmpty() ? "" : "_" + suffix;

        OptionImpl<SodiumGameOptions, Boolean> enable = OptionImpl.createBuilder(Boolean.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, parsable + s + "_enable"))
                .setName(entityType.getDescription())
                .setTooltip(EMPTY)
                .setControl(TickBoxControl::new)
                .setBinding((options, value) -> {
                    if (value) {
                        colorMap.put(id, new OutlineColor(defaultR.get(), defaultG.get(), defaultB.get(), defaultA.get()));
                    } else {
                        colorMap.remove(id);
                    }
                    saveColorAction.run();
                }, options -> colorMap.containsKey(id))
                .setImpact(OptionImpact.LOW)
                .build();

        String transKey = suffix.isEmpty() ? "config.itisme.custom_entity_outline" : "config.itisme.custom_entity_" + suffix + "_outline";
        String entityName = entityType.getDescription().getString();

        OptionImpl<SodiumGameOptions, Integer> red   = createEntityColorSlider(
                parsable + s + "_red",   Component.translatable(transKey + ".red",   entityName),
                colorMap, id, c -> c.red,   (c, v) -> c.red   = v, saveColorAction);
        OptionImpl<SodiumGameOptions, Integer> green = createEntityColorSlider(
                parsable + s + "_green", Component.translatable(transKey + ".green", entityName),
                colorMap, id, c -> c.green, (c, v) -> c.green = v, saveColorAction);
        OptionImpl<SodiumGameOptions, Integer> blue  = createEntityColorSlider(
                parsable + s + "_blue",  Component.translatable(transKey + ".blue",  entityName),
                colorMap, id, c -> c.blue,  (c, v) -> c.blue  = v, saveColorAction);
        OptionImpl<SodiumGameOptions, Integer> alpha = createEntityColorSlider(
                parsable + s + "_alpha", Component.translatable(transKey + ".alpha", entityName),
                colorMap, id, c -> c.alpha, (c, v) -> c.alpha = v, saveColorAction);

        OptionGroup.Builder groupBuilder = OptionGroup.createBuilder()
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, parsable + s + "_group"))
                .add(enable).add(red).add(green).add(blue).add(alpha);

        if (radiusMap != null && saveRadiusAction != null) {
            OptionImpl<SodiumGameOptions, Boolean> enableRadius = OptionImpl.createBuilder(Boolean.TYPE, sodiumOpts)
                    .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, parsable + s + "_radius_enable"))
                    .setName(Component.translatable("config.itisme.custom_entity_shadow_outline.radius_enable", entityName))
                    .setTooltip(EMPTY)
                    .setControl(TickBoxControl::new)
                    .setBinding((options, value) -> {
                        if (value) {
                            radiusMap.put(id, ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT.get().doubleValue());
                        } else {
                            radiusMap.removeDouble(id);
                        }
                        saveRadiusAction.run();
                    }, options -> radiusMap.containsKey(id))
                    .setImpact(OptionImpact.LOW)
                    .build();

            OptionImpl<SodiumGameOptions, Integer> radiusSlider = OptionImpl.createBuilder(Integer.TYPE, sodiumOpts)
                    .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, parsable + s + "_radius"))
                    .setName(Component.translatable("config.itisme.custom_entity_shadow_outline.radius", entityName))
                    .setTooltip(EMPTY)
                    .setControl(option -> new SliderControl(option, 0, 1000, 5, ControlValueFormatter.number()))
                    .setBinding(
                            (options, value) -> {
                                radiusMap.put(id, value / 100.0);
                                saveRadiusAction.run();
                            },
                            options -> (int) Math.round(
                                    radiusMap.containsKey(id)
                                            ? radiusMap.getDouble(id) * 100
                                            : ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT.get() * 100))
                    .setImpact(OptionImpact.LOW)
                    .build();

            groupBuilder.add(enableRadius).add(radiusSlider);
        }

        return groupBuilder.build();
    }

    private static OptionImpl<SodiumGameOptions, Integer> createEntityColorSlider(
            String id, Component name,
            Map<ResourceLocation, OutlineColor> colorMap, ResourceLocation entityId,
            Function<OutlineColor, Integer> getter, BiConsumer<OutlineColor, Integer> setter,
            Runnable saveAction) {
        return OptionImpl.createBuilder(Integer.TYPE, sodiumOpts)
                .setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, id))
                .setName(name)
                .setTooltip(EMPTY)
                .setControl(option -> new SliderControl(option, 0, 255, 5, ControlValueFormatter.number()))
                .setBinding(
                        (options, value) -> {
                            setter.accept(colorMap.getOrDefault(entityId, OutlineColor.getPlaceholder()), value);
                            saveAction.run();
                        },
                        options -> getter.apply(colorMap.getOrDefault(entityId, OutlineColor.getPlaceholder())))
                .setImpact(OptionImpact.LOW)
                .build();
    }
}