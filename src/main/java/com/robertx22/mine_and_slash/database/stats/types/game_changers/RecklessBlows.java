package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.defense.Armor;
import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalPenetration;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.List;
import java.util.stream.Collectors;

public class RecklessBlows extends BaseGameChangerTrait {

    private RecklessBlows() {
    }

    public static final RecklessBlows INSTANCE = new RecklessBlows();

    @Override
    public String locDescForLangFile() {
        return "Expose your weakness to give everything you have.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/reckless_blows";
    }

    @Override
    public String locNameForLangFile() {
        return "Reckless Blows";
    }

    @Override
    public String GUID() {
        return "reckless_blows_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        List<ExactStatData> list = new ElementalPenetration(Elements.Nature).generateAllSingleVariations()
            .stream()
            .map(x -> new ExactStatData(25, StatModTypes.Multi, x))
            .collect(Collectors.toList());

        list.add(new ExactStatData(-50, StatModTypes.Multi, Armor.getInstance()));

        return list;
    }

}
