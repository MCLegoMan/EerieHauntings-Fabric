package com.cartoonishvillain.eeriehauntings.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class ComponentStarter implements EntityComponentInitializer, WorldComponentInitializer {
    public static final ComponentKey<PlayerComponent> PLAYERCOMPONENTINSTANCE =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("eeriehauntings:hauntdata"), PlayerComponent.class);
    public static final ComponentKey<WorldComponet> WORLDCOMPONENTINSTANCE =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("eeriehauntings:worldtracker"), WorldComponet.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(PLAYERCOMPONENTINSTANCE, player -> new PlayerComponent(), RespawnCopyStrategy.LOSSLESS_ONLY);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(WORLDCOMPONENTINSTANCE, world -> new WorldComponet());
    }

}
