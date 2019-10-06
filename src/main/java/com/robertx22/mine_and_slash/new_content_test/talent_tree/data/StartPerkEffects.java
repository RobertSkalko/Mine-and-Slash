package com.robertx22.mine_and_slash.new_content_test.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class StartPerkEffects {

    public static PerkEffect MAGE = new PerkEffect(new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/mage");
    public static PerkEffect THIEF = new PerkEffect(new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/thief");
    public static PerkEffect GUARDIAN = new PerkEffect(new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/guardian");
    public static PerkEffect WARRIOR = new PerkEffect(new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/warrior");

}
