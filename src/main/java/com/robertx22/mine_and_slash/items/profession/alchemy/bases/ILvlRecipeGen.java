package com.robertx22.mine_and_slash.items.profession.alchemy.bases;

import com.robertx22.mine_and_slash.new_content_test.professions.data.Professions;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;

import java.util.ArrayList;
import java.util.List;

public interface ILvlRecipeGen extends IGenerated<BasePotion> {

    BasePotion newInstance(Professions.Levels lvl);

    @Override
    default List<BasePotion> generateAllPossibleStatVariations() {
        List<BasePotion> list = new ArrayList<>();
        for (Professions.Levels value : Professions.Levels.values()) {
            list.add(newInstance(value));
        }
        return list;
    }

}
