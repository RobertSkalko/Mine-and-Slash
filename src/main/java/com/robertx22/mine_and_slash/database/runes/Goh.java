package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.resources.EnergyRegenFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;

import java.util.Arrays;
import java.util.List;

public class Goh extends BaseRune {

    public Goh(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "GOH";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new EnergyRegenFlat());
    }

    @Override
    public List<StatMod> armorStat() {
        return Arrays.asList(new ArmorPercent(), new DodgeRatingFlat());
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return this.spellDamagePercents();
    }

}