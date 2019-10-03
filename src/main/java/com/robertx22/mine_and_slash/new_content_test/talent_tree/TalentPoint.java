package com.robertx22.mine_and_slash.new_content_test.talent_tree;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.initializers.TalentPoints;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerTalentsCap;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TalentPoint implements ISlashRegistryEntry<TalentPoint> {

    public TalentPoint(String guid) {
        this.guid = guid;
    }

    public List<TalentPoint> connections = new ArrayList<>();
    public TalentPointEffect effect;
    public ItemStack renderStack;
    private String guid;
    public int x;
    public int y;

    public boolean isStart = false;

    public TalentPoint setAsStart() {
        this.isStart = true;
        return this;
    }

    public PerkType getPerkType() {
        return this.effect.type;
    }

    public void connectTo(TalentPoint other) {
        if (this.connections.contains(other) == false) {
            this.connections.add(other);
        } else {
            System.out.println(this.GUID() + " can't connect if already connected");
        }

        if (other.connections.contains(this) == false) {
            other.connections.add(this);
        } else {
            System.out.println(other.GUID() + " can't connect if already connected");
        }

    }
    
    public TalentConnection.Allocation getStatus(
            PlayerTalentsCap.IPlayerTalentsData data) {

        if (data.getData().isAllocated(this)) {
            return TalentConnection.Allocation.ALLOCATED;
        } else {

            boolean hascon = false;
            for (TalentPoint con : this.connections) {
                if (data.getData().isAllocated(con)) {
                    hascon = true;
                }
            }

            if (hascon) {
                return TalentConnection.Allocation.CAN_ALLOCATE;
            } else {
                return TalentConnection.Allocation.CANT_ALLOCATE;
            }

        }

    }

    public boolean isConnectedTo(TalentPoint talent) {
        for (TalentPoint con : connections) {
            if (con.GUID().equals(talent.GUID())) {
                return true;
            }
        }
        return false;
    }

    public static List<TalentPoint> getStarts() {
        return Arrays.asList(TalentPoints.CRIT_DMG0);

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
