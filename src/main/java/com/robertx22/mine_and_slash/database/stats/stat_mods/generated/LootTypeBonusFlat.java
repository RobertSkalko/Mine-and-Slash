package com.robertx22.mine_and_slash.database.stats.stat_mods.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatTypes;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LootTypeBonusFlat extends StatMod implements IGenerated<StatMod> {

    public LootType type;

    public LootTypeBonusFlat(LootType type) {
        this.type = type;

    }

    @Override
    public Stat GetBaseStat() {
        return new com.robertx22.mine_and_slash.database.stats.stat_types.generated.LootTypeBonusFlat(type);
    }

    @Override
    public float Min() {
        return 10;
    }

    @Override
    public float Max() {
        return 20;
    }

    @Override
    public StatTypes Type() {
        return StatTypes.Flat;
    }

    @Override
    public String GUID() {
        return "bonus_" + type + "_drops_flat";
    }

    @Override
    public List<StatMod> generateAllPossibleStatVariations() {
        List<StatMod> list = new ArrayList<>();
        Arrays.asList(LootType.values()).forEach(x -> list.add(new LootTypeBonusFlat(x)));
        return list;

    }
}
