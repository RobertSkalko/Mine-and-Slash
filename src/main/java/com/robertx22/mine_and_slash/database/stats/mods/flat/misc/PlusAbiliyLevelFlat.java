package com.robertx22.mine_and_slash.database.stats.mods.flat.misc;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.types.spell_calc.PlusAbilityLevelStat;
import com.robertx22.mine_and_slash.registry.empty_entries.EmptySpell;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class PlusAbiliyLevelFlat extends StatMod implements IGenerated<StatMod> {

    IAbility ability;

    public PlusAbiliyLevelFlat(IAbility ability) {
        this.ability = ability;
    }

    @Override
    public Stat GetBaseStat() {
        return new PlusAbilityLevelStat(ability);
    }

    @Override
    public float Min() {
        return 1;
    }

    @Override
    public float Max() {
        return 3;
    }

    @Override
    public StatModTypes getModType() {
        return StatModTypes.Flat;
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        new PlusAbilityLevelStat(new EmptySpell()).generateAllPossibleStatVariations()
            .forEach(x -> list.add(new PlusAbiliyLevelFlat(x.getAbility())));
        return list;
    }
}
