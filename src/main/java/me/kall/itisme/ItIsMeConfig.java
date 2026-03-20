package me.kall.itisme;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.*;
import me.kall.duplicationless.ext.RegistryEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
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

    public static final ForgeConfigSpec.IntValue OUTLINE_RENDERABLE_DIST;

    public static final ForgeConfigSpec.BooleanValue PLAYER_OUTLINE_RENDERABLE, BOSS_OUTLINE_RENDERABLE, NEUTRAL_OUTLINE_RENDERABLE, HOSTILE_OUTLINE_RENDERABLE, ENTITY_OUTLINE_RENDERABLE;

    public static final ForgeConfigSpec.IntValue PLAYER_OUTLINE_RED, PLAYER_OUTLINE_GREEN, PLAYER_OUTLINE_BLUE, PLAYER_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue BOSS_OUTLINE_RED, BOSS_OUTLINE_GREEN, BOSS_OUTLINE_BLUE, BOSS_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue NEUTRAL_OUTLINE_RED, NEUTRAL_OUTLINE_GREEN, NEUTRAL_OUTLINE_BLUE, NEUTRAL_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue HOSTILE_OUTLINE_RED, HOSTILE_OUTLINE_GREEN, HOSTILE_OUTLINE_BLUE, HOSTILE_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue ENTITY_OUTLINE_RED, ENTITY_OUTLINE_GREEN, ENTITY_OUTLINE_BLUE, ENTITY_OUTLINE_ALPHA;

    public static final ForgeConfigSpec.BooleanValue CHANGE_ATTACKABLE_OUTLINE_COLOR;
    public static final ForgeConfigSpec.IntValue ATTACKABLE_OUTLINE_RED, ATTACKABLE_OUTLINE_GREEN, ATTACKABLE_OUTLINE_BLUE, ATTACKABLE_OUTLINE_ALPHA;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CUSTOM_ENTITY_OUTLINE_COLOR;

    public static final ForgeConfigSpec.BooleanValue PLAYER_SHADOW_OUTLINE_RENDERABLE, BOSS_SHADOW_OUTLINE_RENDERABLE, NEUTRAL_SHADOW_OUTLINE_RENDERABLE, HOSTILE_SHADOW_OUTLINE_RENDERABLE, ENTITY_SHADOW_OUTLINE_RENDERABLE;

    public static final ForgeConfigSpec.DoubleValue PLAYER_SHADOW_RADIUS_EXT, BOSS_SHADOW_RADIUS_EXT, NEUTRAL_SHADOW_RADIUS_EXT, HOSTILE_SHADOW_RADIUS_EXT, ENTITY_SHADOW_RADIUS_EXT;

    public static final ForgeConfigSpec.IntValue PLAYER_SHADOW_OUTLINE_RED, PLAYER_SHADOW_OUTLINE_GREEN, PLAYER_SHADOW_OUTLINE_BLUE, PLAYER_SHADOW_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue BOSS_SHADOW_OUTLINE_RED, BOSS_SHADOW_OUTLINE_GREEN, BOSS_SHADOW_OUTLINE_BLUE, BOSS_SHADOW_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue NEUTRAL_SHADOW_OUTLINE_RED, NEUTRAL_SHADOW_OUTLINE_GREEN, NEUTRAL_SHADOW_OUTLINE_BLUE, NEUTRAL_SHADOW_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue HOSTILE_SHADOW_OUTLINE_RED, HOSTILE_SHADOW_OUTLINE_GREEN, HOSTILE_SHADOW_OUTLINE_BLUE, HOSTILE_SHADOW_OUTLINE_ALPHA;
    public static final ForgeConfigSpec.IntValue ENTITY_SHADOW_OUTLINE_RED, ENTITY_SHADOW_OUTLINE_GREEN, ENTITY_SHADOW_OUTLINE_BLUE, ENTITY_SHADOW_OUTLINE_ALPHA;

    public static final ForgeConfigSpec.BooleanValue CHANGE_ATTACKABLE_SHADOW_OUTLINE_COLOR;
    public static final ForgeConfigSpec.IntValue ATTACKABLE_SHADOW_OUTLINE_RED, ATTACKABLE_SHADOW_OUTLINE_GREEN, ATTACKABLE_SHADOW_OUTLINE_BLUE, ATTACKABLE_SHADOW_OUTLINE_ALPHA;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CUSTOM_ENTITY_SHADOW_OUTLINE_COLOR;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CUSTOM_SHADOW_RADIUS_EXT;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("ItIsMeConfig");

        builder.push("EntityOutline");
        OUTLINE_RENDERABLE_DIST = builder.comment("In blocks.").defineInRange("OutlineRenderableDistance", 24, 0, Integer.MAX_VALUE);

        builder.push("PlayerOutline");
        PLAYER_OUTLINE_RENDERABLE = builder.define("PlayerOutlineRenderable", true);
        PLAYER_OUTLINE_RED   = builder.defineInRange("PlayerOutlineRed",   0,   0, 255);
        PLAYER_OUTLINE_GREEN = builder.defineInRange("PlayerOutlineGreen", 255, 0, 255);
        PLAYER_OUTLINE_BLUE  = builder.defineInRange("PlayerOutlineBlue",  0,   0, 255);
        PLAYER_OUTLINE_ALPHA = builder.defineInRange("PlayerOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("BossEntityOutline");
        BOSS_OUTLINE_RENDERABLE = builder.define("BossOutlineRenderable", true);
        BOSS_OUTLINE_RED   = builder.defineInRange("BossOutlineRed",   255, 0, 255);
        BOSS_OUTLINE_GREEN = builder.defineInRange("BossOutlineGreen", 0,   0, 255);
        BOSS_OUTLINE_BLUE  = builder.defineInRange("BossOutlineBlue",  0,   0, 255);
        BOSS_OUTLINE_ALPHA = builder.defineInRange("BossOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("AttackableEntityTargetOutline");
        CHANGE_ATTACKABLE_OUTLINE_COLOR = builder.define("ChangeAttackableEntityTargetOutlineColor", true);
        ATTACKABLE_OUTLINE_RED   = builder.defineInRange("AttackableOutlineRed",   255, 0, 255);
        ATTACKABLE_OUTLINE_GREEN = builder.defineInRange("AttackableOutlineGreen", 255, 0, 255);
        ATTACKABLE_OUTLINE_BLUE  = builder.defineInRange("AttackableOutlineBlue",  0,   0, 255);
        ATTACKABLE_OUTLINE_ALPHA = builder.defineInRange("AttackableOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("NeutralEntityOutline");
        NEUTRAL_OUTLINE_RENDERABLE = builder.define("NeutralEntityOutlineRenderable", true);
        NEUTRAL_OUTLINE_RED   = builder.defineInRange("NeutralEntityOutlineRed",   255, 0, 255);
        NEUTRAL_OUTLINE_GREEN = builder.defineInRange("NeutralEntityOutlineGreen", 165, 0, 255);
        NEUTRAL_OUTLINE_BLUE  = builder.defineInRange("NeutralEntityOutlineBlue",  0,   0, 255);
        NEUTRAL_OUTLINE_ALPHA = builder.defineInRange("NeutralEntityOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("HostileEntityOutline");
        HOSTILE_OUTLINE_RENDERABLE = builder.define("HostileEntityOutlineRenderable", true);
        HOSTILE_OUTLINE_RED   = builder.defineInRange("HostileEntityOutlineRed",   255, 0, 255);
        HOSTILE_OUTLINE_GREEN = builder.defineInRange("HostileEntityOutlineGreen", 50,  0, 255);
        HOSTILE_OUTLINE_BLUE  = builder.defineInRange("HostileEntityOutlineBlue",  50,  0, 255);
        HOSTILE_OUTLINE_ALPHA = builder.defineInRange("HostileEntityOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("PassiveLivingEntityOutline");
        ENTITY_OUTLINE_RENDERABLE = builder.define("PassiveLivingEntityOutlineRenderable", true);
        ENTITY_OUTLINE_RED   = builder.defineInRange("PassiveLivingEntityOutlineRed",   255, 0, 255);
        ENTITY_OUTLINE_GREEN = builder.defineInRange("PassiveLivingEntityOutlineGreen", 255, 0, 255);
        ENTITY_OUTLINE_BLUE  = builder.defineInRange("PassiveLivingEntityOutlineBlue",  255, 0, 255);
        ENTITY_OUTLINE_ALPHA = builder.defineInRange("PassiveLivingEntityOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("EntityOutlineOverride");
        builder.comment("Format: entityResourceLocation;red;green;blue;alpha. For example: [\"minecraft:skeleton;128;255;70;255\", \"minecraft:zombie;60;90;45;255\"]");
        CUSTOM_ENTITY_OUTLINE_COLOR = builder.defineList("CustomEntityOutline", Lists.newArrayList(), obj -> obj instanceof String);
        builder.pop();

        builder.pop();

        builder.push("EntityShadowOutline");

        builder.push("PlayerShadowOutline");
        PLAYER_SHADOW_OUTLINE_RENDERABLE = builder.define("PlayerShadowOutlineRenderable", true);
        PLAYER_SHADOW_RADIUS_EXT         = builder.defineInRange("PlayerShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        PLAYER_SHADOW_OUTLINE_RED   = builder.defineInRange("PlayerShadowOutlineRed",   0,   0, 255);
        PLAYER_SHADOW_OUTLINE_GREEN = builder.defineInRange("PlayerShadowOutlineGreen", 255, 0, 255);
        PLAYER_SHADOW_OUTLINE_BLUE  = builder.defineInRange("PlayerShadowOutlineBlue",  0,   0, 255);
        PLAYER_SHADOW_OUTLINE_ALPHA = builder.defineInRange("PlayerShadowOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("BossEntityShadowOutline");
        BOSS_SHADOW_OUTLINE_RENDERABLE = builder.define("BossShadowOutlineRenderable", true);
        BOSS_SHADOW_RADIUS_EXT         = builder.defineInRange("BossShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        BOSS_SHADOW_OUTLINE_RED   = builder.defineInRange("BossShadowOutlineRed",   255, 0, 255);
        BOSS_SHADOW_OUTLINE_GREEN = builder.defineInRange("BossShadowOutlineGreen", 0,   0, 255);
        BOSS_SHADOW_OUTLINE_BLUE  = builder.defineInRange("BossShadowOutlineBlue",  0,   0, 255);
        BOSS_SHADOW_OUTLINE_ALPHA = builder.defineInRange("BossShadowOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("AttackableEntityTargetShadowOutline");
        CHANGE_ATTACKABLE_SHADOW_OUTLINE_COLOR = builder.define("ChangeAttackableEntityTargetShadowOutlineColor", true);
        ATTACKABLE_SHADOW_OUTLINE_RED   = builder.defineInRange("AttackableShadowOutlineRed",   255, 0, 255);
        ATTACKABLE_SHADOW_OUTLINE_GREEN = builder.defineInRange("AttackableShadowOutlineGreen", 255, 0, 255);
        ATTACKABLE_SHADOW_OUTLINE_BLUE  = builder.defineInRange("AttackableShadowOutlineBlue",  0,   0, 255);
        ATTACKABLE_SHADOW_OUTLINE_ALPHA = builder.defineInRange("AttackableShadowOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("NeutralEntityShadowOutline");
        NEUTRAL_SHADOW_OUTLINE_RENDERABLE = builder.define("NeutralEntityShadowOutlineRenderable", true);
        NEUTRAL_SHADOW_RADIUS_EXT         = builder.defineInRange("NeutralEntityShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        NEUTRAL_SHADOW_OUTLINE_RED   = builder.defineInRange("NeutralEntityShadowOutlineRed",   255, 0, 255);
        NEUTRAL_SHADOW_OUTLINE_GREEN = builder.defineInRange("NeutralEntityShadowOutlineGreen", 165, 0, 255);
        NEUTRAL_SHADOW_OUTLINE_BLUE  = builder.defineInRange("NeutralEntityShadowOutlineBlue",  0,   0, 255);
        NEUTRAL_SHADOW_OUTLINE_ALPHA = builder.defineInRange("NeutralEntityShadowOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("HostileEntityShadowOutline");
        HOSTILE_SHADOW_OUTLINE_RENDERABLE = builder.define("HostileEntityShadowOutlineRenderable", true);
        HOSTILE_SHADOW_RADIUS_EXT         = builder.defineInRange("HostileEntityShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        HOSTILE_SHADOW_OUTLINE_RED   = builder.defineInRange("HostileEntityShadowOutlineRed",   255, 0, 255);
        HOSTILE_SHADOW_OUTLINE_GREEN = builder.defineInRange("HostileEntityShadowOutlineGreen", 50,  0, 255);
        HOSTILE_SHADOW_OUTLINE_BLUE  = builder.defineInRange("HostileEntityShadowOutlineBlue",  50,  0, 255);
        HOSTILE_SHADOW_OUTLINE_ALPHA = builder.defineInRange("HostileEntityShadowOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("PassiveLivingEntityShadowOutline");
        ENTITY_SHADOW_OUTLINE_RENDERABLE = builder.define("PassiveLivingEntityShadowOutlineRenderable", true);
        ENTITY_SHADOW_RADIUS_EXT         = builder.defineInRange("PassiveLivingEntityShadowRadiusMultiplier", 1.75, 0, Integer.MAX_VALUE);
        ENTITY_SHADOW_OUTLINE_RED   = builder.defineInRange("PassiveLivingEntityShadowOutlineRed",   255, 0, 255);
        ENTITY_SHADOW_OUTLINE_GREEN = builder.defineInRange("PassiveLivingEntityShadowOutlineGreen", 255, 0, 255);
        ENTITY_SHADOW_OUTLINE_BLUE  = builder.defineInRange("PassiveLivingEntityShadowOutlineBlue",  255, 0, 255);
        ENTITY_SHADOW_OUTLINE_ALPHA = builder.defineInRange("PassiveLivingEntityShadowOutlineAlpha", 255, 0, 255);
        builder.pop();

        builder.push("EntityShadowOutlineOverride");
        CUSTOM_SHADOW_RADIUS_EXT = builder.comment("Format: entityResourceLocation;multiplier. For example: [\"minecraft:skeleton;5.0\", \"minecraft:zombie;2.45\"]")
                .defineList("CustomEntityShadowRadiusMultiplier", Lists.newArrayList(), obj -> obj instanceof String);
        CUSTOM_ENTITY_SHADOW_OUTLINE_COLOR = builder.comment("Format: entityResourceLocation;red;green;blue;alpha. For example: [\"minecraft:skeleton;128;255;70;255\", \"minecraft:zombie;60;90;45;255\"]")
                .defineList("CustomEntityShadowOutline", Lists.newArrayList(), obj -> obj instanceof String);
        builder.pop();

        builder.pop();
        builder.pop();
        LIGHT_TWEAK_CONFIG = builder.build();
    }

    public static final Object2ObjectMap<ResourceLocation, OutlineColor> ENTITY_COLOR_MAP        = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
    public static final Object2ObjectMap<ResourceLocation, OutlineColor> ENTITY_SHADOW_COLOR_MAP = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
    public static final Object2DoubleMap<ResourceLocation> ENTITY_SHADOW_RADIUS_MAP              = Object2DoubleMaps.synchronize(new Object2DoubleOpenHashMap<>());

    private static final Logger LOGGER = LogManager.getLogger(ItIsMeConfig.class);

    private static void parseCustomShadowRadius() {
        for (String entry : CUSTOM_SHADOW_RADIUS_EXT.get()) {
            String[] parts = entry.split(";");
            ResourceLocation id = ResourceLocation.parse(parts[0]);
            if (ForgeRegistries.ENTITY_TYPES.getValue(id) == null) {
                LOGGER.warn("Invalid entry {} for custom entity shadow radius in It Is Me config. Skipping.", entry);
                continue;
            }
            ENTITY_SHADOW_RADIUS_MAP.put(id, Double.parseDouble(parts[1]));
        }
    }

    private static void parseCustomEntityOutline() {
        for (String entry : CUSTOM_ENTITY_OUTLINE_COLOR.get()) {
            String[] parts = entry.split(";");
            ResourceLocation id = ResourceLocation.parse(parts[0]);
            if (ForgeRegistries.ENTITY_TYPES.getValue(id) == null) {
                LOGGER.warn("Invalid entry {} for custom entity outline in It Is Me config. Skipping.", entry);
                continue;
            }
            ENTITY_COLOR_MAP.put(id, new OutlineColor(
                    Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
        }
    }

    private static void parseCustomEntityShadowOutline() {
        for (String entry : CUSTOM_ENTITY_SHADOW_OUTLINE_COLOR.get()) {
            String[] parts = entry.split(";");
            ResourceLocation id = ResourceLocation.parse(parts[0]);
            if (ForgeRegistries.ENTITY_TYPES.getValue(id) == null) {
                LOGGER.warn("Invalid entry {} for custom entity shadow outline in It Is Me config. Skipping.", entry);
                continue;
            }
            ENTITY_SHADOW_COLOR_MAP.put(id, new OutlineColor(
                    Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
        }
    }

    public static void saveCustomEntityOutline() {
        List<String> entries = new ObjectArrayList<>(ENTITY_COLOR_MAP.size());
        for (Object2ObjectMap.Entry<ResourceLocation, OutlineColor> entry : ENTITY_COLOR_MAP.object2ObjectEntrySet()) {
            entries.add(entry.getKey().toString() + ";" + entry.getValue().toString());
        }
        CUSTOM_ENTITY_OUTLINE_COLOR.set(entries);
    }

    public static void saveCustomEntityShadowOutline() {
        List<String> entries = new ObjectArrayList<>(ENTITY_SHADOW_COLOR_MAP.size());
        for (Object2ObjectMap.Entry<ResourceLocation, OutlineColor> entry : ENTITY_SHADOW_COLOR_MAP.object2ObjectEntrySet()) {
            entries.add(entry.getKey().toString() + ";" + entry.getValue().toString());
        }
        CUSTOM_ENTITY_SHADOW_OUTLINE_COLOR.set(entries);
    }

    public static void saveCustomEntityShadowRadius() {
        List<String> entries = new ObjectArrayList<>(ENTITY_SHADOW_RADIUS_MAP.size());
        for (Object2DoubleMap.Entry<ResourceLocation> entry : ENTITY_SHADOW_RADIUS_MAP.object2DoubleEntrySet()) {
            entries.add(entry.getKey().toString() + ";" + entry.getDoubleValue());
        }
        CUSTOM_SHADOW_RADIUS_EXT.set(entries);
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
            ENTITY_COLOR_MAP.clear();
            parseCustomEntityOutline();

            ENTITY_SHADOW_COLOR_MAP.clear();
            parseCustomEntityShadowOutline();

            ENTITY_SHADOW_RADIUS_MAP.clear();
            parseCustomShadowRadius();
        }
    }

    public static @Nullable OutlineColor getEntityOutlineColor(Entity entity) {
        if (CHANGE_ATTACKABLE_OUTLINE_COLOR.get()
                && Minecraft.getInstance().hitResult instanceof EntityHitResult hitResult
                && hitResult.getEntity().getUUID().equals(entity.getUUID())) {
            return new OutlineColor(
                    ATTACKABLE_OUTLINE_RED.get(), ATTACKABLE_OUTLINE_GREEN.get(),
                    ATTACKABLE_OUTLINE_BLUE.get(), ATTACKABLE_OUTLINE_ALPHA.get());
        } else if (ENTITY_COLOR_MAP.containsKey(RegistryEntry.get(entity))) {
            return ENTITY_COLOR_MAP.get(RegistryEntry.get(entity));
        } else if (entity instanceof Player) {
            return new OutlineColor(
                    PLAYER_OUTLINE_RED.get(), PLAYER_OUTLINE_GREEN.get(),
                    PLAYER_OUTLINE_BLUE.get(), PLAYER_OUTLINE_ALPHA.get());
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return new OutlineColor(
                    BOSS_OUTLINE_RED.get(), BOSS_OUTLINE_GREEN.get(),
                    BOSS_OUTLINE_BLUE.get(), BOSS_OUTLINE_ALPHA.get());
        } else if (entity instanceof NeutralMob) {
            return new OutlineColor(
                    NEUTRAL_OUTLINE_RED.get(), NEUTRAL_OUTLINE_GREEN.get(),
                    NEUTRAL_OUTLINE_BLUE.get(), NEUTRAL_OUTLINE_ALPHA.get());
        } else if (entity instanceof Monster) {
            return new OutlineColor(
                    HOSTILE_OUTLINE_RED.get(), HOSTILE_OUTLINE_GREEN.get(),
                    HOSTILE_OUTLINE_BLUE.get(), HOSTILE_OUTLINE_ALPHA.get());
        } else if (entity instanceof LivingEntity) {
            return new OutlineColor(
                    ENTITY_OUTLINE_RED.get(), ENTITY_OUTLINE_GREEN.get(),
                    ENTITY_OUTLINE_BLUE.get(), ENTITY_OUTLINE_ALPHA.get());
        } else {
            return null;
        }
    }

    public static @Nullable OutlineColor getEntityShadowOutlineColor(Entity entity) {
        if (CHANGE_ATTACKABLE_SHADOW_OUTLINE_COLOR.get()
                && Minecraft.getInstance().hitResult instanceof EntityHitResult hitResult
                && hitResult.getEntity().getUUID().equals(entity.getUUID())) {
            return new OutlineColor(
                    ATTACKABLE_SHADOW_OUTLINE_RED.get(), ATTACKABLE_SHADOW_OUTLINE_GREEN.get(),
                    ATTACKABLE_SHADOW_OUTLINE_BLUE.get(), ATTACKABLE_SHADOW_OUTLINE_ALPHA.get());
        } else if (ENTITY_SHADOW_COLOR_MAP.containsKey(RegistryEntry.get(entity))) {
            return ENTITY_SHADOW_COLOR_MAP.get(RegistryEntry.get(entity));
        } else if (entity instanceof Player) {
            return new OutlineColor(
                    PLAYER_SHADOW_OUTLINE_RED.get(), PLAYER_SHADOW_OUTLINE_GREEN.get(),
                    PLAYER_SHADOW_OUTLINE_BLUE.get(), PLAYER_SHADOW_OUTLINE_ALPHA.get());
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return new OutlineColor(
                    BOSS_SHADOW_OUTLINE_RED.get(), BOSS_SHADOW_OUTLINE_GREEN.get(),
                    BOSS_SHADOW_OUTLINE_BLUE.get(), BOSS_SHADOW_OUTLINE_ALPHA.get());
        } else if (entity instanceof NeutralMob) {
            return new OutlineColor(
                    NEUTRAL_SHADOW_OUTLINE_RED.get(), NEUTRAL_SHADOW_OUTLINE_GREEN.get(),
                    NEUTRAL_SHADOW_OUTLINE_BLUE.get(), NEUTRAL_SHADOW_OUTLINE_ALPHA.get());
        } else if (entity instanceof Monster) {
            return new OutlineColor(
                    HOSTILE_SHADOW_OUTLINE_RED.get(), HOSTILE_SHADOW_OUTLINE_GREEN.get(),
                    HOSTILE_SHADOW_OUTLINE_BLUE.get(), HOSTILE_SHADOW_OUTLINE_ALPHA.get());
        } else if (entity instanceof LivingEntity) {
            return new OutlineColor(
                    ENTITY_SHADOW_OUTLINE_RED.get(), ENTITY_SHADOW_OUTLINE_GREEN.get(),
                    ENTITY_SHADOW_OUTLINE_BLUE.get(), ENTITY_SHADOW_OUTLINE_ALPHA.get());
        } else {
            return null;
        }
    }

    public static double getEntityShadowRadiusExt(Entity entity) {
        if (ENTITY_SHADOW_RADIUS_MAP.containsKey(RegistryEntry.get(entity))) {
            return ENTITY_SHADOW_RADIUS_MAP.getDouble(RegistryEntry.get(entity));
        } else if (entity instanceof Player) {
            return PLAYER_SHADOW_RADIUS_EXT.get();
        } else if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
            return BOSS_SHADOW_RADIUS_EXT.get();
        } else if (entity instanceof NeutralMob) {
            return NEUTRAL_SHADOW_RADIUS_EXT.get();
        } else if (entity instanceof Monster) {
            return HOSTILE_SHADOW_RADIUS_EXT.get();
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
        } else if (entity instanceof NeutralMob) {
            return NEUTRAL_OUTLINE_RENDERABLE.get();
        } else if (entity instanceof Monster) {
            return HOSTILE_OUTLINE_RENDERABLE.get();
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
        } else if (entity instanceof NeutralMob) {
            return NEUTRAL_SHADOW_OUTLINE_RENDERABLE.get();
        } else if (entity instanceof Monster) {
            return HOSTILE_SHADOW_OUTLINE_RENDERABLE.get();
        } else if (entity instanceof LivingEntity) {
            return ENTITY_SHADOW_OUTLINE_RENDERABLE.get();
        } else {
            return false;
        }
    }
}