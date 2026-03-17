package me.kall.itisme.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.kall.itisme.ItIsMeConfig;
import me.kall.itisme.ShadowOutlineRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EntityRenderDispatcher.class, priority = 100)
public abstract class EntityRenderDispatcherMixin {
    @ModifyExpressionValue(
            method = "renderBlockShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelReader;getMaxLocalRawBrightness(Lnet/minecraft/core/BlockPos;)I",
                    ordinal = 0
            ),
            require = 0
    )
    private static int skipLightCheck(int original) {
        return 15;
    }

    @ModifyExpressionValue(
            method = "renderBlockShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelReader;getMaxLocalRawBrightness(Lnet/minecraft/core/BlockPos;)I",
                    ordinal = 1
            ),
            require = 0
    )
    private static int getMaxLight(int original) {
        return 15;
    }

    @WrapOperation(
            method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;renderShadow(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/entity/Entity;FFLnet/minecraft/world/level/LevelReader;F)V"
            )
    )
    private static void increaseShadowRadius(PoseStack poseStack, MultiBufferSource buffer, Entity entity, float weight, float partialTicks, LevelReader level, float size, @NotNull Operation<Void> original) {
        original.call(poseStack, buffer, entity, weight, partialTicks, level, size * (float) ItIsMeConfig.getEntityShadowRadiusExt(entity));
    }

    @Inject(
            method = "renderBlockShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;shadowVertex(Lcom/mojang/blaze3d/vertex/PoseStack$Pose;Lcom/mojang/blaze3d/vertex/VertexConsumer;IFFFFF)V",
                    ordinal = 0
            )
    )
    private static void renderingShadow(PoseStack.Pose pose, VertexConsumer vertexConsumer, ChunkAccess chunk, LevelReader level, BlockPos pos, double x, double y, double z, float size, float i, CallbackInfo ci) {
        ShadowOutlineRenderer.setShadowRendered();
    }

    @Inject(
            method = "renderShadow",
            at = @At("RETURN")
    )
    private static void renderShadowOutline(PoseStack poseStack, MultiBufferSource buffer, Entity entity, float weight, float partialTicks, LevelReader level, float size, CallbackInfo ci) {
        if (ShadowOutlineRenderer.isShadowRendered() && ItIsMeConfig.entityShadowOutlineRenderable(entity)) ShadowOutlineRenderer.renderShadowOutline(poseStack, buffer, entity, weight, partialTicks, level, size);
    }
}