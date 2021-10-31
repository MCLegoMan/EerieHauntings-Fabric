package com.cartoonishvillain.eeriehauntings;

import net.fabricmc.api.ModInitializer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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
	}
}
