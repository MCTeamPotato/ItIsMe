package me.kall.itisme.config;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ConfigComponents {
    public static final Component GENERAL_PAGE = Component.translatable("config.itisme.general_page");
    public static final Component OUTLINE_RENDERABLE_DIST = Component.translatable("config.itisme.outline_renderable_dist.name");

    public static final class Outline {
        public static final Component ENTITY_OUTLINE_PAGE = Component.translatable("config.itisme.entity_outline_page");

        public static final class Attackable {
            public static final Component RENDERABLE = Component.translatable("config.itisme.attackable_outline_renderable.name");
            public static final Component RED =        Component.translatable("config.itisme.attackable_outline_red.name");
            public static final Component GREEN =      Component.translatable("config.itisme.attackable_outline_green.name");
            public static final Component BLUE =       Component.translatable("config.itisme.attackable_outline_blue.name");
            public static final Component ALPHA =      Component.translatable("config.itisme.attackable_outline_alpha.name");
        }

        public static final class Player {
            public static final Component RENDERABLE = Component.translatable("config.itisme.player_outline_renderable.name");
            public static final Component RED =        Component.translatable("config.itisme.player_outline_red.name");
            public static final Component GREEN =      Component.translatable("config.itisme.player_outline_green.name");
            public static final Component BLUE =       Component.translatable("config.itisme.player_outline_blue.name");
            public static final Component ALPHA =      Component.translatable("config.itisme.player_outline_alpha.name");
        }

        public static final class Boss {
            public static final Component RENDERABLE = Component.translatable("config.itisme.boss_outline_renderable.name");
            public static final Component RED =        Component.translatable("config.itisme.boss_outline_red.name");
            public static final Component GREEN =      Component.translatable("config.itisme.boss_outline_green.name");
            public static final Component BLUE =       Component.translatable("config.itisme.boss_outline_blue.name");
            public static final Component ALPHA =      Component.translatable("config.itisme.boss_outline_alpha.name");
        }

        public static final class Neutral {
            public static final Component RENDERABLE = Component.translatable("config.itisme.neutral_outline_renderable.name");
            public static final Component RED =        Component.translatable("config.itisme.neutral_outline_red.name");
            public static final Component GREEN =      Component.translatable("config.itisme.neutral_outline_green.name");
            public static final Component BLUE =       Component.translatable("config.itisme.neutral_outline_blue.name");
            public static final Component ALPHA =      Component.translatable("config.itisme.neutral_outline_alpha.name");
        }

        public static final class Hostile {
            public static final Component RENDERABLE = Component.translatable("config.itisme.hostile_outline_renderable.name");
            public static final Component RED =        Component.translatable("config.itisme.hostile_outline_red.name");
            public static final Component GREEN =      Component.translatable("config.itisme.hostile_outline_green.name");
            public static final Component BLUE =       Component.translatable("config.itisme.hostile_outline_blue.name");
            public static final Component ALPHA =      Component.translatable("config.itisme.hostile_outline_alpha.name");
        }

        public static final class Passive {
            public static final Component RENDERABLE = Component.translatable("config.itisme.entity_outline_renderable.name");
            public static final Component RED =        Component.translatable("config.itisme.entity_outline_red.name");
            public static final Component GREEN =      Component.translatable("config.itisme.entity_outline_green.name");
            public static final Component BLUE =       Component.translatable("config.itisme.entity_outline_blue.name");
            public static final Component ALPHA =      Component.translatable("config.itisme.entity_outline_alpha.name");
        }

        public static final class Custom {
            public static final Component CUSTOM_ENTITY_OUTLINE_PAGE = Component.translatable("config.itisme.custom_entity_outline_page");

            @Contract("_ -> new")
            public static @NotNull Component red(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_outline.red", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component green(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_outline.green", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component blue(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_outline.blue", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component alpha(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_outline.alpha", entityType.getDescription().getString());
            }
        }
    }

    public static final class ShadowOutline {
        public static final Component ENTITY_SHADOW_OUTLINE_PAGE = Component.translatable("config.itisme.entity_shadow_outline_page");

        public static final class Attackable {
            public static final Component RENDERABLE = Component.translatable("config.itisme.attackable_shadow_outline_renderable.name");
            public static final Component RED =        Component.translatable("config.itisme.attackable_shadow_outline_red.name");
            public static final Component GREEN =      Component.translatable("config.itisme.attackable_shadow_outline_green.name");
            public static final Component BLUE =       Component.translatable("config.itisme.attackable_shadow_outline_blue.name");
            public static final Component ALPHA =      Component.translatable("config.itisme.attackable_shadow_outline_alpha.name");
        }

        public static final class Player {
            public static final Component RENDERABLE =        Component.translatable("config.itisme.player_shadow_outline_renderable.name");
            public static final Component RADIUS_MULTIPLIER = Component.translatable("config.itisme.player_shadow_outline_radius_multiplier.name");
            public static final Component RED =               Component.translatable("config.itisme.player_shadow_outline_red.name");
            public static final Component GREEN =             Component.translatable("config.itisme.player_shadow_outline_green.name");
            public static final Component BLUE =              Component.translatable("config.itisme.player_shadow_outline_blue.name");
            public static final Component ALPHA =             Component.translatable("config.itisme.player_shadow_outline_alpha.name");
        }

        public static final class Boss {
            public static final Component RENDERABLE =        Component.translatable("config.itisme.boss_shadow_outline_renderable.name");
            public static final Component RADIUS_MULTIPLIER = Component.translatable("config.itisme.boss_shadow_outline_radius_multiplier.name");
            public static final Component RED =               Component.translatable("config.itisme.boss_shadow_outline_red.name");
            public static final Component GREEN =             Component.translatable("config.itisme.boss_shadow_outline_green.name");
            public static final Component BLUE =              Component.translatable("config.itisme.boss_shadow_outline_blue.name");
            public static final Component ALPHA =             Component.translatable("config.itisme.boss_shadow_outline_alpha.name");
        }

        public static final class Neutral {
            public static final Component RENDERABLE =        Component.translatable("config.itisme.neutral_shadow_outline_renderable.name");
            public static final Component RADIUS_MULTIPLIER = Component.translatable("config.itisme.neutral_shadow_outline_radius_multiplier.name");
            public static final Component RED =               Component.translatable("config.itisme.neutral_shadow_outline_red.name");
            public static final Component GREEN =             Component.translatable("config.itisme.neutral_shadow_outline_green.name");
            public static final Component BLUE =              Component.translatable("config.itisme.neutral_shadow_outline_blue.name");
            public static final Component ALPHA =             Component.translatable("config.itisme.neutral_shadow_outline_alpha.name");
        }

        public static final class Hostile {
            public static final Component RENDERABLE =        Component.translatable("config.itisme.hostile_shadow_outline_renderable.name");
            public static final Component RADIUS_MULTIPLIER = Component.translatable("config.itisme.hostile_shadow_outline_radius_multiplier.name");
            public static final Component RED =               Component.translatable("config.itisme.hostile_shadow_outline_red.name");
            public static final Component GREEN =             Component.translatable("config.itisme.hostile_shadow_outline_green.name");
            public static final Component BLUE =              Component.translatable("config.itisme.hostile_shadow_outline_blue.name");
            public static final Component ALPHA =             Component.translatable("config.itisme.hostile_shadow_outline_alpha.name");
        }

        public static final class Passive {
            public static final Component RENDERABLE =        Component.translatable("config.itisme.entity_shadow_outline_renderable.name");
            public static final Component RADIUS_MULTIPLIER = Component.translatable("config.itisme.entity_shadow_outline_radius_multiplier.name");
            public static final Component RED =               Component.translatable("config.itisme.entity_shadow_outline_red.name");
            public static final Component GREEN =             Component.translatable("config.itisme.entity_shadow_outline_green.name");
            public static final Component BLUE =              Component.translatable("config.itisme.entity_shadow_outline_blue.name");
            public static final Component ALPHA =             Component.translatable("config.itisme.entity_shadow_outline_alpha.name");
        }

        public static final class Custom {
            public static final Component CUSTOM_ENTITY_SHADOW_OUTLINE_PAGE = Component.translatable("config.itisme.custom_entity_shadow_outline_page");

            @Contract("_ -> new")
            public static @NotNull Component red(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_shadow_outline.red", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component green(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_shadow_outline.green", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component blue(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_shadow_outline.blue", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component alpha(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_shadow_outline.alpha", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component customRadius(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_shadow_outline.radius_enable", entityType.getDescription().getString());
            }

            @Contract("_ -> new")
            public static @NotNull Component radiusMultiplier(@NotNull EntityType<?> entityType) {
                return Component.translatable("config.itisme.custom_entity_shadow_outline.radius", entityType.getDescription().getString());
            }
        }
    }
}