package com.robertx22.mine_and_slash.saveclasses.gearitem;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Storable
public abstract class StatGroupData implements IStatsContainer {

    public StatGroupData() {

    }

    public void increaseAllModsBy(GearItemData gear, int percent) {
        Mods.forEach(x -> x.setPercentClamp(gear.getRarity()
                .StatPercents(), x.getPercent() + percent));
    }

    @Store
    public List<StatModData> Mods = new ArrayList<StatModData>();

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
        return Arrays.asList(new LevelAndStats(Mods, level));
    }

}
