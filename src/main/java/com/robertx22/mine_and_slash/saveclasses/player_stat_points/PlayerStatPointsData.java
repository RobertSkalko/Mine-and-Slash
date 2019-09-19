package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.stat_types.core_stats.*;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class PlayerStatPointsData {

    public PlayerStatPointsData() {

    }

    @Store
    public SingleStatPointData dexterity = new SingleStatPointData(new Dexterity());
    @Store
    public SingleStatPointData intelligence = new SingleStatPointData(new Intelligence());
    @Store
    public SingleStatPointData vitality = new SingleStatPointData(new Vitality());
    @Store
    public SingleStatPointData strength = new SingleStatPointData(new Strength());
    @Store
    public SingleStatPointData wisdom = new SingleStatPointData(new Wisdom());
    @Store
    public SingleStatPointData stamina = new SingleStatPointData(new Stamina());

    public int getCurrentlyAllocatedPointAmount() {
        return getAllStatDatas().stream().mapToInt(x -> x.points).sum();
    }

    public List<SingleStatPointData> getAllStatDatas() {
        List<SingleStatPointData> list = new ArrayList<>();

        list.add(vitality);
        list.add(strength);

        list.add(intelligence);
        list.add(wisdom);

        list.add(dexterity);
        list.add(stamina);
        return list;
    }

}
