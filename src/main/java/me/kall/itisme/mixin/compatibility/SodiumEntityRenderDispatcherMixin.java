package me.kall.itisme.mixin.compatibility;

import com.bawnorton.mixinsquared.TargetHandler;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.kall.itisme.ShadowOutlineRenderer;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityRenderDispatcher.class, priority = 1500)
public abstract class SodiumEntityRenderDispatcherMixin {
    @TargetHandler(
            mixin = "net.caffeinemc.mods.sodium.mixin.features.render.entity.shadows.EntityRenderDispatcherMixin",
            name = "renderShadowPartFast"
    )
    @ModifyExpressionValue(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelReader;getMaxLocalRawBrightness(Lnet/minecraft/core/BlockPos;)I"
            )
    )
    private static int getMaxLight(int original) {
        return 15;
    }

    @TargetHandler(
            mixin = "net.caffeinemc.mods.sodium.mixin.features.render.entity.shadows.EntityRenderDispatcherMixin",
            name = "renderShadowPartFast"
    )
    @Inject(
            method = "@MixinSquared:Handler",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/phys/shapes/VoxelShape;bounds()Lnet/minecraft/world/phys/AABB;"
            )
    )
    private static void renderingShadow(PoseStack.Pose entry, VertexConsumer vertices, ChunkAccess chunk, LevelReader world, BlockPos pos, double x, double y, double z, float radius, float opacity, CallbackInfo originCi, CallbackInfo ci) {
        ShadowOutlineRenderer.setShadowRendered();
    }
}
