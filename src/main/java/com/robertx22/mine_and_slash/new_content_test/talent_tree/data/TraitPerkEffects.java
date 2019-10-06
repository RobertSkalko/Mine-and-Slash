package com.robertx22.mine_and_slash.new_content_test.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.stat_types.game_changers.*;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkEffectBuilder;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkType;

public class TraitPerkEffects {

    public static final PerkEffect BLOOD_MAGE = PerkEffectBuilder.trait(BloodMage.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect HARMONY = PerkEffectBuilder.trait(Harmony.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect MAGICAL_LIFE = PerkEffectBuilder.trait(MagicalLife.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect MANA_BATTERY = PerkEffectBuilder.trait(ManaBattery.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect RECKLESS_BLOWS = PerkEffectBuilder.trait(RecklessBlows.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect REFRESHING_BREEZE = PerkEffectBuilder.trait(RefreshingBreeze.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect STEADY_HAND = PerkEffectBuilder.trait(SteadyHand.INSTANCE, PerkType.MAJOR);
    public static final PerkEffect TRUE_HIT = PerkEffectBuilder.trait(TrueHit.INSTANCE, PerkType.MAJOR);

}
