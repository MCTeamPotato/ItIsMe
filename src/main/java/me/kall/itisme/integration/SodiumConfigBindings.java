package me.kall.itisme.integration;

import me.jellysquid.mods.sodium.client.gui.SodiumGameOptions;
import me.kall.itisme.config.ItIsMeConfig;

import java.util.function.BiConsumer;
import java.util.function.Function;

public final class SodiumConfigBindings {
    public static final Function<SodiumGameOptions, Integer> OUTLINE_RENDERABLE_DIST_GETTER = options -> ItIsMeConfig.OUTLINE_RENDERABLE_DIST.get();
    public static final BiConsumer<SodiumGameOptions, Integer> OUTLINE_RENDERABLE_DIST_SETTER = (options, value) -> ItIsMeConfig.OUTLINE_RENDERABLE_DIST.set(value);

    public static final Function<SodiumGameOptions, Boolean> PLAYER_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.PLAYER_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> PLAYER_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.PLAYER_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> PLAYER_OUTLINE_RED_GETTER = options -> ItIsMeConfig.PLAYER_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.PLAYER_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> PLAYER_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.PLAYER_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.PLAYER_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> PLAYER_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.PLAYER_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.PLAYER_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> PLAYER_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.PLAYER_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.PLAYER_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> BOSS_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.BOSS_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> BOSS_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.BOSS_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> BOSS_OUTLINE_RED_GETTER = options -> ItIsMeConfig.BOSS_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.BOSS_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> BOSS_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.BOSS_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.BOSS_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> BOSS_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.BOSS_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.BOSS_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> BOSS_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.BOSS_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.BOSS_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> ATTACKABLE_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.CHANGE_ATTACKABLE_OUTLINE_COLOR.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> ATTACKABLE_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.CHANGE_ATTACKABLE_OUTLINE_COLOR.set(value);

    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_RED_GETTER = options -> ItIsMeConfig.ATTACKABLE_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.ATTACKABLE_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.ATTACKABLE_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.ATTACKABLE_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> NEUTRAL_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.NEUTRAL_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> NEUTRAL_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_RED_GETTER = options -> ItIsMeConfig.NEUTRAL_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.NEUTRAL_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.NEUTRAL_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.NEUTRAL_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> HOSTILE_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.HOSTILE_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> HOSTILE_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> HOSTILE_OUTLINE_RED_GETTER = options -> ItIsMeConfig.HOSTILE_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> HOSTILE_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.HOSTILE_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> HOSTILE_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.HOSTILE_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> HOSTILE_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.HOSTILE_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> ENTITY_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.ENTITY_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> ENTITY_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.ENTITY_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> ENTITY_OUTLINE_RED_GETTER = options -> ItIsMeConfig.ENTITY_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.ENTITY_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> ENTITY_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.ENTITY_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.ENTITY_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> ENTITY_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.ENTITY_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.ENTITY_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> ENTITY_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.ENTITY_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.ENTITY_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> PLAYER_SHADOW_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> PLAYER_SHADOW_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> PLAYER_SHADOW_RADIUS_MULTIPLIER_GETTER = options -> (int) Math.round(ItIsMeConfig.PLAYER_SHADOW_RADIUS_EXT.get() * 100);
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_SHADOW_RADIUS_MULTIPLIER_SETTER = (options, value) -> ItIsMeConfig.PLAYER_SHADOW_RADIUS_EXT.set(value / 100.0);

    public static final Function<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_RED_GETTER = options -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> PLAYER_SHADOW_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.PLAYER_SHADOW_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> BOSS_SHADOW_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> BOSS_SHADOW_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> BOSS_SHADOW_RADIUS_MULTIPLIER_GETTER = options -> (int) Math.round(ItIsMeConfig.BOSS_SHADOW_RADIUS_EXT.get() * 100);
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_SHADOW_RADIUS_MULTIPLIER_SETTER = (options, value) -> ItIsMeConfig.BOSS_SHADOW_RADIUS_EXT.set(value / 100.0);

    public static final Function<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_RED_GETTER = options -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> BOSS_SHADOW_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.BOSS_SHADOW_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> ATTACKABLE_SHADOW_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.CHANGE_ATTACKABLE_SHADOW_OUTLINE_COLOR.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> ATTACKABLE_SHADOW_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.CHANGE_ATTACKABLE_SHADOW_OUTLINE_COLOR.set(value);

    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_RED_GETTER = options -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ATTACKABLE_SHADOW_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.ATTACKABLE_SHADOW_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> NEUTRAL_SHADOW_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> NEUTRAL_SHADOW_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> NEUTRAL_SHADOW_RADIUS_MULTIPLIER_GETTER = options -> (int) Math.round(ItIsMeConfig.NEUTRAL_SHADOW_RADIUS_EXT.get() * 100);
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_SHADOW_RADIUS_MULTIPLIER_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_SHADOW_RADIUS_EXT.set(value / 100.0);

    public static final Function<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_RED_GETTER = options -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> NEUTRAL_SHADOW_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.NEUTRAL_SHADOW_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> HOSTILE_SHADOW_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> HOSTILE_SHADOW_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> HOSTILE_SHADOW_RADIUS_MULTIPLIER_GETTER = options -> (int) Math.round(ItIsMeConfig.HOSTILE_SHADOW_RADIUS_EXT.get() * 100);
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_SHADOW_RADIUS_MULTIPLIER_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_SHADOW_RADIUS_EXT.set(value / 100.0);

    public static final Function<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_RED_GETTER = options -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> HOSTILE_SHADOW_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.HOSTILE_SHADOW_OUTLINE_ALPHA.set(value);

    public static final Function<SodiumGameOptions, Boolean> ENTITY_SHADOW_OUTLINE_RENDERABLE_GETTER = options -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RENDERABLE.get();
    public static final BiConsumer<SodiumGameOptions, Boolean> ENTITY_SHADOW_OUTLINE_RENDERABLE_SETTER = (options, value) -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RENDERABLE.set(value);

    public static final Function<SodiumGameOptions, Integer> ENTITY_SHADOW_RADIUS_MULTIPLIER_GETTER = options -> (int) Math.round(ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT.get() * 100);
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_SHADOW_RADIUS_MULTIPLIER_SETTER = (options, value) -> ItIsMeConfig.ENTITY_SHADOW_RADIUS_EXT.set(value / 100.0);

    public static final Function<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_RED_GETTER = options -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_RED_SETTER = (options, value) -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_RED.set(value);
    public static final Function<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_GREEN_GETTER = options -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_GREEN_SETTER = (options, value) -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_GREEN.set(value);
    public static final Function<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_BLUE_GETTER = options -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_BLUE_SETTER = (options, value) -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_BLUE.set(value);
    public static final Function<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_ALPHA_GETTER = options -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA.get();
    public static final BiConsumer<SodiumGameOptions, Integer> ENTITY_SHADOW_OUTLINE_ALPHA_SETTER = (options, value) -> ItIsMeConfig.ENTITY_SHADOW_OUTLINE_ALPHA.set(value);
}