package me.kall.itisme.mixin.compatibility;

import com.github.argon4w.acceleratedrendering.features.entities.AcceleratedEntityShadowRenderer;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.mojang.blaze3d.vertex.VertexConsumer;
import me.kall.itisme.ShadowOutlineRenderer;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AcceleratedEntityShadowRenderer.class)
public abstract class AcceleratedEntityShadowRendererMixin {
    @ModifyExpressionValue(
            method = "render(Lcom/mojang/blaze3d/vertex/VertexConsumer;Lcom/github/argon4w/acceleratedrendering/features/entities/AcceleratedEntityShadowRenderer$Context;Lorg/joml/Matrix4f;Lorg/joml/Matrix3f;III)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/LevelReader;getMaxLocalRawBrightness(Lnet/minecraft/core/BlockPos;)I"
            )
    )
    private int getMaxLight(int original) {
        return 15;
    }

    @Inject(
            method = "render(Lcom/mojang/blaze3d/vertex/VertexConsumer;Lcom/github/argon4w/acceleratedrendering/features/entities/AcceleratedEntityShadowRenderer$Context;Lorg/joml/Matrix4f;Lorg/joml/Matrix3f;III)V",
            remap = false,
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/github/argon4w/acceleratedrendering/core/buffers/accelerated/builders/IAcceleratedVertexConsumer;endTransform()V",
                    remap = false
            )
    )
    private void renderingShadow(VertexConsumer vertexConsumer, AcceleratedEntityShadowRenderer.Context context, Matrix4f transform, Matrix3f normal, int light, int overlay, int color, CallbackInfo ci) {
        ShadowOutlineRenderer.setShadowRendered();
    }
}
