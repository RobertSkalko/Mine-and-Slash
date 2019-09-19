package com.robertx22.mine_and_slash.saveclasses.player_stat_points;

import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.corestats.*;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IStatsContainer;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.List;

@Storable
public class PlayerStatsData implements IStatsContainer {

    public PlayerStatsData() {

    }

    @Store
    public SingleStatData dexterity = new SingleStatData(new DexterityFlat());
    @Store
    public SingleStatData intelligence = new SingleStatData(new IntelligenceFlat());
    @Store
    public SingleStatData vitality = new SingleStatData(new VitalityFlat());
    @Store
    public SingleStatData strength = new SingleStatData(new StrengthFlat());
    @Store
    public SingleStatData wisdom = new SingleStatData(new WisdomFlat());
    @Store
    public SingleStatData stamina = new SingleStatData(new StaminaFlat());

    public int getCurrentlyAllocatedPointAmount() {
        return getAllStatDatas().stream().mapToInt(x -> x.points).sum();
    }

    public List<SingleStatData> getAllStatDatas() {
        List<SingleStatData> list = new ArrayList<>();

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
