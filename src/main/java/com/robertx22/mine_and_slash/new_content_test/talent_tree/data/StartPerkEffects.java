package com.robertx22.mine_and_slash.new_content_test.talent_tree.data;

import com.robertx22.mine_and_slash.database.stats.stat_types.offense.CriticalHit;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.PerkEffect;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;

public class StartPerkEffects {

    public static PerkEffect MAGE;
    public static PerkEffect THIEF;
    public static PerkEffect GUARDIAN;
    public static PerkEffect WARRIOR;

    public static void create() {
        MAGE = new PerkEffect("mage", new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/mage");
        THIEF = new PerkEffect("thief", new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/thief");
        GUARDIAN = new PerkEffect("guardian", new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/guardian");
        WARRIOR = new PerkEffect("warrior", new ExactStatData(0, StatTypes.Flat, CriticalHit.GUID), "starts/warrior");

    }

}
