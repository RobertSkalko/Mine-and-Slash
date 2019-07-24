package com.robertx22.mine_and_slash.database.stats.stat_mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_types.generated.BonusSpecificSpell;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public class BonusSpecificSpellFlat extends StatMod implements IGenerated<StatMod> {

    public BaseSpell spell;

    public BonusSpecificSpellFlat(BaseSpell type) {
        this.spell = type;
    }

    @Override
    public float Min() {
        return 20;
    }

    @Override
    public float Max() {
        return 50;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public Stat GetBaseStat() {
        return new BonusSpecificSpell(spell);
    }

    @Override
    public String GUID() {
        return spell.formattedGUID() + "power_flat";
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        SlashRegistry.Spells()
                .getAll()
                .values()
                .forEach(x -> list.add(new BonusSpecificSpellFlat(x)));
        return list;
    }

}
