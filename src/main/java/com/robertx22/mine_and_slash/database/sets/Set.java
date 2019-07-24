package com.robertx22.mine_and_slash.database.sets;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.MinMax;
import com.robertx22.mine_and_slash.database.requirements.GearRequestedFor;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.bases.IhasRequirements;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.WornSetData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public abstract class Set implements IWeighted, IGUID, IRarity, IhasRequirements, IAutoLocName, ISlashRegistryEntry<Set> {

    public Set() {
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.SET;
    }

    @Override
    public String locNameLangFileGUID() {
        return Ref.MODID + ".set." + formattedGUID();
    }

    public int StatPercent = 100;

    public MinMax statPercents = new MinMax(100, 100);

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Item_Sets;
    }

    @Override
    public int Weight() {
        return this.getRarity().Weight();
    }

    public abstract HashMap<Integer, StatMod> AllMods();

    public boolean meetsRequirements(GearRequestedFor requested) {

        return requirements().satisfiesAllRequirements(requested);

    }

    public List<StatMod> getObtainedMods(WornSetData data) {

        List<StatMod> mods = new ArrayList();

        if (data.setGUID == this.GUID()) {
            for (Entry<Integer, StatMod> mod : this.AllMods().entrySet()) {
                if (data.count >= mod.getKey()) {
                    mods.add(mod.getValue());
                }
            }
        }

        return mods;
    }

}
