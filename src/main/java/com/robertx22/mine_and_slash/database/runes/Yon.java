package com.robertx22.mine_and_slash.database.runes;

import com.robertx22.mine_and_slash.database.runes.base.BaseRune;
import com.robertx22.mine_and_slash.database.spells.spell_classes.ocean.FrostballSpell;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.misc.PlusAbiliyLevelFlat;

import java.util.List;

public class Yon extends BaseRune {

    public Yon(int rarity) {
        super(rarity);
    }

    @Override
    public String name() {
        return "YON";
    }

    @Override
    public List<StatMod> weaponStat() {
        return new PlusAbiliyLevelFlat(FrostballSpell.getInstance()).generateAllPossibleStatVariations();
    }

    @Override
    public List<StatMod> armorStat() {
        return new PlusAbiliyLevelFlat(FrostballSpell.getInstance()).generateAllPossibleStatVariations();
    }

    @Override
    public List<StatMod> jewerlyStat() {
        return new PlusAbiliyLevelFlat(FrostballSpell.getInstance()).generateAllPossibleStatVariations();

    }

}
