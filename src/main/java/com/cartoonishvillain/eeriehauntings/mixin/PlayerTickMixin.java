package com.cartoonishvillain.eeriehauntings.mixin;

import com.cartoonishvillain.eeriehauntings.components.HauntedWorker;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerTickMixin {
    @Inject(at = @At("TAIL"), method = "tick()V")
    private void Eerietick(CallbackInfo info) {
        HauntedWorker.tickHaunting((Player) (Object) this);
    }
}

