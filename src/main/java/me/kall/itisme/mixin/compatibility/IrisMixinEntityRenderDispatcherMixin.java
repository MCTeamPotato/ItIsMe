package me.kall.itisme.mixin.compatibility;

import com.bawnorton.mixinsquared.TargetHandler;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("CancellableInjectionUsage")
@Mixin(value = EntityRenderDispatcher.class, priority = 2000)
public abstract class IrisMixinEntityRenderDispatcherMixin {
    @TargetHandler(mixin = "net.irisshaders.iris.mixin.MixinEntityRenderDispatcher", name = "iris$maybeSuppressShadow")
    @Inject(method = "@MixinSquared:Handler", at = @At("HEAD"), cancellable = true)
    private static void skipSuppressShadow(CallbackInfo ci, @NotNull CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
