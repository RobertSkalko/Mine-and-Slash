package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.ManaOnHitFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.EnergyRegenPercent;

import java.util.Arrays;
import java.util.List;

public class Rah extends BaseRune {

    public Rah(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "RAH";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new ManaOnHitFlat());
    }

    @Override
    public List<StatMod> armorStat() {
        return this.penePercents();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new EnergyRegenPercent());
    }

}