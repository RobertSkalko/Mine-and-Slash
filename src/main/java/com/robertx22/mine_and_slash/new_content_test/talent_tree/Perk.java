package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.perks.StartPerks;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Perk implements ISlashRegistryEntry<Perk> {

    public Perk(String guid) {
        this.guid = guid;
    }

    public List<Perk> connections = new ArrayList<>();
    public PerkEffect effect;
    private String guid;
    public int x;
    public int y;

    public boolean isStart = false;

    public void render(int x, int y) {

        this.effect.render(x, y);
    }

    public Perk setAsStart() {
        this.isStart = true;
        return this;
    }

    public PerkType getPerkType() {
        return this.effect.type;
    }

    public boolean tryConnectTo(Perk other) {

        if (this.equals(other)) {
            return false;
        }

        boolean added = false;

        if (this.connections.contains(other) == false) {
            this.connections.add(other);
            added = true;
        }

        if (other.connections.contains(this) == false) {
            other.connections.add(this);
            added = true;
        }

        return added;

    }

    public PerkConnection.Allocation getStatus(PlayerTalentsCap.IPlayerTalentsData data) {

        if (data.getData().isAllocated(this)) {
            return PerkConnection.Allocation.ALLOCATED;
        } else {

            if (this.isStart) {
                return PerkConnection.Allocation.CAN_ALLOCATE;
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

    public boolean isConnectedTo(Perk talent) {
        for (Perk con : connections) {
            if (con.GUID().equals(talent.GUID())) {
                return true;
            }
        }
        return false;
    }

    public static List<Perk> getStarts() {
        return Arrays.asList(StartPerks.GUARDIAN, StartPerks.MAGE, StartPerks.THIEF, StartPerks.WARRIOR);
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.TALENT;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public int getRarityRank() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int Tier() {
        return 0;
    }
}
