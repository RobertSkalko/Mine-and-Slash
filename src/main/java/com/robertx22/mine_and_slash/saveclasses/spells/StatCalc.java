package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class StatCalc {

    @Store
    public String statID;

    @Store
    public float multi;

    public Stat getStat() {
        return SlashRegistry.Stats().get(statID);
    }

    public StatCalc(Stat stat, float multi) {
        super();
        this.statID = stat.GUID();
        this.multi = multi;
    }

    public int getCalculatedValue(EntityCap.UnitData data) {
        return (int) (data.getUnit().getStat(statID).val * multi);
    }

}
