package com.robertx22.mine_and_slash.database.unique_items.axes;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Axe;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.LifestealPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalDamagePercent;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;

import java.util.Arrays;
import java.util.List;

public class AxeWaterFire implements IUnique {
    public AxeWaterFire() {

    }

    static StatReq req = new StatReq(
        LvlPointStat.INTELLIGENCE, StatReq.Size.SMALL, LvlPointStat.STRENGTH, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public int Tier() {
        return 16;
    }

    @Override
    public String GUID() {
        return "axewaterfire0";
    }

    float multi = 0.7F;

    @Override
    public List<StatMod> uniqueStats() {
        return Arrays.asList(new CriticalDamagePercent().size(StatMod.Size.CRIPPLED),
            new LifestealPercent().size(StatMod.Size.CRIPPLED), new ManaOnHitFlat().size(StatMod.Size.CRIPPLED)
        );
    }

    @Override
    public List<StatMod> primaryStats() {
        return Arrays.asList(new ElementalAttackDamageFlat(Elements.Fire).size(StatMod.Size.LOW),
            new ElementalAttackDamageFlat(Elements.Water).size(StatMod.Size.LOW)
        );
    }

    @Override
    public String locNameForLangFile() {
        return Styles.YELLOW + "Axe of Frostfire";
    }

    @Override
    public String locDescForLangFile() {
        return "My efforts to merge elements shall not be in vain.";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return Axe.INSTANCE;
    }
}




