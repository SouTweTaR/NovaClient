package com.nova.client.mixin;

import com.nova.client.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    // Игра спрашивает "рисовать ли контур у сущности?" — для ESP отвечаем "да"
    @Inject(method = "hasOutline", at = @At("HEAD"), cancellable = true)
    private void novaclient$hasOutline(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (ModuleManager.ESP.shouldHighlight(entity)) {
            cir.setReturnValue(true);
        }
    }
}
