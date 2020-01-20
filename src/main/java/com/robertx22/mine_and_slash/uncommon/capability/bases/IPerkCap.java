package com.robertx22.mine_and_slash.uncommon.capability.bases;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.saveclasses.talents.BasePerksData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashSet;
import java.util.Set;

import static com.robertx22.mine_and_slash.database.talent_tree.PerkConnection.Allocation.*;

public abstract class IPerkCap<T extends BasePerk, D extends BasePerksData<T>> {

    public abstract int getAllowedPoints(EntityCap.UnitData data);

    public abstract void allocate(T talent);

    public abstract void applyStats(EntityCap.UnitData data, PlayerEntity player);

    public abstract D getPerksData();

    public abstract SlashRegistryContainer getContainer();

    public Set<PerkConnection> getConnections() {

        HashSet<PerkConnection> set = new HashSet<>();

        for (Object o : getContainer().getList()) {
            T talent = (T) o;

            if (getPerksData().isAllocated(talent)) {
                for (Object obj : talent.connections) {
                    T con = (T) obj;

                    if (getPerksData().isAllocated(con)) {
                        set.add(new PerkConnection(ALLOCATED, talent, con));
                    } else {
                        set.add(new PerkConnection(CAN_ALLOCATE, talent, con));
                    }
                }

            } else {
                for (Object obj : talent.connections) {
                    T con = (T) obj;

                    if (getPerksData().isAllocated(con)) {
                        set.add(new PerkConnection(CAN_ALLOCATE, talent, con));
                    } else {
                        set.add(new PerkConnection(CANT_ALLOCATE, talent, con));
                    }
                }
            }

        }

        return set;

    }

    public boolean hasPerk(T perk) {
        return this.getPerksData().isAllocated(perk);
    }

    public boolean tryRemovePoint(T talent) {
        if (getPerksData().canRemove(talent)) {
            this.getPerksData().remove(talent.GUID());
            this.getPerksData().resetPoints--;
            return true;
        }
        return false;
    }

    public void allocate(Perk talent) {
        this.getPerksData().allocate(talent.GUID());
    }

    public int getFreePoints(EntityCap.UnitData data) {
        return getAllowedPoints(data) - this.getAllocatedPoints();
    }

    public int getAllocatedPoints() {
        return this.getPerksData().getAllocatedPerks();
    }

    public void reset() {
        this.getPerksData().reset();
    }

    public void addResetPoints(int amount) {
        this.getPerksData().resetPoints += amount;
    }

    public boolean canAllocatePoint(T talent, EntityCap.UnitData data) {

        if (getFreePoints(data) > 0 == false) {
            return false;
        }

        if (talent.isStart) {
            if (this.getPerksData()
                    .getAllCurrentPerks()
                    .stream()
                    .anyMatch(x -> x.isStart)) {
                // if player already picked a starting point, dont allow to pick other start points
                return false;
            }

            return true;
        }

        boolean can = false;
        for (Object obj : talent.connections) {
            T con = (T) obj;
            if (this.getPerksData().isAllocated(con)) {
                can = true;
                break;
            }
        }

        return can;

    }

}
