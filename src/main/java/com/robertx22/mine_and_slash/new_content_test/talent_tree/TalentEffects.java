package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalDamage;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class TalentEffects {

    public static TalentPointEffect NOTHING = new TalentPointEffect(new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID));

    public static TalentPointEffect SMALL_CRIT_HIT = new TalentPointEffect(new ExactStatData(2, StatTypes.Flat, CriticalHit.GUID));
    public static TalentPointEffect MEDIUM_CRIT_HIT = new TalentPointEffect(new ExactStatData(4, StatTypes.Flat, CriticalHit.GUID));
    public static TalentPointEffect BIG_CRIT_HIT = new TalentPointEffect(new ExactStatData(10, StatTypes.Flat, CriticalHit.GUID));

    public static TalentPointEffect SMALL_CRIT_DMG = new TalentPointEffect(new ExactStatData(3, StatTypes.Flat, CriticalDamage.GUID));
    public static TalentPointEffect MEDIUM_CRIT_DMG = new TalentPointEffect(new ExactStatData(5, StatTypes.Flat, CriticalDamage.GUID));
    public static TalentPointEffect BIG_CRIT_DMG = new TalentPointEffect(new ExactStatData(15, StatTypes.Flat, CriticalDamage.GUID));

}

