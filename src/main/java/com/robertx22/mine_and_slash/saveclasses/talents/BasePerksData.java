package com.robertx22.mine_and_slash.saveclasses.talents;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.IApplyableStats;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.*;

@Storable
public abstract class BasePerksData<T extends BasePerk> implements IApplyableStats {

    @Store
    public int resetPoints = 0;

    @Store
    protected HashMap<String, Boolean> map = new HashMap<>();

    public boolean isAllocated(String guid) {
        return map.getOrDefault(guid, false);
    }

    public boolean isAllocated(T point) {
        return isAllocated(point.GUID());
    }

    public void allocate(String guid) {
        map.put(guid, true);
    }

    public void remove(String guid) {
        map.put(guid, false);
    }

    public void reset() {
        this.map.clear();
    }

    public abstract SlashRegistryContainer getRegistryContainer();

    public int getAllocatedPerks() {

        int talents = 0;
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                if (getRegistryContainer().isRegistered(entry.getKey())) {
                    talents++;
                }
            }
        }
        return talents;
    }

    @Override
    public void applyStats(EntityCap.UnitData data) {
        for (BasePerk talent : getAllCurrentPerks()) {
            if (talent.effect != null) {

                if (talent.effect instanceof IApplyableStats) {
                    IApplyableStats apply = (IApplyableStats) talent.effect;
                    apply.applyStats(data);
                }

            }
        }
    }

    public boolean canRemove(T toRemove) {
        if (!isAllocated(toRemove)) {
            return false;
        }
        if (resetPoints <= 0) {
            return false;
        }

        for (Object obj : toRemove.connections) {
            T perk = (T) obj;

            if (isAllocated(perk)) {
                if (hasPathToStart(perk, toRemove) == false) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean hasPathToStart(T check, T toRemove) {
        Queue<T> openSet = new ArrayDeque<>();

        openSet.addAll(check.connections);

        Set<T> closedSet = new HashSet<>();

        while (!openSet.isEmpty()) {
            T current = openSet.poll();

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

    public List<T> getAllCurrentPerks() {
        List<T> list = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {

                T perk = (T) getRegistryContainer().get(entry.getKey());
                if (perk != null) {
                    list.add(perk);
                }
            }
        }
        return list;

    }
}
