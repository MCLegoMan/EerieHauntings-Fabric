package com.cartoonishvillain.eeriehauntings.networking;

import com.cartoonishvillain.eeriehauntings.EerieHauntings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ConfigPacket {

    public static void send(ServerPlayer player, FriendlyByteBuf byteBuf){
        byteBuf.writeInt(EerieHauntings.serverConfig.config.chalkDurationInDays);
        byteBuf.writeFloat((float) EerieHauntings.serverConfig.config.hauntChanceAddedBySoulBallHit);
        byteBuf.writeBoolean(EerieHauntings.serverConfig.config.enableBoons);
        byteBuf.writeBoolean(EerieHauntings.serverConfig.config.easyExorcismMode);
        ServerPlayNetworking.send(player, new ResourceLocation("eeriehauntings:configupdate"), byteBuf);
    }
}
