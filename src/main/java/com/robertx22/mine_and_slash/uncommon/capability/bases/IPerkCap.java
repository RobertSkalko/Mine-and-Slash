package com.robertx22.mine_and_slash.uncommon.capability.bases;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.Perk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.registry.SlashRegistryContainer;
import com.robertx22.mine_and_slash.saveclasses.talents.BasePerksData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashSet;
import java.util.Set;

import static com.robertx22.mine_and_slash.database.talent_tree.PerkConnection.Allocation.*;

public abstract class IPerkCap<T extends BasePerk, D extends BasePerksData<T>> {

    public abstract int getAllowedPoints(EntityCap.UnitData data);

    public abstract void allocate(T talent);

    public abstract void applyStats(EntityCap.UnitData data, PlayerEntity player);

    public abstract D getAbilitiesData();

    public abstract SlashRegistryContainer getContainer();

    public Set<PerkConnection> getConnections() {

        HashSet<PerkConnection> set = new HashSet<>();

        for (Object o : getContainer().getList()) {
            T talent = (T) o;

            if (getAbilitiesData().isAllocated(talent)) {
                for (Object obj : talent.connections) {
                    T con = (T) obj;

                    if (getAbilitiesData().isAllocated(con)) {
                        set.add(new PerkConnection(ALLOCATED, talent, con));
                    } else {
                        set.add(new PerkConnection(CAN_ALLOCATE, talent, con));
                    }
                }

            } else {
                for (Object obj : talent.connections) {
                    T con = (T) obj;

                    if (getAbilitiesData().isAllocated(con)) {
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
        return this.getAbilitiesData()
            .isAllocated(perk);
    }

    public boolean tryRemovePoint(T perk, ServerPlayerEntity player) {
        if (getAbilitiesData().canRemove(perk)) {
            this.getAbilitiesData()
                .remove(perk.GUID());
            this.getAbilitiesData().resetPoints--;
            return true;
        }
        return false;
    }

    public void allocate(Perk talent) {
        this.getAbilitiesData()
            .allocate(talent.GUID());
    }

    public int getFreePoints(EntityCap.UnitData data) {
        return getAllowedPoints(data) - this.getAllocatedPoints();
    }

    public int getAllocatedPoints() {
        return this.getAbilitiesData()
            .getAllocatedPerks();
    }

    public void reset() {
        this.getAbilitiesData()
            .reset();
    }

    public void addResetPoints(int amount) {
        this.getAbilitiesData().resetPoints += amount;
    }

    public boolean allowMultipleStarts() {
        return false;
    }

    public boolean canAllocatePoint(T talent, EntityCap.UnitData data) {

        if (getFreePoints(data) > 0 == false) {
            return false;
        }

        if (talent.isStart) {
            if (allowMultipleStarts()) {
                return true;
            } else {
                if (this.getAbilitiesData()
                    .getAllCurrentPerks()
                    .stream()
                    .anyMatch(x -> x.isStart)) {
                    // if player already picked a starting point, dont allow to pick other start points
                    return false;
                } else {
                    return true;
                }
            }

        }

        boolean can = false;
        for (Object obj : talent.connections) {
            T con = (T) obj;
            if (this.getAbilitiesData()
                .isAllocated(con)) {
                can = true;
                break;
            }
        }

        return can;

    }

}
