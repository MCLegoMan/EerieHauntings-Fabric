package com.cartoonishvillain.eeriehauntings.client;

import com.cartoonishvillain.eeriehauntings.Register;
import com.cartoonishvillain.eeriehauntings.entity.SoulBallProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class soulballrenderer extends EntityRenderer<SoulBallProjectile> {
    public static final ItemStack stack = new ItemStack(Register.SOULBALL);
    protected soulballrenderer(EntityRendererProvider.Context context) {
        super(context);

    }

    @Override
    public void render(SoulBallProjectile entity, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED, i, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, entity.getId());
        super.render(entity, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    public ResourceLocation getTextureLocation(SoulBallProjectile entity) {
        return null;
    }
}
