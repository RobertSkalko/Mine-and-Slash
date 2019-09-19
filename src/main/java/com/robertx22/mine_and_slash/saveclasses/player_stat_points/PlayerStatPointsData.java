package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class PlayerStatPointsData {

    public PlayerStatPointsData() {

    }

    @Store
    public SingleStatPointData dexterity = new SingleStatPointData(LvlPointStat.DEXTERITY);
    @Store
    public SingleStatPointData intelligence = new SingleStatPointData(LvlPointStat.INTELLIGENCE);
    @Store
    public SingleStatPointData vitality = new SingleStatPointData(LvlPointStat.VITALITY);
    @Store
    public SingleStatPointData strength = new SingleStatPointData(LvlPointStat.STRENGTH);
    @Store
    public SingleStatPointData wisdom = new SingleStatPointData(LvlPointStat.WISDOM);
    @Store
    public SingleStatPointData stamina = new SingleStatPointData(LvlPointStat.STAMINA);

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
