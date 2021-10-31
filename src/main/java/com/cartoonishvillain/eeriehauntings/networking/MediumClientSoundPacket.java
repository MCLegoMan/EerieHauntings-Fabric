package com.cartoonishvillain.eeriehauntings.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class MediumClientSoundPacket {

    public static void encodeAndSend(ServerPlayer player, FriendlyByteBuf buffer, int ID){
        buffer.writeInt(ID);
        ServerPlayNetworking.send(player, new ResourceLocation("eeriehauntings:mediumsoundpacket"), buffer);
    }



}
