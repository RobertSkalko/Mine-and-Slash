package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;

public class Perk extends BasePerk<Perk, PlayerTalentsCap.IPlayerTalentsData> {

    public Perk(String guid) {
        super(guid);
    }

    public void render(int x, int y) {
        this.effect.render(x, y);
    }

    public Perk setAsStart() {
        this.isStart = true;
        return this;
    }

    @Override
    public PerkConnection.Allocation getStatus(PlayerTalentsCap.IPlayerTalentsData data) {

        if (data.getData().isAllocated(this)) {
            return PerkConnection.Allocation.ALLOCATED;
        } else {

            if (this.isStart) {
                if (data.getAllocatedPoints() == 0) { // only 1 start
                    return PerkConnection.Allocation.CAN_ALLOCATE;
                }
            }

            boolean hascon = false;
            for (Perk con : this.connections) {
                if (data.getData().isAllocated(con)) {
                    hascon = true;
                }
            }

            if (hascon) {
                return PerkConnection.Allocation.CAN_ALLOCATE;
            } else {
                return PerkConnection.Allocation.CANT_ALLOCATE;
            }

        }

    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.PERK;
    }

}
