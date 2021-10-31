package com.cartoonishvillain.eeriehauntings.components;

import com.cartoonishvillain.eeriehauntings.EerieHauntings;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;

public class PlayerComponent implements ComponentV3 {
    protected boolean haunted = false;
    protected boolean anger = false;
    protected float hauntChance = (float) EerieHauntings.serverConfig.config.baseHauntChance;
    protected int ghostType = 0;
    protected int protectedDays = 0;
    protected int hauntTicks = 0;
    protected int effectTicks = 0;
    protected int effectID = 0;

    @Override
    public void readFromNbt(CompoundTag tag) {
        haunted = tag.getBoolean("haunted");
        anger = tag.getBoolean("anger");
        hauntChance = tag.getFloat("hauntchance");
        ghostType = tag.getInt("ghosttype");
        protectedDays = tag.getInt("protecteddays");
        hauntTicks = tag.getInt("hauntactivitytime");
        effectTicks = tag.getInt("effectticks");
        effectID = tag.getInt("effectid");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putBoolean("haunted", haunted);
        tag.putBoolean("anger", anger);
        tag.putFloat("hauntchance", hauntChance);
        tag.putInt("ghosttype", ghostType);
        tag.putInt("protecteddays", protectedDays);
        tag.putInt("hauntactivitytime", hauntTicks);
        tag.putInt("effectticks", effectTicks);
        tag.putInt("effectid", effectID);
    }


    public float getHauntChance() {
        return hauntChance;
    }


    public void setHauntChance(float hauntChance) {
        this.hauntChance = hauntChance;
    }


    public void addHauntChance(float addedChance) {
        this.hauntChance += addedChance;
    }


    public boolean getIsHaunted() {
        return haunted;
    }


    public void setHaunted(boolean isHaunted) {
        this.haunted = isHaunted;
    }


    public int getGhostType() {
        return ghostType;
    }


    public void setGhostType(int Type) {
        ghostType = Type;
    }


    public int getProtectedDays() {
        return protectedDays;
    }


    public void setProtectedDays(int days) {
        protectedDays = days;
        if(protectedDays < 0) protectedDays = 0;
    }


    public void setAnger(boolean isAngry) {
        anger = isAngry;
    }


    public boolean getAnger() {
        return anger;
    }


    public int getHauntActionTicks() {
        return hauntTicks;
    }


    public void setHauntActionTicks(int ticks) {
        hauntTicks = ticks;
    }


    public boolean checkHauntActionTicks() {
        hauntTicks--;
        return hauntTicks <= 0;
    }


    public int getVisualEffectTime() {
        return effectTicks;
    }


    public void setVisualEffectTime(int ticks) {
        effectTicks = ticks;
    }


    public int getEffectID() {
        return effectID;
    }


    public void setEffectID(int ID) {
        effectID = ID;
    }

}
