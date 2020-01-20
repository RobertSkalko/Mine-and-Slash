package com.robertx22.mine_and_slash.database.spells.spell_tree;

import com.robertx22.mine_and_slash.database.talent_tree.BasePerk;
import com.robertx22.mine_and_slash.database.talent_tree.PerkConnection;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.uncommon.capability.bases.IPerkCap;

public class SpellPerk extends BasePerk<SpellPerk, IPerkCap> {

    public SpellPerk(String guid) {
        super(guid);
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SPELL_PERK;
    }

    @Override
    public PerkConnection.Allocation getStatus(IPerkCap data) {
        return null;
/*
        if (data.getPerksData().isAllocated(this)) {
            return PerkConnection.Allocation.ALLOCATED;
        } else {

            if (this.isStart) {
                if (data.getAllocatedPoints() == 0) { // only 1 start
                    return PerkConnection.Allocation.CAN_ALLOCATE;
                }
            }

            boolean hascon = false;
            for (Perk con : this.connections) {
                if (data.getPerksData().isAllocated(con)) {
                    hascon = true;
                }
            }

            if (hascon) {
                return PerkConnection.Allocation.CAN_ALLOCATE;
            } else {
                return PerkConnection.Allocation.CANT_ALLOCATE;
            }

        }
*/
    }

}
