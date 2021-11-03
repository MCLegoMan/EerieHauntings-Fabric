package com.cartoonishvillain.eeriehauntings.mixin;


import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static com.cartoonishvillain.eeriehauntings.Register.UNEARTHLYSHARD;

@Mixin(Item.class)
public class ItemToolTipMixin {
    @Inject(at = @At("TAIL"), method = "appendHoverText")
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag, CallbackInfo info) {
        if(itemStack.getItem().equals(UNEARTHLYSHARD)){
            list.add(new TranslatableComponent("info.eeriehauntings.shard").withStyle(ChatFormatting.GOLD));
            list.add(new TranslatableComponent("info.eeriehauntings.shardgain").withStyle(ChatFormatting.RED));
        }
    }
}
