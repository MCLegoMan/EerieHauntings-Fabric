package com.cartoonishvillain.eeriehauntings.items.rewarditems;

import com.cartoonishvillain.eeriehauntings.EerieHauntings;
import com.cartoonishvillain.eeriehauntings.components.PlayerComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.cartoonishvillain.eeriehauntings.components.ComponentStarter.PLAYERCOMPONENTINSTANCE;

public class CalciteChalk extends Item {
    public CalciteChalk(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if(!p_41432_.isClientSide()) {

            PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(p_41433_);
                if (!h.getIsHaunted()) {
                    h.setProtectedDays(EerieHauntings.serverConfig.config.chalkDurationInDays);
                    p_41433_.displayClientMessage(new TranslatableComponent("info.eeriehauntings.activatechalk", EerieHauntings.serverConfig.config.chalkDurationInDays), false);
                    p_41433_.getCooldowns().addCooldown(this, 100);
                    p_41433_.getItemInHand(p_41434_).shrink(1);
                } else {
                    p_41433_.displayClientMessage(new TranslatableComponent("info.eeriehauntings.failactivatechalk"), false);
                    p_41433_.getCooldowns().addCooldown(this, 100);
                }
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        p_41423_.add(new TranslatableComponent("info.eeriehauntings.calciteexplain", EerieHauntings.serverConfig.config.chalkDurationInDays).withStyle(ChatFormatting.GOLD));
    }
}
