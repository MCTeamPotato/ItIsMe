package me.kall.itisme;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import me.kall.duplicationless.ext.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber(modid = ItIsMe.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItIsMeConfig {
    public static final ForgeConfigSpec LIGHT_TWEAK_CONFIG;

    public static final ForgeConfigSpec.IntValue PLAYER_OUTLINE_RED, PLAYER_OUTLINE_GREEN, PLAYER_OUTLINE_BLUE, PLAYER_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue BOSS_OUTLINE_RED, BOSS_OUTLINE_GREEN, BOSS_OUTLINE_BLUE, BOSS_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue ENTITY_OUTLINE_RED, ENTITY_OUTLINE_GREEN, ENTITY_OUTLINE_BLUE, ENTITY_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CUSTOM_ENTITY_OUTLINE_COLOR, CUSTOM_SHADOW_RADIUS_EXT;

    public static final ForgeConfigSpec.IntValue PLAYER_SHADOW_OUTLINE_RED, PLAYER_SHADOW_OUTLINE_GREEN, PLAYER_SHADOW_OUTLINE_BLUE, PLAYER_SHADOW_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue BOSS_SHADOW_OUTLINE_RED, BOSS_SHADOW_OUTLINE_GREEN, BOSS_SHADOW_OUTLINE_BLUE, BOSS_SHADOW_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue ENTITY_SHADOW_OUTLINE_RED, ENTITY_SHADOW_OUTLINE_GREEN, ENTITY_SHADOW_OUTLINE_BLUE, ENTITY_SHADOW_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CUSTOM_ENTITY_SHADOW_OUTLINE_COLOR;

    public static final ForgeConfigSpec.IntValue OUTLINE_RENDERABLE_DIST;

    public static final ForgeConfigSpec.DoubleValue PLAYER_SHADOW_RADIUS_EXT, BOSS_SHADOW_RADIUS_EXT, ENTITY_SHADOW_RADIUS_EXT;

    public static final ForgeConfigSpec.BooleanValue PLAYER_OUTLINE_RENDERABLE, BOSS_OUTLINE_RENDERABLE, ENTITY_OUTLINE_RENDERABLE;
    public static final ForgeConfigSpec.BooleanValue PLAYER_SHADOW_OUTLINE_RENDERABLE, BOSS_SHADOW_OUTLINE_RENDERABLE, ENTITY_SHADOW_OUTLINE_RENDERABLE;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("ItIsMeConfig");
        builder.push("EntityOutline");
        OUTLINE_RENDERABLE_DIST = builder.comment("In blocks.").defineInRange("OutlineRenderableDistance", 24, 0, Integer.MAX_VALUE);
        builder.push("PlayerOutline");
        PLAYER_OUTLINE_RENDERABLE = builder.define("PlayerOutlineRenderable", true);
        PLAYER_OUTLINE_RED = builder.defineInRange("PlayerOutlineRed", 0, 0, 255);
        PLAYER_OUTLINE_GREEN = builder.defineInRange("PlayerOutlineGreen", 255, 0, 255);
        PLAYER_OUTLINE_BLUE = builder.defineInRange("PlayerOutlineBlue", 0, 0, 255);
        PLAYER_OUTLINE_ALPHA = builder.defineInRange("PlayerOutlineAlpha", 255, 0, 255);
        builder.pop();
        builder.push("BossEntityOutline");
        BOSS_OUTLINE_RENDERABLE = builder.define("BossOutlineRenderable", true);
        BOSS_OUTLINE_RED = builder.defineInRange("BossOutlineRed", 255, 0, 255);
        BOSS_OUTLINE_GREEN = builder.defineInRange("BossOutlineGreen", 0, 0, 255);
        BOSS_OUTLINE_BLUE = builder.defineInRange("BossOutlineBlue", 0, 0, 255);
        BOSS_OUTLINE_ALPHA = builder.defineInRange("BossOutlineAlpha", 255, 0, 255);
        builder.pop();
        builder.push("OtherLivingEntityOutline");
        ENTITY_OUTLINE_RENDERABLE = builder.define("LivingEntityOutlineRenderable", true);
        ENTITY_OUTLINE_RED = builder.defineInRange("LivingEntityOutlineRed", 255, 0, 255);
        ENTITY_OUTLINE_GREEN = builder.defineInRange("LivingEntityOutlineGreen", 255, 0, 255);
        ENTITY_OUTLINE_BLUE = builder.defineInRange("LivingEntityOutlineBlue", 255, 0, 255);
        ENTITY_OUTLINE_ALPHA = builder.defineInRange("LivingEntityOutlineAlpha", 255, 0, 255);
        builder.pop();
        builder.push("EntityOutlineOverride");
        builder.comment("Format: entityResourceLocation;red;green;blue;alpha. For example: [\"minecraft:skeleton;128;255;70;255\", \"minecraft:zombie;60;90;45;255\"]");
        CUSTOM_ENTITY_OUTLINE_COLOR = builder.defineList("CustomEntityOutline", Lists.newArrayList(), obj -> obj instanceof String);
        builder.pop();
        builder.pop();
        builder.push("EntityShadowOutline");
        builder.push("PlayerShadowOutline");
        PLAYER_SHADOW_OUTLINE_RENDERABLE = builder.define("PlayerShadowOutlineRenderable", true);
        PLAYER_SHADOW_RADIUS_EXT = builder.defineInRange("PlayerShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        PLAYER_SHADOW_OUTLINE_RED = builder.defineInRange("PlayerShadowOutlineRed", 0, 0, 255);
        PLAYER_SHADOW_OUTLINE_GREEN = builder.defineInRange("PlayerShadowOutlineGreen", 255, 0, 255);
        PLAYER_SHADOW_OUTLINE_BLUE = builder.defineInRange("PlayerShadowOutlineBlue", 0, 0, 255);
        PLAYER_SHADOW_OUTLINE_ALPHA = builder.defineInRange("PlayerShadowOutlineAlpha", 255, 0, 255);
        builder.pop();
        builder.push("BossEntityShadowOutline");
        BOSS_SHADOW_OUTLINE_RENDERABLE = builder.define("BossShadowOutlineRenderable", true);
        BOSS_SHADOW_RADIUS_EXT = builder.defineInRange("BossShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        BOSS_SHADOW_OUTLINE_RED = builder.defineInRange("BossShadowOutlineRed", 255, 0, 255);
        BOSS_SHADOW_OUTLINE_GREEN = builder.defineInRange("BossShadowOutlineGreen", 0, 0, 255);
        BOSS_SHADOW_OUTLINE_BLUE = builder.defineInRange("BossShadowOutlineBlue", 0, 0, 255);
        BOSS_SHADOW_OUTLINE_ALPHA = builder.defineInRange("BossShadowOutlineAlpha", 255, 0, 255);
        builder.pop();
        builder.push("OtherLivingEntityShadowOutline");
        ENTITY_SHADOW_OUTLINE_RENDERABLE = builder.define("LivingEntityShadowOutlineRenderable", true);
        ENTITY_SHADOW_RADIUS_EXT = builder.defineInRange("LivingEntityShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        ENTITY_SHADOW_OUTLINE_RED = builder.defineInRange("LivingEntityShadowOutlineRed", 255, 0, 255);
        ENTITY_SHADOW_OUTLINE_GREEN = builder.defineInRange("LivingEntityShadowOutlineGreen", 255, 0, 255);
        ENTITY_SHADOW_OUTLINE_BLUE = builder.defineInRange("LivingEntityShadowOutlineBlue", 255, 0, 255);
        ENTITY_SHADOW_OUTLINE_ALPHA = builder.defineInRange("LivingEntityShadowOutlineAlpha", 255, 0, 255);
        builder.pop();
        builder.push("EntityShadowOutlineOverride");
        CUSTOM_SHADOW_RADIUS_EXT = builder.comment("Format: entityResourceLocation;multiplier. For example: [\"minecraft:skeleton;5.0\", \"minecraft:zombie;2.45\"]").defineList("CustomEntityShadowRadiusMultiplier", Lists.newArrayList(), obj -> obj instanceof String);
        CUSTOM_ENTITY_SHADOW_OUTLINE_COLOR = builder.comment("Format: entityResourceLocation;red;green;blue;alpha. For example: [\"minecraft:skeleton;128;255;70;255\", \"minecraft:zombie;60;90;45;255\"]").defineList("CustomEntityShadowOutline", Lists.newArrayList(), obj -> obj instanceof String);
        builder.pop();
        builder.pop();
        builder.pop();
        LIGHT_TWEAK_CONFIG = builder.build();
    }

    private static final Object2ObjectMap<ResourceLocation, OutlineColor> ENTITY_COLOR_MAP = new Object2ObjectOpenHashMap<>();
    private static final Object2ObjectMap<ResourceLocation, OutlineColor> ENTITY_SHADOW_COLOR_MAP = new Object2ObjectOpenHashMap<>();
    private static final Object2DoubleMap<ResourceLocation> ENTITY_SHADOW_RADIUS_MAP = new Object2DoubleOpenHashMap<>();
    private static final Logger LOGGER = LogManager.getLogger(ItIsMeConfig.class);

    private static void parseCustomShadowRadius() {
        for (String entry : CUSTOM_SHADOW_RADIUS_EXT.get()) {
            String[] parts = entry.split(";");
            ResourceLocation id = ResourceLocation.parse(parts[0]);
            if (ForgeRegistries.ENTITY_TYPES.getValue(id) == null) {
                LOGGER.warn("Invalid entry {} for custom entity shadow radius in Light Tweak config. Skipping.", entry);
                continue;
            }
            double ext = Double.parseDouble(parts[1]);
            ENTITY_SHADOW_RADIUS_MAP.put(id, ext);
        }
    }

    private static void parseCustomEntityOutline() {
        for (String entry : CUSTOM_ENTITY_OUTLINE_COLOR.get()) {
            String[] parts = entry.split(";");
            ResourceLocation id = ResourceLocation.parse(parts[0]);
            if (ForgeRegistries.ENTITY_TYPES.getValue(id) == null) {
                LOGGER.warn("Invalid entry {} for custom entity outline in Light Tweak config. Skipping.", entry);
                continue;
            }
            int red = Integer.parseInt(parts[1]);
            int green = Integer.parseInt(parts[2]);
            int blue = Integer.parseInt(parts[3]);
            int alpha = Integer.parseInt(parts[4]);
            OutlineColor color = new OutlineColor(red, green, blue, alpha);
            ENTITY_COLOR_MAP.put(id, color);
        }
    }

    private static void parseCustomEntityShadowOutline() {
        for (String entry : CUSTOM_ENTITY_SHADOW_OUTLINE_COLOR.get()) {
            String[] parts = entry.split(";");
            ResourceLocation id = ResourceLocation.parse(parts[0]);
            if (ForgeRegistries.ENTITY_TYPES.getValue(id) == null) {
                LOGGER.warn("Invalid entry {} for custom entity shadow outline in Light Tweak config. Skipping.", entry);
                continue;
            }
            int red = Integer.parseInt(parts[1]);
            int green = Integer.parseInt(parts[2]);
            int blue = Integer.parseInt(parts[3]);
            int alpha = Integer.parseInt(parts[4]);
            OutlineColor color = new OutlineColor(red, green, blue, alpha);
            ENTITY_SHADOW_COLOR_MAP.put(id, color);
        }
    }

    @SubscribeEvent
    public static void parseCustomEntityOutline(@NotNull FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            parseCustomEntityOutline();
            parseCustomEntityShadowOutline();
            parseCustomShadowRadius();
        });
    }

    @SubscribeEvent
    public static void reloadConfig(ModConfigEvent.@NotNull Reloading event) {
        if (event.getConfig().getModId().equals(ItIsMe.MOD_ID)) {
            synchronized (ENTITY_COLOR_MAP) {
                ENTITY_COLOR_MAP.clear();
                parseCustomEntityOutline();
            }

            synchronized (ENTITY_SHADOW_COLOR_MAP) {
                ENTITY_SHADOW_COLOR_MAP.clear();
                parseCustomEntityShadowOutline();
            }

            synchronized (ENTITY_SHADOW_RADIUS_MAP) {
                ENTITY_SHADOW_RADIUS_MAP.clear();
                parseCustomShadowRadius();
            }
        }
    }

    public static @Nullable OutlineColor getEntityOutlineColor(Entity entity) {
        if (entity instanceof Player) {
            return new OutlineColor(PLAYER_OUTLINE_RED.get(), PLAYER_OUTLINE_GREEN.get(), PLAYER_OUTLINE_BLUE.get(), PLAYER_OUTLINE_ALPHA.get());
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return new OutlineColor(BOSS_OUTLINE_RED.get(), BOSS_OUTLINE_GREEN.get(), BOSS_OUTLINE_BLUE.get(), BOSS_OUTLINE_ALPHA.get());
        } else if (ENTITY_COLOR_MAP.containsKey(RegistryEntry.get(entity))) {
            return ENTITY_COLOR_MAP.get(RegistryEntry.get(entity));
        } else if (entity instanceof LivingEntity) {
            return new OutlineColor(ENTITY_OUTLINE_RED.get(), ENTITY_OUTLINE_GREEN.get(), ENTITY_OUTLINE_BLUE.get(), ENTITY_OUTLINE_ALPHA.get());
        } else {
            return null;
        }
    }

    public static @Nullable OutlineColor getEntityShadowOutlineColor(Entity entity) {
        if (entity instanceof Player) {
            return new OutlineColor(PLAYER_SHADOW_OUTLINE_RED.get(), PLAYER_SHADOW_OUTLINE_GREEN.get(), PLAYER_SHADOW_OUTLINE_BLUE.get(), PLAYER_SHADOW_OUTLINE_ALPHA.get());
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return new OutlineColor(BOSS_SHADOW_OUTLINE_RED.get(), BOSS_SHADOW_OUTLINE_GREEN.get(), BOSS_SHADOW_OUTLINE_BLUE.get(), BOSS_SHADOW_OUTLINE_ALPHA.get());
        } else if (ENTITY_SHADOW_COLOR_MAP.containsKey(RegistryEntry.get(entity))) {
            return ENTITY_SHADOW_COLOR_MAP.get(RegistryEntry.get(entity));
        } else if (entity instanceof LivingEntity) {
            return new OutlineColor(ENTITY_SHADOW_OUTLINE_RED.get(), ENTITY_SHADOW_OUTLINE_GREEN.get(), ENTITY_SHADOW_OUTLINE_BLUE.get(), ENTITY_SHADOW_OUTLINE_ALPHA.get());
        } else {
            return null;
        }
    }

    public static double getEntityShadowRadiusExt(Entity entity) {
        if (entity instanceof Player) {
            return PLAYER_SHADOW_RADIUS_EXT.get();
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return BOSS_SHADOW_RADIUS_EXT.get();
        } else if (ENTITY_SHADOW_RADIUS_MAP.containsKey(RegistryEntry.get(entity))) {
            return ENTITY_SHADOW_RADIUS_MAP.getDouble(RegistryEntry.get(entity));
        } else if (entity instanceof LivingEntity) {
            return ENTITY_SHADOW_RADIUS_EXT.get();
        } else {
            return 1.0D;
        }
    }

    public static boolean entityOutlineRenderable(Entity entity) {
        if (entity instanceof Player) {
            return PLAYER_OUTLINE_RENDERABLE.get();
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return BOSS_OUTLINE_RENDERABLE.get();
        } else if (entity instanceof LivingEntity) {
            return ENTITY_OUTLINE_RENDERABLE.get();
        } else {
            return false;
        }
    }

    public static boolean entityShadowOutlineRenderable(Entity entity) {
        if (entity instanceof Player) {
            return PLAYER_SHADOW_OUTLINE_RENDERABLE.get();
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return BOSS_SHADOW_OUTLINE_RENDERABLE.get();
        } else if (entity instanceof LivingEntity) {
            return ENTITY_SHADOW_OUTLINE_RENDERABLE.get();
        } else {
            return false;
        }
    }
}
