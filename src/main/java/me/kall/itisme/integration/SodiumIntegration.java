package me.kall.itisme.integration;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import me.kall.itisme.ItIsMe;
import me.kall.itisme.data.OutlineColor;
import me.kall.itisme.config.ItIsMeConfig;
import me.kall.itisme.util.SodiumConfigBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.common.NeoForge;
import org.embeddedt.embeddium.api.OptionGUIConstructionEvent;
import org.embeddedt.embeddium.api.options.OptionIdentifier;
import org.embeddedt.embeddium.api.options.structure.OptionGroup;
import org.embeddedt.embeddium.api.options.structure.OptionImpact;
import org.embeddedt.embeddium.api.options.structure.OptionImpl;
import org.embeddedt.embeddium.api.options.structure.OptionPage;
import org.embeddedt.embeddium.impl.gui.EmbeddiumOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class SodiumIntegration {
    private static final int MAX_GROUPS_PER_PAGE = 30;

    private static final Component EMPTY = Component.empty();

    public static void register() {
        NeoForge.EVENT_BUS.addListener(SodiumIntegration::registerConfig);
    }

    private static void registerConfig(@NotNull OptionGUIConstructionEvent event) {
        OptionGroup generalGroup = SodiumConfigBuilder.buildGroup("general_group", SodiumConfigBuilder.intOption("outline_renderable_dist", ConfigComponents.OUTLINE_RENDERABLE_DIST, EMPTY, 0, 512, 1, SodiumConfigBindings.OUTLINE_RENDERABLE_DIST_SETTER, SodiumConfigBindings.OUTLINE_RENDERABLE_DIST_GETTER, OptionImpact.LOW));

        OptionPage generalPage = new OptionPage(OptionIdentifier.create(ItIsMe.MOD_ID, "general_page"), ConfigComponents.GENERAL_PAGE, ImmutableList.of(generalGroup));
        event.addPage(generalPage);

        OptionGroup playerOutlineGroup = SodiumConfigBuilder.buildGroup(
                "player_outline_group",
                SodiumConfigBuilder.booleanOption("player_outline_renderable", ConfigComponents.Outline.Player.RENDERABLE, EMPTY, SodiumConfigBindings.PLAYER_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.PLAYER_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_outline_red", ConfigComponents.Outline.Player.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_OUTLINE_RED_SETTER, SodiumConfigBindings.PLAYER_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_outline_green", ConfigComponents.Outline.Player.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_OUTLINE_GREEN_SETTER, SodiumConfigBindings.PLAYER_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_outline_blue", ConfigComponents.Outline.Player.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_OUTLINE_BLUE_SETTER, SodiumConfigBindings.PLAYER_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_outline_alpha", ConfigComponents.Outline.Player.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.PLAYER_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup bossOutlineGroup = SodiumConfigBuilder.buildGroup(
                "boss_outline_group",
                SodiumConfigBuilder.booleanOption("boss_outline_renderable", ConfigComponents.Outline.Boss.RENDERABLE, EMPTY, SodiumConfigBindings.BOSS_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.BOSS_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_outline_red", ConfigComponents.Outline.Boss.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_OUTLINE_RED_SETTER, SodiumConfigBindings.BOSS_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_outline_green", ConfigComponents.Outline.Boss.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_OUTLINE_GREEN_SETTER, SodiumConfigBindings.BOSS_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_outline_blue", ConfigComponents.Outline.Boss.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_OUTLINE_BLUE_SETTER, SodiumConfigBindings.BOSS_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_outline_alpha", ConfigComponents.Outline.Boss.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.BOSS_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup attackableOutlineGroup = SodiumConfigBuilder.buildGroup(
                "attackable_outline_group",
                SodiumConfigBuilder.booleanOption("attackable_outline_renderable", ConfigComponents.Outline.Attackable.RENDERABLE, EMPTY, SodiumConfigBindings.ATTACKABLE_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.ATTACKABLE_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_outline_red", ConfigComponents.Outline.Attackable.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_OUTLINE_RED_SETTER, SodiumConfigBindings.ATTACKABLE_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_outline_green", ConfigComponents.Outline.Attackable.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_OUTLINE_GREEN_SETTER, SodiumConfigBindings.ATTACKABLE_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_outline_blue", ConfigComponents.Outline.Attackable.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_OUTLINE_BLUE_SETTER, SodiumConfigBindings.ATTACKABLE_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_outline_alpha", ConfigComponents.Outline.Attackable.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.ATTACKABLE_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup neutralOutlineGroup = SodiumConfigBuilder.buildGroup(
                "neutral_outline_group",
                SodiumConfigBuilder.booleanOption("neutral_outline_renderable", ConfigComponents.Outline.Neutral.RENDERABLE, EMPTY, SodiumConfigBindings.NEUTRAL_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.NEUTRAL_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_outline_red", ConfigComponents.Outline.Neutral.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_OUTLINE_RED_SETTER, SodiumConfigBindings.NEUTRAL_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_outline_green", ConfigComponents.Outline.Neutral.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_OUTLINE_GREEN_SETTER, SodiumConfigBindings.NEUTRAL_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_outline_blue", ConfigComponents.Outline.Neutral.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_OUTLINE_BLUE_SETTER, SodiumConfigBindings.NEUTRAL_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_outline_alpha", ConfigComponents.Outline.Neutral.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.NEUTRAL_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup hostileOutlineGroup = SodiumConfigBuilder.buildGroup(
                "hostile_outline_group",
                SodiumConfigBuilder.booleanOption("hostile_outline_renderable", ConfigComponents.Outline.Hostile.RENDERABLE, EMPTY, SodiumConfigBindings.HOSTILE_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.HOSTILE_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_outline_red", ConfigComponents.Outline.Hostile.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_OUTLINE_RED_SETTER, SodiumConfigBindings.HOSTILE_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_outline_green", ConfigComponents.Outline.Hostile.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_OUTLINE_GREEN_SETTER, SodiumConfigBindings.HOSTILE_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_outline_blue", ConfigComponents.Outline.Hostile.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_OUTLINE_BLUE_SETTER, SodiumConfigBindings.HOSTILE_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_outline_alpha", ConfigComponents.Outline.Hostile.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.HOSTILE_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup entityOutlineGroup = SodiumConfigBuilder.buildGroup(
                "entity_outline_group",
                SodiumConfigBuilder.booleanOption("entity_outline_renderable", ConfigComponents.Outline.Passive.RENDERABLE, EMPTY, SodiumConfigBindings.ENTITY_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.ENTITY_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_outline_red", ConfigComponents.Outline.Passive.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_OUTLINE_RED_SETTER, SodiumConfigBindings.ENTITY_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_outline_green", ConfigComponents.Outline.Passive.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_OUTLINE_GREEN_SETTER, SodiumConfigBindings.ENTITY_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_outline_blue", ConfigComponents.Outline.Passive.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_OUTLINE_BLUE_SETTER, SodiumConfigBindings.ENTITY_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_outline_alpha", ConfigComponents.Outline.Passive.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.ENTITY_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionIdentifier<Void> entityOutlinePageID = OptionIdentifier.create(ItIsMe.MOD_ID, "entity_outline_page");
        OptionPage entityOutlinePage = new OptionPage(entityOutlinePageID, ConfigComponents.Outline.ENTITY_OUTLINE_PAGE, ImmutableList.of(playerOutlineGroup, bossOutlineGroup, attackableOutlineGroup, neutralOutlineGroup, hostileOutlineGroup, entityOutlineGroup));
        event.addPage(entityOutlinePage);

        OptionGroup playerShadowOutlineGroup = SodiumConfigBuilder.buildGroup(
                "player_shadow_outline_group",
                SodiumConfigBuilder.booleanOption("player_shadow_outline_renderable", ConfigComponents.ShadowOutline.Player.RENDERABLE, EMPTY, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_shadow_outline_radius_multiplier", ConfigComponents.ShadowOutline.Player.RADIUS_MULTIPLIER, EMPTY, 0, 400, 5, SodiumConfigBindings.PLAYER_SHADOW_RADIUS_MULTIPLIER_SETTER, SodiumConfigBindings.PLAYER_SHADOW_RADIUS_MULTIPLIER_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_shadow_outline_red", ConfigComponents.ShadowOutline.Player.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_RED_SETTER, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_shadow_outline_green", ConfigComponents.ShadowOutline.Player.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_GREEN_SETTER, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_shadow_outline_blue", ConfigComponents.ShadowOutline.Player.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_BLUE_SETTER, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("player_shadow_outline_alpha", ConfigComponents.ShadowOutline.Player.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.PLAYER_SHADOW_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup bossShadowOutlineGroup = SodiumConfigBuilder.buildGroup(
                "boss_shadow_outline_group",
                SodiumConfigBuilder.booleanOption("boss_shadow_outline_renderable", ConfigComponents.ShadowOutline.Boss.RENDERABLE, EMPTY, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_shadow_outline_radius_multiplier", ConfigComponents.ShadowOutline.Boss.RADIUS_MULTIPLIER, EMPTY, 0, 400, 5, SodiumConfigBindings.BOSS_SHADOW_RADIUS_MULTIPLIER_SETTER, SodiumConfigBindings.BOSS_SHADOW_RADIUS_MULTIPLIER_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_shadow_outline_red", ConfigComponents.ShadowOutline.Boss.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_RED_SETTER, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_shadow_outline_green", ConfigComponents.ShadowOutline.Boss.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_GREEN_SETTER, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_shadow_outline_blue", ConfigComponents.ShadowOutline.Boss.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_BLUE_SETTER, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("boss_shadow_outline_alpha", ConfigComponents.ShadowOutline.Boss.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.BOSS_SHADOW_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup attackableShadowOutlineGroup = SodiumConfigBuilder.buildGroup(
                "attackable_shadow_outline_group",
                SodiumConfigBuilder.booleanOption("attackable_shadow_outline_renderable", ConfigComponents.ShadowOutline.Attackable.RENDERABLE, EMPTY, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_shadow_outline_red", ConfigComponents.ShadowOutline.Attackable.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_RED_SETTER, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_shadow_outline_green", ConfigComponents.ShadowOutline.Attackable.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_GREEN_SETTER, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_shadow_outline_blue", ConfigComponents.ShadowOutline.Attackable.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_BLUE_SETTER, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("attackable_shadow_outline_alpha", ConfigComponents.ShadowOutline.Attackable.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.ATTACKABLE_SHADOW_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup neutralShadowOutlineGroup = SodiumConfigBuilder.buildGroup(
                "neutral_shadow_outline_group",
                SodiumConfigBuilder.booleanOption("neutral_shadow_outline_renderable", ConfigComponents.ShadowOutline.Neutral.RENDERABLE, EMPTY, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_shadow_outline_radius_multiplier", ConfigComponents.ShadowOutline.Neutral.RADIUS_MULTIPLIER, EMPTY, 0, 400, 5, SodiumConfigBindings.NEUTRAL_SHADOW_RADIUS_MULTIPLIER_SETTER, SodiumConfigBindings.NEUTRAL_SHADOW_RADIUS_MULTIPLIER_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_shadow_outline_red", ConfigComponents.ShadowOutline.Neutral.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_RED_SETTER, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_shadow_outline_green", ConfigComponents.ShadowOutline.Neutral.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_GREEN_SETTER, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_shadow_outline_blue", ConfigComponents.ShadowOutline.Neutral.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_BLUE_SETTER, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("neutral_shadow_outline_alpha", ConfigComponents.ShadowOutline.Neutral.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.NEUTRAL_SHADOW_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup hostileShadowOutlineGroup = SodiumConfigBuilder.buildGroup(
                "hostile_shadow_outline_group",
                SodiumConfigBuilder.booleanOption("hostile_shadow_outline_renderable", ConfigComponents.ShadowOutline.Hostile.RENDERABLE, EMPTY, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_shadow_outline_radius_multiplier", ConfigComponents.ShadowOutline.Hostile.RADIUS_MULTIPLIER, EMPTY, 0, 400, 5, SodiumConfigBindings.HOSTILE_SHADOW_RADIUS_MULTIPLIER_SETTER, SodiumConfigBindings.HOSTILE_SHADOW_RADIUS_MULTIPLIER_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_shadow_outline_red", ConfigComponents.ShadowOutline.Hostile.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_RED_SETTER, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_shadow_outline_green", ConfigComponents.ShadowOutline.Hostile.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_GREEN_SETTER, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_shadow_outline_blue", ConfigComponents.ShadowOutline.Hostile.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_BLUE_SETTER, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("hostile_shadow_outline_alpha", ConfigComponents.ShadowOutline.Hostile.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.HOSTILE_SHADOW_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionGroup entityShadowOutlineGroup = SodiumConfigBuilder.buildGroup(
                "entity_shadow_outline_group",
                SodiumConfigBuilder.booleanOption("entity_shadow_outline_renderable", ConfigComponents.ShadowOutline.Passive.RENDERABLE, EMPTY, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_RENDERABLE_SETTER, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_RENDERABLE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_shadow_outline_radius_multiplier", ConfigComponents.ShadowOutline.Passive.RADIUS_MULTIPLIER, EMPTY, 0, 400, 5, SodiumConfigBindings.ENTITY_SHADOW_RADIUS_MULTIPLIER_SETTER, SodiumConfigBindings.ENTITY_SHADOW_RADIUS_MULTIPLIER_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_shadow_outline_red", ConfigComponents.ShadowOutline.Passive.RED, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_RED_SETTER, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_RED_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_shadow_outline_green", ConfigComponents.ShadowOutline.Passive.GREEN, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_GREEN_SETTER, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_GREEN_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_shadow_outline_blue", ConfigComponents.ShadowOutline.Passive.BLUE, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_BLUE_SETTER, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_BLUE_GETTER, OptionImpact.LOW),
                SodiumConfigBuilder.intOption("entity_shadow_outline_alpha", ConfigComponents.ShadowOutline.Passive.ALPHA, EMPTY, 0, 255, 5, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_ALPHA_SETTER, SodiumConfigBindings.ENTITY_SHADOW_OUTLINE_ALPHA_GETTER, OptionImpact.LOW)
        );

        OptionPage entityShadowOutlinePage = new OptionPage(OptionIdentifier.create(ItIsMe.MOD_ID, "entity_shadow_outline_page"), ConfigComponents.ShadowOutline.ENTITY_SHADOW_OUTLINE_PAGE, ImmutableList.of(playerShadowOutlineGroup, bossShadowOutlineGroup, attackableShadowOutlineGroup, neutralShadowOutlineGroup, hostileShadowOutlineGroup, entityShadowOutlineGroup));
        event.addPage(entityShadowOutlinePage);

        Collator collator = Collator.getInstance(Minecraft.getInstance().getLanguageManager().getJavaLocale());
        collator.setStrength(Collator.PRIMARY);

        List<Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>>> entities = BuiltInRegistries.ENTITY_TYPE
                .entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue().getDescription().getString(), collator))
                .toList();

        ImmutableList.Builder<OptionGroup> customEntityOutlineGroups = new ImmutableList.Builder<>();
        ImmutableList.Builder<OptionGroup> customEntityShadowOutlineGroups = new ImmutableList.Builder<>();

        for (Map.Entry<ResourceKey<EntityType<?>>, EntityType<?>> entry : entities) {
            ResourceLocation id = entry.getKey().location();
            EntityType<?> type = entry.getValue();
            if (type.getCategory().equals(MobCategory.MISC)) continue;
            String parsable = id.toString().replace(":", "");

            customEntityOutlineGroups.add(createCustomEntityOutlineGroup(parsable, type, "", ItIsMeConfig.ENTITY_COLOR_MAP, ItIsMeConfig::saveCustomEntityOutline, null, null, ItIsMeConfig.ENTITY_OUTLINE_RED, ItIsMeConfig.ENTITY_OUTLINE_GREEN, ItIsMeConfig.ENTITY_OUTLINE_BLUE, ItIsMeConfig.ENTITY_OUTLINE_ALPHA));
            customEntityShadowOutlineGroups.add(createCustomEntityOutlineGroup(parsable, type, "shadow", ItIsMeConfig.ENTITY_SHADOW_COLOR_MAP, ItIsMeConfig::saveCustomEntityShadowOutline, ItIsMeConfig.ENTITY_SHADOW_RADIUS_MAP, ItIsMeConfig::saveCustomEntityShadowRadius, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE, ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA));
        }

        addPagesPaginated(event, "custom_entity_outline_page", ConfigComponents.Outline.Custom.CUSTOM_ENTITY_OUTLINE_PAGE, customEntityOutlineGroups.build());
        addPagesPaginated(event, "custom_entity_shadow_outline_page", ConfigComponents.ShadowOutline.Custom.CUSTOM_ENTITY_SHADOW_OUTLINE_PAGE, customEntityShadowOutlineGroups.build());
    }

    private static void addPagesPaginated(OptionGUIConstructionEvent event, String basePageId, Component baseTitle, @NotNull List<OptionGroup> groups) {
        int total = groups.size();
        int pageCount = (total + MAX_GROUPS_PER_PAGE - 1) / MAX_GROUPS_PER_PAGE;

        for (int pageIndex = 0; pageIndex < pageCount; pageIndex++) {
            int from = pageIndex * MAX_GROUPS_PER_PAGE;
            int to = Math.min(from + MAX_GROUPS_PER_PAGE, total);

            Component title = pageCount > 1 ? baseTitle.copy().append(Component.literal(" (" + (pageIndex + 1) + "/" + pageCount + ")")) : baseTitle;
            OptionPage page = new OptionPage(OptionIdentifier.create(ItIsMe.MOD_ID, basePageId + (pageCount > 1 ? "_" + (pageIndex + 1) : "")), title, ImmutableList.copyOf(groups.subList(from, to)));
            event.addPage(page);
        }
    }

    private static OptionGroup createCustomEntityOutlineGroup(String parsable, @NotNull EntityType<?> entityType, @NotNull String suffix, Map<ResourceLocation, OutlineColor> colorMap, Runnable saveColorAction, @Nullable Object2DoubleMap<ResourceLocation> radiusMap, @Nullable Runnable saveRadiusAction, Supplier<Integer> defaultR, Supplier<Integer> defaultG, Supplier<Integer> defaultB, Supplier<Integer> defaultA) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, parsable);
        String s = suffix.isEmpty() ? "" : "_" + suffix;

        Component redName, greenName, blueName, alphaName;
        if (suffix.isEmpty()) {
            redName = ConfigComponents.Outline.Custom.red(entityType);
            greenName = ConfigComponents.Outline.Custom.green(entityType);
            blueName = ConfigComponents.Outline.Custom.blue(entityType);
            alphaName = ConfigComponents.Outline.Custom.alpha(entityType);
        } else {
            redName = ConfigComponents.ShadowOutline.Custom.red(entityType);
            greenName = ConfigComponents.ShadowOutline.Custom.green(entityType);
            blueName = ConfigComponents.ShadowOutline.Custom.blue(entityType);
            alphaName = ConfigComponents.ShadowOutline.Custom.alpha(entityType);
        }

        OptionImpl<EmbeddiumOptions, Boolean> enable = SodiumConfigBuilder.booleanOption(parsable + s + "_enable", entityType.getDescription(), EMPTY,
                (options, value) -> {
                    if (value) {
                        colorMap.put(id, new OutlineColor(defaultR.get(), defaultG.get(), defaultB.get(), defaultA.get()));
                    } else {
                        colorMap.remove(id);
                    }
                    saveColorAction.run();
                }, options -> colorMap.containsKey(id), OptionImpact.LOW
        );

        OptionImpl<EmbeddiumOptions, Integer> red = createEntityColorSlider(parsable + s + "_red", redName, colorMap, id, color -> color.red, (color, value) -> color.red = value, saveColorAction);
        OptionImpl<EmbeddiumOptions, Integer> green = createEntityColorSlider(parsable + s + "_green", greenName, colorMap, id, color -> color.green, (color, value) -> color.green = value, saveColorAction);
        OptionImpl<EmbeddiumOptions, Integer> blue = createEntityColorSlider(parsable + s + "_blue", blueName, colorMap, id, color -> color.blue, (color, value) -> color.blue = value, saveColorAction);
        OptionImpl<EmbeddiumOptions, Integer> alpha = createEntityColorSlider(parsable + s + "_alpha", alphaName, colorMap, id, color -> color.alpha, (color, value) -> color.alpha = value, saveColorAction);

        OptionGroup.Builder groupBuilder = OptionGroup.createBuilder().setId(ResourceLocation.fromNamespaceAndPath(ItIsMe.MOD_ID, parsable + s + "_group")).add(enable).add(red).add(green).add(blue).add(alpha);

        if (radiusMap != null && saveRadiusAction != null) {
            OptionImpl<EmbeddiumOptions, Boolean> enableRadius = SodiumConfigBuilder.booleanOption(parsable + s + "_radius_enable", ConfigComponents.ShadowOutline.Custom.customRadius(entityType), EMPTY,
                    (options, value) -> {
                        if (value) {
                            radiusMap.put(id, ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT.get().doubleValue());
                        } else {
                            radiusMap.removeDouble(id);
                        }
                        saveRadiusAction.run();
                    }, options -> radiusMap.containsKey(id), OptionImpact.LOW
            );

            OptionImpl<EmbeddiumOptions, Integer> radiusSlider = SodiumConfigBuilder.intOption(parsable + s + "_radius", ConfigComponents.ShadowOutline.Custom.radiusMultiplier(entityType), EMPTY, 0, 400, 5,
                    (options, value) -> {
                        radiusMap.put(id, value / 100.0);
                        saveRadiusAction.run();
                    }, options -> (int) Math.round(radiusMap.containsKey(id) ? radiusMap.getDouble(id) * 100 : ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT.get() * 100), OptionImpact.LOW
            );

            groupBuilder.add(enableRadius).add(radiusSlider);
        }

        return groupBuilder.build();
    }

    private static OptionImpl<EmbeddiumOptions, Integer> createEntityColorSlider(String id, Component name, Map<ResourceLocation, OutlineColor> colorMap, ResourceLocation entityId, Function<OutlineColor, Integer> getter, BiConsumer<OutlineColor, Integer> setter, Runnable saveAction) {
        Function<EmbeddiumOptions, Integer> colorGetter = options -> getter.apply(colorMap.getOrDefault(entityId, OutlineColor.getPlaceholder()));
        BiConsumer<EmbeddiumOptions, Integer> colorSetter = (options, value) -> {
            colorMap.computeIfAbsent(entityId, key -> OutlineColor.getPlaceholder());
            setter.accept(colorMap.get(entityId), value);
            saveAction.run();
        };

        return SodiumConfigBuilder.intOption(id, name, EMPTY, 0, 255, 5, colorSetter, colorGetter, OptionImpact.LOW);
    }
}