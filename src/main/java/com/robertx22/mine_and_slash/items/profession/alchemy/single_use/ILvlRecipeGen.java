package com.robertx22.mine_and_slash.items.profession.alchemy.single_use;

import com.robertx22.mine_and_slash.items.consumables.bases.BaseConsumabletem;
import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public interface ILvlRecipeGen extends IGenerated<BaseConsumabletem> {

    BaseConsumabletem newInstance(Professions.Levels lvl);

    @Override
    default List<BaseConsumabletem> generateAllPossibleStatVariations() {
        List<BaseConsumabletem> list = new ArrayList<>();
        for (Professions.Levels value : Professions.Levels.values()) {
            list.add(newInstance(value));
        }
        return list;
    }

}
