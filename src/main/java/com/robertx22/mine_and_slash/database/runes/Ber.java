package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.defense.DodgeRatingFlat;
import com.robertx22.mine_and_slash.database.stats.mods.percent.HealthRegenPercent;
import com.robertx22.mine_and_slash.database.stats.mods.percent.offense.CriticalHitPercent;

import java.util.Arrays;
import java.util.List;

public class Ber extends BaseRune {

    public Ber(int rarity) {
        super(rarity);

    }

    @Override
    public String name() {
        return "BER";
    }

    @Override
    public List<StatMod> weaponStat() {
        return Arrays.asList(new CriticalHitPercent());
    }

    @Override
    public List<StatMod> armorStat() {
        return Arrays.asList(new DodgeRatingFlat());
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return Arrays.asList(new HealthRegenPercent());
    }

}