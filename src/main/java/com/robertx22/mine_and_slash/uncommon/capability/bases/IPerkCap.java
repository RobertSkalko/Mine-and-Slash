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

    public abstract void syncToClient(PlayerEntity player);

    public abstract int getAllowedPoints(EntityCap.UnitData data);

    public abstract void allocate(T talent);

    public abstract void applyStats(EntityCap.UnitData data, PlayerEntity player);

    public abstract D getData();

    public abstract SlashRegistryContainer getContainer();

    public Set<PerkConnection> getConnections() {

        HashSet<PerkConnection> set = new HashSet<>();

        for (Object o : getContainer().getList()) {
            T talent = (T) o;

            if (getData().isAllocated(talent)) {
                for (Object obj : talent.connections) {
                    T con = (T) obj;

                    if (getData().isAllocated(con)) {
                        set.add(new PerkConnection(ALLOCATED, talent, con));
                    } else {
                        set.add(new PerkConnection(CAN_ALLOCATE, talent, con));
                    }
                }

            } else {
                for (Object obj : talent.connections) {
                    T con = (T) obj;

                    if (getData().isAllocated(con)) {
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
        return this.getData().isAllocated(perk);
    }

    public boolean tryRemovePoint(T talent) {
        if (getData().canRemove(talent)) {
            this.getData().remove(talent.GUID());
            this.getData().resetPoints--;
            return true;
        }
        return false;
    }

    public void allocate(Perk talent) {
        this.getData().allocate(talent.GUID());
    }

    public int getFreePoints(EntityCap.UnitData data) {
        return getAllowedPoints(data) - this.getAllocatedPoints();
    }

    public int getAllocatedPoints() {
        return this.getData().getAllocatedPerks();
    }

    public void reset() {
        this.getData().reset();
    }

    public void addResetPoints(int amount) {
        this.getData().resetPoints += amount;
    }

    public boolean canAllocatePoint(T talent, EntityCap.UnitData data) {

        if (getFreePoints(data) > 0 == false) {
            return false;
        }

        if (talent.isStart) {
            if (this.getData().getAllCurrentTalents().stream().anyMatch(x -> x.isStart)) {
                // if player already picked a starting point, dont allow to pick other start points
                return false;
            }

            return true;
        }

        boolean can = false;
        for (Object obj : talent.connections) {
            T con = (T) obj;
            if (this.getData().isAllocated(con)) {
                can = true;
                break;
            }
        }

        return can;

    }

}
