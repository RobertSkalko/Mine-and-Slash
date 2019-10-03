package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.*;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.SpellDamage;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class PerkEffects {

    public static PerkEffect NOTHING = new PerkEffect(new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "");

    public static PerkEffectsWrapper SPELL_DMG = PerkEffectBuilder.build("", new ExactStatData(2, StatTypes.Flat, SpellDamage.GUID));
    public static PerkEffectsWrapper CRIT_HIT = PerkEffectBuilder.build("", new ExactStatData(2, StatTypes.Flat, CriticalHit.GUID));
    public static PerkEffectsWrapper CRIT_DMG = PerkEffectBuilder.build("", new ExactStatData(3, StatTypes.Flat, CriticalDamage.GUID));

    static int core_amount = 2;

    public static PerkEffectsWrapper INTELLIGENCE = PerkEffectBuilder.build("int", new ExactStatData(core_amount, StatTypes.Flat, Intelligence.GUID));
    public static PerkEffectsWrapper WISDOM = PerkEffectBuilder.build("wis", new ExactStatData(core_amount, StatTypes.Flat, Wisdom.GUID));

    public static PerkEffectsWrapper STAMINA = PerkEffectBuilder.build("sta", new ExactStatData(core_amount, StatTypes.Flat, Stamina.GUID));
    public static PerkEffectsWrapper DEXTERITY = PerkEffectBuilder.build("dex", new ExactStatData(core_amount, StatTypes.Flat, Dexterity.GUID));

    public static PerkEffectsWrapper VITALITY = PerkEffectBuilder.build("vit", new ExactStatData(core_amount, StatTypes.Flat, Vitality.GUID));
    public static PerkEffectsWrapper STRENGTH = PerkEffectBuilder.build("str", new ExactStatData(core_amount, StatTypes.Flat, Strength.GUID));

}

