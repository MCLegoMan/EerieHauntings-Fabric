package com.cartoonishvillain.eeriehauntings.mixin;

import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRenderer.class)
public interface EerieRendererAccessor {
    @Invoker("loadEffect")
    public void eerieInvokeLoadEffect(ResourceLocation resourceLocation);
}
