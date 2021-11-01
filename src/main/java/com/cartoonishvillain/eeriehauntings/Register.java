package com.cartoonishvillain.eeriehauntings;

import com.cartoonishvillain.eeriehauntings.entity.SoulBallProjectile;
import com.cartoonishvillain.eeriehauntings.items.Materials;
import com.cartoonishvillain.eeriehauntings.items.Offering;
import com.cartoonishvillain.eeriehauntings.items.ghosthuntingitems.*;
import com.cartoonishvillain.eeriehauntings.items.rewarditems.CalciteChalk;
import com.cartoonishvillain.eeriehauntings.items.rewarditems.GhostlyInstrument;
import com.cartoonishvillain.eeriehauntings.items.rewarditems.SoulBall;
import com.cartoonishvillain.eeriehauntings.items.rewarditems.UnearthlyDagger;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class Register {

    public static ResourceLocation medium_sound_id = new ResourceLocation("eeriehauntings", "medium_sounds");
    public static ResourceLocation strong_sound_id = new ResourceLocation("eeriehauntings", "strong_sounds");
    public static ResourceLocation emf_sound_id = new ResourceLocation("eeriehauntings", "emfcounter");
    public static ResourceLocation radio_sound_id = new ResourceLocation("eeriehauntings", "radio");
    public static SoundEvent MEDIUMSTRENGTHSOUNDS = new SoundEvent(medium_sound_id);
    public static SoundEvent STRONGSTRENGTHSOUNDS = new SoundEvent(strong_sound_id);
    public static SoundEvent EMFCOUNTERSOUNDS = new SoundEvent(emf_sound_id);
    public static SoundEvent RADIOSOUND = new SoundEvent(radio_sound_id);

    public static final Item EMFCOUNTER = new EMFCounter(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1));
    public static final Item OLDRADIO = new Radio(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1));
    public static final Item UNEARTHLYSHARD = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item UNEARTHLYGEM = new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));
    public static final Item INCENSESTICK = new IncenseStick(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));
    public static final Item PURFIEDWATER = new PurifiedWater(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));
    public static final Item REDSTONEDREAMCATCHER = new RedstoneDreamCatcher(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));

    public static final Item CALCITECHALK = new CalciteChalk(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));
    public static final Item UNEARTHLYOFFERING = new Offering(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS));
    public static final Item GHOSTLYINSTRUMENT = new GhostlyInstrument(new Item.Properties().durability(128).tab(CreativeModeTab.TAB_MISC));
    public static final Item AMPLIFIEDGHOSTLYINSTRUMENT = new GhostlyInstrument(new Item.Properties().durability(128).tab(CreativeModeTab.TAB_MISC));

    public static final Item UNEARTHLYDAGGER = new UnearthlyDagger(Materials.UNEARTHLY, 3, -1.5f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT));
    public static final Item SOULBALL = new SoulBall(new Item.Properties().tab(CreativeModeTab.TAB_MISC));

    public static final EntityType<SoulBallProjectile> SOULLBALLENTITYTYPE = Registry.register(Registry.ENTITY_TYPE, new ResourceLocation("eeriehauntings:soulballprojectile"), FabricEntityTypeBuilder.<SoulBallProjectile>create(MobCategory.MISC, SoulBallProjectile::new).dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());

    public static void init(){
        Registry.register(Registry.SOUND_EVENT, medium_sound_id, MEDIUMSTRENGTHSOUNDS);
        Registry.register(Registry.SOUND_EVENT, strong_sound_id, STRONGSTRENGTHSOUNDS);
        Registry.register(Registry.SOUND_EVENT, emf_sound_id, EMFCOUNTERSOUNDS);
        Registry.register(Registry.SOUND_EVENT, radio_sound_id, RADIOSOUND);

        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:emf_counter"), EMFCOUNTER);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:old_radio"), OLDRADIO);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:unearthly_shard"), UNEARTHLYSHARD);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:unearthly_gem"), UNEARTHLYGEM);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:incense_stick"), INCENSESTICK);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:purified_water"), PURFIEDWATER);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:redstone_dream_catcher"), REDSTONEDREAMCATCHER);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:calcite_chalk"), CALCITECHALK);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:unearthly_offering"), UNEARTHLYOFFERING);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:ghostly_instrument"), GHOSTLYINSTRUMENT);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:amplified_ghostly_instrument"), AMPLIFIEDGHOSTLYINSTRUMENT);

        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:unearthly_dagger"), UNEARTHLYDAGGER);
        Registry.register(Registry.ITEM, new ResourceLocation("eeriehauntings:soulball"), SOULBALL);


    }


//    public static final Item EMFCOUNTER = ITEMS.register("emf_counter", () -> new EMFCounter(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));
//    public static final Item OLDRADIO = ITEMS.register("old_radio", () -> new Radio(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1)));
//    public static final Item UNEARTHLYSHARD = ITEMS.register("unearthly_shard", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
//    public static final Item UNEARTHLYGEM = ITEMS.register("unearthly_gem", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
//    public static final Item INCENSESTICK = ITEMS.register("incense_stick", () -> new IncenseStick(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
//    public static final Item PURIFIEDWATER = ITEMS.register("purified_water", () -> new PurifiedWater(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
//    public static final Item REDSTONEDREAMCATCHER = ITEMS.register("redstone_dream_catcher", () -> new RedstoneDreamCatcher(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
//
//
//    public static final Item CALCITECHALK = ITEMS.register("calcite_chalk", () -> new CalciteChalk(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
//    public static final Item UNEARTHLYOFFERING = ITEMS.register("unearthly_offering", () -> new Offering(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
//    public static final Item GHOSTLYINSTRUMENT = ITEMS.register("ghostly_instrument", () -> new GhostlyInstrument(new Item.Properties().durability(128).tab(CreativeModeTab.TAB_MISC)));
//    public static final Item AMPLIFIEDGHOSTLYINSTRUMENT = ITEMS.register("amplified_ghostly_instrument", () -> new GhostlyInstrument(new Item.Properties().durability(128).tab(CreativeModeTab.TAB_MISC)));
}
