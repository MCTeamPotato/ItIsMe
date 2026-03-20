package me.kall.itisme.mixin;

import me.kall.itisme.config.ItIsMeConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(
            method = "isCurrentlyGlowing",
            at = @At("RETURN"),
            cancellable = true
    )
    private void alwaysGlowing(@NotNull CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) return;
        LivingEntity entity = (LivingEntity) (Object) this;

        if (ItIsMeConfig.entityOutlineRenderable(entity)) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return;
            cir.setReturnValue(player.distanceTo(entity) <= ItIsMeConfig.OUTLINE_RENDERABLE_DIST.get());
        }
    }
}
