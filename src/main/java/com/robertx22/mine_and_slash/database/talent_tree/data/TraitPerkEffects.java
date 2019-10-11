package com.robertx22.mine_and_slash.database.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.types.game_changers.*;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.database.talent_tree.PerkEffectBuilder;
import com.robertx22.mine_and_slash.database.talent_tree.PerkType;

public class TraitPerkEffects {

    public static PerkEffect BLOOD_MAGE;
    public static PerkEffect HARMONY;
    public static PerkEffect MAGICAL_LIFE;
    public static PerkEffect MANA_BATTERY;
    public static PerkEffect RECKLESS_BLOWS;
    public static PerkEffect REFRESHING_BREEZE;
    public static PerkEffect STEADY_HAND;
    public static PerkEffect TRUE_HIT;
    public static PerkEffect BLEED_MASTERY;

    public static void create() {
        TRUE_HIT = PerkEffectBuilder.trait("true_hit", TrueHit.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        STEADY_HAND = PerkEffectBuilder.trait("steady_hand", SteadyHand.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        RECKLESS_BLOWS = PerkEffectBuilder.trait("reckless_blows", RecklessBlows.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        MANA_BATTERY = PerkEffectBuilder.trait("mana_battery", ManaBattery.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        BLOOD_MAGE = PerkEffectBuilder.trait("blood_mage", BloodMage.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        MAGICAL_LIFE = PerkEffectBuilder.trait("magical_life", MagicalLife.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        HARMONY = PerkEffectBuilder.trait("harmony", Harmony.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        REFRESHING_BREEZE = PerkEffectBuilder.trait("refreshing_breeze", RefreshingBreeze.INSTANCE, PerkType.MAJOR)
                .setGameChanger();
        BLEED_MASTERY = PerkEffectBuilder.trait("bleed_mastery", BleedMastery.INSTANCE, PerkType.MAJOR)
                .setGameChanger();

    }

}
