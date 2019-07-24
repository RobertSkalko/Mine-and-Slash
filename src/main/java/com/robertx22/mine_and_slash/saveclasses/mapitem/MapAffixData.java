package com.robertx22.mine_and_slash.saveclasses.mapitem;

import com.robertx22.mine_and_slash.database.map_affixes.BaseMapAffix;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.uncommon.enumclasses.AffectedEntities;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.List;

@Storable
public class MapAffixData {

    public MapAffixData() {

    }

    public MapAffixData(BaseMapAffix affix, int percent) {
        this.GUID = affix.GUID();
        this.percent = percent;

        if (affix.isBeneficial()) {
            affectedEntities = AffectedEntities.Mobs;
        } else {
            affectedEntities = AffectedEntities.Players;
        }

    }

    public float getBonusLootMultiplier() {
        return (float) percent / 500;
    }

    public MapAffixData(String guid, int percent, AffectedEntities affects) {
        this.GUID = guid;
        this.percent = percent;
        this.affectedEntities = affects;
    }

    @Store
    public String GUID;

    @Store
    public int percent;

    @Store
    public AffectedEntities affectedEntities;

    public BaseMapAffix getAffix() {

        return SlashRegistry.MapAffixes().get(GUID);
    }

    public List<StatModData> GetAllStats() {
        return getAffix().Stats(percent);
    }

}
