package com.cartoonishvillain.eeriehauntings.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "eeriehauntings")
public class EerieConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject
    public EerieOptions config = new EerieOptions();

    public static class EerieOptions{
        public double baseHauntChance = 5;
        public double villagerKillHauntChanceIncrease = 10;
        public double playerKillHauntChanceIncrease = 25;
        public double golemKillHauntChanceIncrease = 7.5;
        public double petKillHauntChanceIncrease = 12.5;
        public double otherKillHauntChanceIncrease = 1.5;

        public boolean spiritsPerformMediumStrengthEffects = true;
        public boolean spiritsPerformStrongStrengthEffects = true;
        public boolean spiritsCanAffectThePhysicalWorldSometimes = true;
        public int physicalEffectScanRange_HigherIsSpookierButTakesMoreTime = 5;
        public int minimumCooldownInTicksForEvents = 400;
        public int maximumCooldownInTicksForEvents = 700;
        public int boonPotencyAdder = 0;

        public boolean easyExorcismMode = true;
        public boolean doSpiritsGetAngry = true;
        public boolean enableBoons = true;
        public int chalkDurationInDays = 10;
        public double hauntChanceAddedBySoulBallHit = 5;
    }
}
