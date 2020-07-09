package com.robertx22.mine_and_slash.database.stats.types.generated;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LootTypeBonus extends Stat implements IGenerated<Stat> {

    public LootType type;

    public LootTypeBonus(LootType type) {
        this.type = type;

    }

    @Override
    public String locDescLangFileGUID() {
        return Ref.MODID + ".stat_desc." + "loot_drop_bonus";
    }

    @Override
    public boolean IsShownOnStatGui() {
        return false;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "Increases Loot Drops of the type";
    }

    @Override
    public String GUID() {
        return "bonus_" + type.GUID() + "_drops";
    }

    @Override
    public String locNameForLangFile() {
        return "Bonus " + type.getName() + " Drops";
    }

    @Override
    public List<Stat> generateAllPossibleStatVariations() {
        List<Stat> list = new ArrayList<>();
        Arrays.asList(LootType.values())
            .forEach(x -> list.add(new LootTypeBonus(x)));
        return list;

    }
}
