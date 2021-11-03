package com.cartoonishvillain.eeriehauntings.components;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.nbt.CompoundTag;

public class WorldComponent implements ComponentV3 {
    protected boolean night = false;
    @Override
    public void readFromNbt(CompoundTag tag) {
        night = tag.getBoolean("night");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putBoolean("night", night);
    }

    public boolean isNight() {
        return night;
    }

    public void setisNight(boolean status) {
        night = status;
    }
}
