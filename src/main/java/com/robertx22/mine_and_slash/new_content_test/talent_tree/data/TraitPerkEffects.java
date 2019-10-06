package com.robertx22.mine_and_slash.new_content_test.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.stat_types.game_changers.*;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkEffectBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkType;

public class TraitPerkEffects {

    public static final PerkEffect BLOOD_MAGE = PerkEffectBuilder.trait("blood_mage", BloodMage.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect HARMONY = PerkEffectBuilder.trait("harmony", Harmony.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect MAGICAL_LIFE = PerkEffectBuilder.trait("magical_life", MagicalLife.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect MANA_BATTERY = PerkEffectBuilder.trait("mana_battery", ManaBattery.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect RECKLESS_BLOWS = PerkEffectBuilder.trait("reckless_blows", RecklessBlows.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect REFRESHING_BREEZE = PerkEffectBuilder.trait("refreshing_breeze", RefreshingBreeze.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect STEADY_HAND = PerkEffectBuilder.trait("steady_hand", SteadyHand.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect TRUE_HIT = PerkEffectBuilder.trait("true_hit", TrueHit.INSTANCE, PerkType.MAJOR);

}
