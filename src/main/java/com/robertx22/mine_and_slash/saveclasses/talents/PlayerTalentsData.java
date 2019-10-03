package com.robertx22.mine_and_slash.saveclasses.talents;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.TalentPoint;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Storable
public class PlayerTalentsData implements IApplyableStats {

    @Store
    private HashMap<String, Boolean> map = new HashMap<>();

    public boolean isAllocated(String guid) {
        return map.getOrDefault(guid, false);
    }

    public boolean isAllocated(TalentPoint point) {
        return isAllocated(point.GUID());
    }

    public void allocate(String guid) {
        map.put(guid, true);
    }

    public int getAllocatedTalents() {

        int talents = 0;
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                if (SlashRegistry.Talents().isRegistered(entry.getKey())) {
                    talents++;
                }
            }
        }
        return talents;
    }

    public void reset() {
        this.map.clear();
    }

    public List<TalentPoint> getAllCurrentTalents() {
        List<TalentPoint> list = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                list.add(SlashRegistry.Talents().get(entry.getKey()));
            }
        }
        return list;

    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        for (TalentPoint talent : getAllCurrentTalents()) {
            talent.effect.applyStats(data);
        }
    }
}
