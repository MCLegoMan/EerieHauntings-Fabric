package com.cartoonishvillain.eeriehauntings.client;

import com.cartoonishvillain.eeriehauntings.Register;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ClientInitializer implements ClientModInitializer {



    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Register.SOULLBALLENTITYTYPE, (ThrownItemRenderer::new));
    }


}
