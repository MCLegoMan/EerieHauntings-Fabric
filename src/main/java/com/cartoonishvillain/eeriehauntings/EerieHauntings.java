package com.cartoonishvillain.eeriehauntings;

import com.cartoonishvillain.eeriehauntings.components.WorldComponet;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.BedBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.cartoonishvillain.eeriehauntings.components.ComponentStarter.WORLDCOMPONENTINSTANCE;

public class EerieHauntings implements ModInitializer {
	public static ArrayList<SoundEvent> lowEndSounds = new ArrayList<SoundEvent>(List.of(SoundEvents.GLASS_BREAK, SoundEvents.CREEPER_PRIMED, SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, SoundEvents.WITHER_BREAK_BLOCK, SoundEvents.CHEST_OPEN, SoundEvents.WITHER_SPAWN, SoundEvents.WITHER_AMBIENT, SoundEvents.LAVA_EXTINGUISH, SoundEvents.PISTON_CONTRACT, SoundEvents.PISTON_EXTEND, SoundEvents.TNT_PRIMED, SoundEvents.GENERIC_EXPLODE, SoundEvents.ENDER_DRAGON_GROWL, SoundEvents.AMBIENT_CAVE, SoundEvents.AMBIENT_CAVE));

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Register.init();
		ServerTickEvents.END_SERVER_TICK.register(WorldTimeWatch.getInstance());
	}



	public static class WorldTimeWatch implements ServerTickEvents.EndTick{
		private static ServerLevel level = null;
		private static final WorldTimeWatch INSTANCE = new WorldTimeWatch();
		@Override
		public void onEndTick(MinecraftServer server) {
			if (level == null) {
				for (ServerLevel levelcheck : server.getAllLevels()) {
					if (levelcheck.dimension().toString().contains("minecraft:overworld")) {
						level = levelcheck;
					}
				}
			}

			if (level != null) {
				if (WORLDCOMPONENTINSTANCE.get(level).isNight() && level.isDay()) {
					WORLDCOMPONENTINSTANCE.get(level).setisNight(false);
					//TODO: RESETGHOSTANGERS
				} else if (!WORLDCOMPONENTINSTANCE.get(level).isNight() && !level.isDay()) {
					WORLDCOMPONENTINSTANCE.get(level).setisNight(true);
					//TODO: HAUNTCHECK
				}
			}
		}

		public static WorldTimeWatch getInstance() {return INSTANCE;}
	}
	
}
