package com.cartoonishvillain.eeriehauntings.client;

import com.cartoonishvillain.eeriehauntings.Register;
import com.cartoonishvillain.eeriehauntings.packets.spawning.EntitySpawnPacket;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class ClientInitializer implements ClientModInitializer {

    public static final ResourceLocation SPAWNPACKET = new ResourceLocation("eeriehauntings:spawn_packet");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Register.SOULLBALLENTITYTYPE, (soulballrenderer::new));
        receiveEntityPacket();
    }

    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(SPAWNPACKET, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.byId(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUUID();
            int entityId = byteBuf.readVarInt();
            Vec3 pos = EntitySpawnPacket.PacketBufUtil.readVec3(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (Minecraft.getInstance().level == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(Minecraft.getInstance().level);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.setPacketCoordinates(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setYRot(pitch);
                e.setXRot(yaw);
                e.setId(entityId);
                e.setUUID(uuid);
                Minecraft.getInstance().level.addFreshEntity(e);
            });
        });

    }
}
