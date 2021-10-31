package com.cartoonishvillain.eeriehauntings.networking;

import com.cartoonishvillain.eeriehauntings.EerieHauntings;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Supplier;

public class LightClientSoundPacket {
    
    public static void encodeAndSend(ServerPlayer player, FriendlyByteBuf buffer, int ID, int soundID){
        buffer.writeInt(ID);
        buffer.writeInt(soundID);
        ServerPlayNetworking.send(player, new ResourceLocation("eeriehauntings:lightsoundpacket"), buffer);
    }

}
