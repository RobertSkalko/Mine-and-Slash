package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.ArmorPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.PhysicalDamagePercent;

import java.util.Arrays;
import java.util.List;

public class Ita extends BaseRune {

    public Ita(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "ITA";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new PhysicalDamagePercent());
    }

    @Override
    public List<StatMod> armorStat() {
        return Arrays.asList(new ArmorPercent(), new DodgeRatingFlat());
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return this.spellDamageFlats();
    }

}
