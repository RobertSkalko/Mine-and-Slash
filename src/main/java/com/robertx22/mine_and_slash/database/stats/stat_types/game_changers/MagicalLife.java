package com.robertx22.mine_and_slash.database.stats.stat_types.game_changers;

import com.robertx22.mine_and_slash.database.stats.TransferMethod;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.Health;
import com.robertx22.mine_and_slash.database.stats.stat_types.resources.MagicShield;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatTransfer;

import java.util.Arrays;
import java.util.List;

public class MagicalLife extends BaseGameChangerTrait implements IStatTransfer {

    private MagicalLife() {
    }

    public static final MagicalLife INSTANCE = new MagicalLife();

    @Override
    public String locDescForLangFile() {
        return "Transforms your magic shield into health.";
    }

    @Override
    public String locNameForLangFile() {
        return "Magical Life";
    }

    @Override
    public List<TransferMethod> Transfer() {
        return Arrays.asList(new TransferMethod(MagicShield.INSTANCE, Health.INSTANCE));
    }

    @Override
    public String GUID() {
        return "magical_life_trait";
    }

    @Override
    public void transferStats(Unit copy, Unit unit, StatData data) {

        for (TransferMethod stat : this.Transfer()) {

            float val = copy.getStat(stat.converted.GUID()).Flat;

            unit.getStat(stat.converted).Flat -= val;
            unit.getStat(stat.statThatBenefits).Flat += val;

        }

    }

}
