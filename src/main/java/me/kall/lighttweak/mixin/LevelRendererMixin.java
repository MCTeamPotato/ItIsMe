package me.kall.lighttweak.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import me.kall.lighttweak.LightTweakConfig;
import me.kall.lighttweak.OutlineColor;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.OutlineBufferSource;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
    @WrapOperation(
            method = "renderLevel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/OutlineBufferSource;setColor(IIII)V"
            )
    )
    private void resetColor(OutlineBufferSource bufferSource, int red, int green, int blue, int alpha, @NotNull Operation<Void> original, @Local Entity entity) {
        OutlineColor color = LightTweakConfig.getEntityOutlineColor(entity);
        if (color == null) {
            original.call(bufferSource, red, green, blue, alpha);
        } else {
            original.call(bufferSource, color.red, color.green, color.blue, color.alpha);
        }
    }
}
