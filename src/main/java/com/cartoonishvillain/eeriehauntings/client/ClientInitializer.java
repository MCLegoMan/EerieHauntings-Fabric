package com.cartoonishvillain.eeriehauntings.client;

import com.cartoonishvillain.eeriehauntings.EerieHauntings;
import com.cartoonishvillain.eeriehauntings.Register;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class ClientInitializer implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Register.SOULLBALLENTITYTYPE, (ThrownItemRenderer::new));
        registerPackets();
    }


    @Environment(EnvType.CLIENT)
    public static void registerPackets() {
        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation("eeriehauntings:lightsoundpacket"), (client, handler, buf, responseSender) ->
        {
            int ID = buf.readInt();
            int soundID = buf.readInt();
            client.execute(() -> {
                Entity entity = Minecraft.getInstance().level.getEntity(ID);
                if(entity instanceof Player){
                    float randomPitch =  ((Player) entity).getRandom().nextFloat();
                    int xModifier = ((Player) entity).getRandom().nextInt(6 + 6) - 6;
                    int yModifier = ((Player) entity).getRandom().nextInt(6 + 6) - 6;
                    int zModifier = ((Player) entity).getRandom().nextInt(6 + 6) - 6;
                    entity.level.playSound((Player) entity, entity.getX()+xModifier, entity.getY()+yModifier, entity.getZ()+zModifier, EerieHauntings.lowEndSounds.get(soundID), SoundSource.MASTER, 1.25f, randomPitch*1.2f);
            }

        });

    });

        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation("eeriehauntings:mediumsoundpacket"), (client, handler, buf, responseSender) ->{
           int ID = buf.readInt();
           client.execute(() -> {
               Entity entity = Minecraft.getInstance().level.getEntity(ID);
               if(entity instanceof Player){
                   float randomPitch =  ((Player) entity).getRandom().nextFloat();
                   int xModifier = ((Player) entity).getRandom().nextInt(3 + 3) - 3;
                   int yModifier = ((Player) entity).getRandom().nextInt(3 + 3) - 3;
                   int zModifier = ((Player) entity).getRandom().nextInt(3 + 3) - 3;
                   entity.level.playSound((Player) entity, entity.getX()+xModifier, entity.getY()+yModifier, entity.getZ()+zModifier, Register.MEDIUMSTRENGTHSOUNDS, SoundSource.MASTER, 1.25f, randomPitch*1.15f);
               }
           });
        });

        ClientPlayNetworking.registerGlobalReceiver(new ResourceLocation("eeriehauntings:strongsoundpacket"), (client, handler, buf, responseSender) -> {
            int ID = buf.readInt();
            client.execute(() -> {
                Entity entity = Minecraft.getInstance().level.getEntity(ID);
                if(entity instanceof Player){
                    float randomPitch =  ((Player) entity).getRandom().nextFloat();
                    entity.level.playSound((Player) entity, entity.getX(), entity.getY(), entity.getZ(), Register.STRONGSTRENGTHSOUNDS, SoundSource.MASTER, 1.5f, randomPitch);
                }
            });
        });

    }
}
