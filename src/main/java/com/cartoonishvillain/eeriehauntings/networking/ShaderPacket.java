package com.cartoonishvillain.eeriehauntings.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ShaderPacket {

    public static void encodeAndSend(ServerPlayer player, FriendlyByteBuf buffer, int ID, short ticks, short shaderID) {
        buffer.writeInt(ID);
        buffer.writeShort(ticks);
        buffer.writeShort(shaderID);
        ServerPlayNetworking.send(player, new ResourceLocation("eeriehauntings:shaderpacket"), buffer);
    }
}
