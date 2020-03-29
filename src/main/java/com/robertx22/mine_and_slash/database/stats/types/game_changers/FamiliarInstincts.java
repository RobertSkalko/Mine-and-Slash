package com.robertx22.mine_and_slash.database.stats.types.game_changers;

import com.robertx22.mine_and_slash.database.stats.types.generated.ElementalResist;
import com.robertx22.mine_and_slash.database.stats.types.resources.HealthRegen;
import com.robertx22.mine_and_slash.saveclasses.ExactStatData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;

import java.util.List;
import java.util.stream.Collectors;

public class FamiliarInstincts extends BaseGameChangerTrait {

    static int INC = 10;
    static int DEC = 20;

    private FamiliarInstincts() {
    }

    public static FamiliarInstincts getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String locDescForLangFile() {
        return "Get in touch with your primal self.";
    }

    @Override
    public String getIconPath() {
        return "game_changers/familiar_instincts";
    }

    @Override
    public String locNameForLangFile() {
        return "Familiar Instincts";
    }

    @Override
    public String GUID() {
        return "familiar_instincts_trait";
    }

    @Override
    public List<ExactStatData> getExactStats() {

        List<ExactStatData> list = ElementalResist.MAP.getList()
            .stream()
            .map(x -> new ExactStatData(INC, StatModTypes.Multi, x))
            .collect(Collectors.toList());

        list.add(new ExactStatData(-DEC, StatModTypes.Multi, HealthRegen.getInstance()));

        return list;
    }

    private static class SingletonHolder {
        private static final FamiliarInstincts INSTANCE = new FamiliarInstincts();
    }
}

