package com.cartoonishvillain.eeriehauntings.items.ghosthuntingitems;

import com.cartoonishvillain.eeriehauntings.EerieHauntings;
import com.cartoonishvillain.eeriehauntings.client.ClientInitializer;
import com.cartoonishvillain.eeriehauntings.components.HauntedWorker;
import com.cartoonishvillain.eeriehauntings.components.PlayerComponent;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.cartoonishvillain.eeriehauntings.components.ComponentStarter.PLAYERCOMPONENTINSTANCE;

public class IncenseStick extends Item {

    public IncenseStick(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if(p_41433_.getMainHandItem().getItem() instanceof IncenseStick && p_41433_.getOffhandItem().getItem().equals(Items.FLINT_AND_STEEL) && !p_41432_.isClientSide()){
            p_41433_.getCooldowns().addCooldown(this, 100);
            p_41433_.getMainHandItem().shrink(1);
            p_41432_.playSound(null, p_41433_.getOnPos(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1, 1);

            PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(p_41433_);
                int type = h.getGhostType();
                if(h.getIsHaunted()){
                    if(!EerieHauntings.serverConfig.config.easyExorcismMode) {
                        if (type == 3) {
                            if (h.getAnger()) HauntedWorker.expellGhost((ServerPlayer) p_41433_);
                            else HauntedWorker.exorciseGhost((ServerPlayer) p_41433_);
                        } else {
                            if(EerieHauntings.serverConfig.config.doSpiritsGetAngry)
                                h.setAnger(true);
                            h.setHauntActionTicks(1);
                        }
                    } else HauntedWorker.exorciseGhost((ServerPlayer) p_41433_);
                }
        }
        return super.use(p_41432_, p_41433_, p_41434_);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        if(p_41422_ != null && p_41422_.isClientSide) {
            if (!ClientInitializer.easyModeEnabled) {
                p_41423_.add(Component.translatable("tools.eeriehauntings.incenseusage").withStyle(ChatFormatting.GOLD));
                p_41423_.add(Component.translatable("tools.eeriehauntings.incense").withStyle(ChatFormatting.GOLD));
            } else {
                p_41423_.add(Component.translatable("tools.eeriehauntings.easyincense").withStyle(ChatFormatting.GOLD));
            }
        }
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }
}
