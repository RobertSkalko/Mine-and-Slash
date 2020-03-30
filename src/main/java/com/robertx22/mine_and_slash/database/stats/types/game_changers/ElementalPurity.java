package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.generated.AllElementalDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.List;
import java.util.stream.Collectors;

public class ElementalPurity extends BaseGameChangerTrait {

    private ElementalPurity() {
    }

    public static ElementalPurity getInstance() {
        return SingletonHolder.INSTANCE;
    }

    static int ELE_INCREASE = 25;
    static int PHYS_DECREASE = 50;

    @Override
    public String locDescForLangFile() {
        return "Purify yourself of worldly affairs.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/elemental_purity";
    }

    @Override
    public String locNameForLangFile() {
        return "Elemental Purity";
    }

    @Override
    public String GUID() {
        return "elemental_purity_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        List<ExactStatData> list = new AllElementalDamage(Elements.Nature).generateAllSingleVariations()
            .stream()
            .map(x -> new ExactStatData(ELE_INCREASE, StatModTypes.Flat, x))
            .collect(Collectors.toList());
        ;

        list.add(new ExactStatData(-PHYS_DECREASE, StatModTypes.Multi, PhysicalDamage.getInstance()));

        return list;
    }

    private static class SingletonHolder {
        private static final ElementalPurity INSTANCE = new ElementalPurity();
    }
}
