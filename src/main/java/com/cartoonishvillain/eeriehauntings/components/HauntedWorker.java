package com.cartoonishvillain.eeriehauntings.components;

import com.cartoonishvillain.eeriehauntings.EerieHauntings;
import com.cartoonishvillain.eeriehauntings.Register;
import com.cartoonishvillain.eeriehauntings.networking.LightClientSoundPacket;
import com.cartoonishvillain.eeriehauntings.networking.MediumClientSoundPacket;
import com.cartoonishvillain.eeriehauntings.networking.StrongClientSoundPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.UUID;

import static com.cartoonishvillain.eeriehauntings.components.ComponentStarter.PLAYERCOMPONENTINSTANCE;

public class HauntedWorker {

    public static void tickHaunting(ServerPlayer player){
        PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
        if(ValidPlayer(player)) {
            if (h.getIsHaunted() && h.checkHauntActionTicks()) {
                if (player.level.isDay()) {
                    lightEffect( player);
                } else {
                    if (!h.getAnger()) {
                        int chance = player.getRandom().nextInt(10);
                        if (chance <= 5) {
                            lightEffect(player);
                        } else if (chance <= 7) {
                            moderateEffect(player, true);
                        } else if (chance <= 8) {
                            lightEffect(player);
                            moderateEffect( player, true);
                        } else {
                            strongEffect(player, true);
                        }

                    }

                    //If angered, it'll lean towards stronger events.
                    else {
                        int chance = player.getRandom().nextInt(10);
                        if (chance <= 2) {
                            lightEffect(player);
                        } else if (chance <= 5) {
                            moderateEffect(player, true);
                        } else if (chance <= 7) {
                            lightEffect(player);
                            moderateEffect(player, true);
                        } else {
                            strongEffect(player, true);
                        }
                    }
                }
                h.setHauntActionTicks(player.getRandom().nextInt(EerieHauntings.serverConfig.config.maximumCooldownInTicksForEvents - EerieHauntings.serverConfig.config.minimumCooldownInTicksForEvents) + EerieHauntings.serverConfig.config.minimumCooldownInTicksForEvents);

                if (player.getMainHandItem().getItem().equals(Register.OLDRADIO) && h.getGhostType() != 1 && h.getGhostType() != 0) {
                    player.getCooldowns().addCooldown(Register.OLDRADIO, 100);
                    player.level.playSound(null, player.getOnPos(), Register.RADIOSOUND, SoundSource.MASTER, 1, 1);
                    player.displayClientMessage(new TranslatableComponent("item.eeriehauntings.radio").withStyle(ChatFormatting.GOLD), true);
                } else if (player.getMainHandItem().getItem().equals(Register.EMFCOUNTER) && h.getGhostType() != 2 && h.getGhostType() != 0) {
                    player.getCooldowns().addCooldown(Register.EMFCOUNTER, 100);
                    player.level.playSound(null, player.getOnPos(), Register.EMFCOUNTERSOUNDS, SoundSource.MASTER, 1, 1);
                    player.displayClientMessage(new TranslatableComponent("item.eeriehauntings.emf").withStyle(ChatFormatting.GOLD), true);
                }

            }
        }
        if(!h.getIsHaunted()) h.setHauntActionTicks(0);

    }

    public static void HauntCheck(MinecraftServer server){
        List<ServerPlayer> players = server.getPlayerList().getPlayers();
        for(ServerPlayer player : players){
            PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
                //if the player isn't haunted and isn't protected from haunting, attempt to haunt
                if(!h.getIsHaunted() && h.getProtectedDays() == 0){

                    //if torment is installed and compat is on, run the torment method

                    //check haunt chance with randomizer
                    if (player.getRandom().nextInt(100) < h.getHauntChance()){
                        //player is haunted! Attatch random ghost type.
                        h.setHaunted(true);
                        h.setGhostType(player.getRandom().nextInt(3)+1);
                    }
                }
                if(h.getProtectedDays() > 0){h.setProtectedDays(h.getProtectedDays() - 1);}
                //reset haunt chances for all.
                h.setHauntChance((float) EerieHauntings.serverConfig.config.baseHauntChance);
        }
    }

    public static void ResetGhostAngers(MinecraftServer server){
        List<ServerPlayer> players = server.getPlayerList().getPlayers();
        for(ServerPlayer player : players){
            PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
                h.setAnger(false);
        }
    }


    private static void lightEffect(ServerPlayer player){
        int soundID = player.getRandom().nextInt(EerieHauntings.lowEndSounds.size());
        LightClientSoundPacket.encodeAndSend(player, new FriendlyByteBuf(Unpooled.buffer()), player.getId(), soundID);
    }

    private static void moderateEffect(ServerPlayer player, boolean notRecycled){
        if(EerieHauntings.serverConfig.config.spiritsPerformMediumStrengthEffects) {
            int random;
            if(EerieHauntings.serverConfig.config.spiritsCanAffectThePhysicalWorldSometimes && notRecycled) random = player.getRandom().nextInt(5);
            else random = player.getRandom().nextInt(3);
            switch (random) {
                case 0 -> {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 350, 0));
//                player.displayClientMessage(new TranslatableComponent("ghost.moderateslow.alert"), false);
                }
                case 1 -> {
                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 350, 0));
//                player.displayClientMessage(new TranslatableComponent("ghost.moderateblind.alert"), false);
                }
                case 2 -> {
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 350, 0));
//                player.displayClientMessage(new TranslatableComponent("ghost.moderateweakness.alert"), false);
                }
                case 3 -> {
                    if (!leverWork(player)) {
                        moderateEffect(player, false);
                        return;
                    }
                    break;
                }
                case 4 -> {
                    if (!buttonWork(player)) {
                        moderateEffect(player, false);
                        return;
                    }
                    break;
                }
            }
            MediumClientSoundPacket.encodeAndSend(player, new FriendlyByteBuf(Unpooled.buffer()), player.getId());
        }else {lightEffect(player);}
    }

    //TODO: Reimplement shaders
    private static void strongEffect(ServerPlayer player, boolean notRecycled){
        if(EerieHauntings.serverConfig.config.spiritsPerformStrongStrengthEffects) {
            int random;
            if (EerieHauntings.serverConfig.config.spiritsCanAffectThePhysicalWorldSometimes && notRecycled) random = player.getRandom().nextInt(4);
            else random = player.getRandom().nextInt(3);
            switch (random) {
                case 0 -> {
                    player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 0));
                    PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
                        h.setEffectID(1);
                        h.setVisualEffectTime(200);

//                player.displayClientMessage(new TranslatableComponent("ghost.stronglevitate.alert"), false);
                }
                case 1 -> {
                    player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 350, 0));
                    PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
                        h.setEffectID(3);
                        h.setVisualEffectTime(350);

//                player.displayClientMessage(new TranslatableComponent("ghost.strongconfusion.alert"), false);
                }
                case 2 -> {
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0));
                    PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
                        h.setEffectID(2);
                        h.setVisualEffectTime(200);

//                player.displayClientMessage(new TranslatableComponent("ghost.stronghunger.alert"), false);
                }
                case 3 -> {
                    if(!doorWork(player)){
                        strongEffect(player, false);
                        return;
                    }
                    break;
                }
            }
            StrongClientSoundPacket.encodeAndSend(player, new FriendlyByteBuf(Unpooled.buffer()), player.getId());
        }else {lightEffect(player);}
    }

    //Ghost removal w/o making the ghost angry (or letting it calm down first)
    public static void exorciseGhost(ServerPlayer player){
        PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
                h.setHaunted(false);
                h.setGhostType(0);
                ItemEntity item = new ItemEntity(EntityType.ITEM, player.level);
                item.setItem(new ItemStack(Register.UNEARTHLYSHARD));
                item.setPos(player.getX(), player.getY(), player.getZ());
                player.level.addFreshEntity(item);
    }

    //Ghost removal while angry. Doesn't drop an unearthlyShard
    public static void expellGhost(ServerPlayer player){
        PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);

                h.setHaunted(false);
                h.setGhostType(0);

    }

    //Ghost removal with an offering. Doesn't drop an unearthlyShard. Buffs the player.
    public static void boonExpelGhost(ServerPlayer player){
        PlayerComponent h = PLAYERCOMPONENTINSTANCE.get(player);
        h.setHaunted(false);
        h.setGhostType(0);
            if(EerieHauntings.serverConfig.config.enableBoons) {
                int rand = player.getRandom().nextInt(6);
                switch (rand) {
                    case 0 -> {
                        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, Integer.MAX_VALUE, 1 + EerieHauntings.serverConfig.config.boonPotencyAdder, false, false));
                        player.displayClientMessage(new TranslatableComponent("boon.eeriehauntings.speed").withStyle(ChatFormatting.AQUA), false);
                    }
                    case 1 -> {
                        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, Integer.MAX_VALUE, EerieHauntings.serverConfig.config.boonPotencyAdder, false, false));
                        player.displayClientMessage(new TranslatableComponent("boon.eeriehauntings.haste").withStyle(ChatFormatting.YELLOW), false);
                    }
                    case 2 -> {
                        player.addEffect(new MobEffectInstance(MobEffects.JUMP, Integer.MAX_VALUE, EerieHauntings.serverConfig.config.boonPotencyAdder, false, false));
                        player.displayClientMessage(new TranslatableComponent("boon.eeriehauntings.jump").withStyle(ChatFormatting.GREEN), false);
                    }
                    case 3 -> {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, Integer.MAX_VALUE, EerieHauntings.serverConfig.config.boonPotencyAdder, false, false));
                        player.displayClientMessage(new TranslatableComponent("boon.eeriehauntings.resistance").withStyle(ChatFormatting.RED), false);
                    }
                    case 4 -> {
                        player.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, Integer.MAX_VALUE, 1 + EerieHauntings.serverConfig.config.boonPotencyAdder, false, false));
                        player.displayClientMessage(new TranslatableComponent("boon.eeriehauntings.life").withStyle(ChatFormatting.LIGHT_PURPLE), false);
                    }
                    case 5 -> {
                        player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, Integer.MAX_VALUE, EerieHauntings.serverConfig.config.boonPotencyAdder, false, false));
                        player.displayClientMessage(new TranslatableComponent("boon.eeriehauntings.strength").withStyle(ChatFormatting.DARK_RED), false);
                    }
                }
            } else player.displayClientMessage(new TranslatableComponent("boon.eeriehauntings.disabled").withStyle(ChatFormatting.DARK_RED), false);
        }



    private static boolean doorWork(Player player){
        boolean open = player.getRandom().nextBoolean();
        boolean worked = false;
        int range = EerieHauntings.serverConfig.config.physicalEffectScanRange_HigherIsSpookierButTakesMoreTime;
        for(int i = (int) (player.getX() - range); i < player.getX()+range; i++){
            for(int j = (int) (player.getY() - range); j < player.getY()+range; j++){
                for(int k = (int) player.getZ() - range; k < player.getZ()+range; k++){
                    BlockPos blockPos = new BlockPos(i, j, k);
                    BlockState blockState = player.level.getBlockState(blockPos);
                    if (blockState.getBlock() instanceof DoorBlock && blockState.is(BlockTags.WOODEN_DOORS)) {
                        ((DoorBlock) blockState.getBlock()).setOpen(null, player.level, blockState, blockPos, open); worked = true;}
                }
            }
        }

        return worked;
    }


    private static boolean leverWork(Player player){
        boolean worked = false;
        int range = EerieHauntings.serverConfig.config.physicalEffectScanRange_HigherIsSpookierButTakesMoreTime;
        for(int i = (int) (player.getX() - range); i < player.getX()+range; i++){
            for(int j = (int) (player.getY() - range); j < player.getY()+range; j++){
                for(int k = (int) player.getZ() - range; k < player.getZ()+range; k++){
                    BlockPos blockPos = new BlockPos(i, j, k);
                    BlockState blockState = player.level.getBlockState(blockPos);
                    if (blockState.getBlock() instanceof LeverBlock) {
                        ((LeverBlock) blockState.getBlock()).pull(blockState, player.level, blockPos);
                        worked = true;
                    }
                }
            }
        }
        return worked;
    }

    private static boolean buttonWork(Player player){
        boolean worked = false;
        int range = EerieHauntings.serverConfig.config.physicalEffectScanRange_HigherIsSpookierButTakesMoreTime;
        for(int i = (int) (player.getX() - range); i < player.getX()+range; i++){
            for(int j = (int) (player.getY() - range); j < player.getY()+range; j++){
                for(int k = (int) player.getZ() - range; k < player.getZ()+range; k++){
                    BlockPos blockPos = new BlockPos(i, j, k);
                    BlockState blockState = player.level.getBlockState(blockPos);
                    if (blockState.getBlock() instanceof ButtonBlock) {
                        ((ButtonBlock) blockState.getBlock()).press(blockState, player.level, blockPos);
                        worked = true;
                    }
                }
            }
        }
        return worked;
    }


    private static void broadcast(MinecraftServer server, Component translationTextComponent){
        server.getPlayerList().broadcastMessage(translationTextComponent, ChatType.CHAT, UUID.randomUUID());
    }

    private static boolean ValidPlayer(Player player){
        return !player.isCreative() && !player.isSpectator();
    }

}
