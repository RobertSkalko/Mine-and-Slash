package com.robertx22.mine_and_slash.database.unique_items.swords;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Sword;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.LifeOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.MagicStealFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class SwordWater implements IUnique {
    public SwordWater() {

    }

    static StatReq req = new StatReq(LvlPointStat.INTELLIGENCE, StatReq.Size.MEDIUM, LvlPointStat.WISDOM, StatReq.Size.MEDIUM);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 6;
    }

    @Override
    public String GUID() {
        return "swordwater0";
    }

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(
            new ManaOnHitFlat(),
            new MagicStealFlat(),
            new LifeOnHitFlat()
        );
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Sword.INSTANCE;
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Water));
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Ice Elemental Sword";
    }

    @Override
    public String locDescForLangFile() {
        return "Energy is everywhere, it just begs to be grasped";
    }
}
