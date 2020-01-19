package com.robertx22.mine_and_slash.saveclasses.talents;

import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.*;

@Storable
public class PlayerTalentsData implements IApplyableStats {

    @Store
    public int resetPoints = 0;

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

    public void remove(String guid) {
        map.put(guid, false);
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

    public boolean canRemove(Perk toRemove) {
        if (!isAllocated(toRemove)) {
            return false;
        }
        if (resetPoints <= 0) {
            return false;
        }

        for (Perk perk : toRemove.connections) {
            if (isAllocated(perk)) {
                if (hasPathToStart(perk, toRemove) == false) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasPathToStart(Perk check, Perk toRemove) {
        Queue<Perk> openSet = new ArrayDeque<>();

        openSet.addAll(check.connections);

        Set<Perk> closedSet = new HashSet<>();

        while (!openSet.isEmpty()) {
            Perk current = openSet.poll();

            if (current == toRemove || !isAllocated(current)) {
                continue; // skip exploring this path
            }

            if (current.isStart) {
                return true;
            }

            if (!closedSet.add(current)) {
                continue; // we already visited it
            }

            openSet.addAll(current.connections);
        }

        return false;
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

                if (talent.effect instanceof IApplyableStats) {
                    IApplyableStats apply = (IApplyableStats) talent.effect;
                    apply.applyStats(data);
                }

            }
        }
    }
}
