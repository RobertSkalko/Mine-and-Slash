package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class PlayerStatPointsData implements IStatsContainer {

    public PlayerStatPointsData() {

    }

    @Store
    public SingleStatPointData dexterity = new SingleStatPointData(new DexterityFlat());
    @Store
    public SingleStatPointData intelligence = new SingleStatPointData(new IntelligenceFlat());
    @Store
    public SingleStatPointData vitality = new SingleStatPointData(new VitalityFlat());
    @Store
    public SingleStatPointData strength = new SingleStatPointData(new StrengthFlat());
    @Store
    public SingleStatPointData wisdom = new SingleStatPointData(new WisdomFlat());
    @Store
    public SingleStatPointData stamina = new SingleStatPointData(new StaminaFlat());

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

    @Override
    public List<LevelAndStats> GetAllStats(int level) {
        List<LevelAndStats> list = new ArrayList<>();
        getAllStatDatas().forEach(x -> list.addAll(x.GetAllStats(level)));
        return list;
    }
}
