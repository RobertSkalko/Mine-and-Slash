package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Dos extends BaseRune {

    public Dos(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "DOS";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> armorStat() {
        return this.penePercents();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new EnergyRegenFlat());
    }

}