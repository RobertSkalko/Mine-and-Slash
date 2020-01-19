package com.robertx22.mine_and_slash.database.talent_tree;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePerk<T extends BasePerk, C> implements ISlashRegistryEntry<T> {

    public BasePerk(String guid) {
        this.guid = guid;
    }

    public List<T> connections = new ArrayList<>();
    public BasePerkEffect effect;
    protected String guid;
    public int x;
    public int y;

    public boolean isStart = false;

    public abstract PerkConnection.Allocation getStatus(C data);

    public boolean isConnectedTo(T talent) {
        for (T con : connections) {
            if (con.GUID().equals(talent.GUID())) {
                return true;
            }
        }
        return false;
    }

    public PerkType getPerkType() {
        // make major stats only lookk major if they are gamechangers. i agree i didnt think this one through
        if (effect.getPerkType() == PerkType.MAJOR) {
            if (effect.isGameChanger()) {
                return effect.getPerkType();
            } else {
                return PerkType.BIG;
            }
        }

        return this.effect.getPerkType();
    }

    public boolean tryConnectTo(T other) {

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

    @Override
    public String GUID() {
        return guid;
    }
}
