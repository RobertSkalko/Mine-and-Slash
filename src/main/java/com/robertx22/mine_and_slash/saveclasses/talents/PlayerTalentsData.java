package com.robertx22.mine_and_slash.saveclasses.talents;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
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

    public boolean isAllocated(Perk point) {
        return isAllocated(point.GUID());
    }

    public void allocate(String guid) {
        map.put(guid, true);
    }

    public int getAllocatedTalents() {

        int talents = 0;
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                if (SlashRegistry.Perks().isRegistered(entry.getKey())) {
                    talents++;
                }
            }
        }
        return talents;
    }

    public void reset() {
        this.map.clear();
    }

    public List<Perk> getAllCurrentTalents() {
        List<Perk> list = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {

                Perk perk = SlashRegistry.Perks().get(entry.getKey());
                if (perk != null) {
                    list.add(perk);
                }
            }
        }
        return list;

    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        for (Perk talent : getAllCurrentTalents()) {
            if (talent.effect != null) {
                talent.effect.applyStats(data);
            }
        }
    }
}
