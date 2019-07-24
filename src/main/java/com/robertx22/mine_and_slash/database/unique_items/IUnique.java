package com.robertx22.mine_and_slash.database.unique_items;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IGearSlotType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;

import java.util.List;

public interface IUnique extends IWeighted, IRarity, IGearSlotType, ITiered, IGUID, IAutoLocName, IAutoLocDesc, IGearItem, ISlashRegistryEntry<IUnique> {

    @Override
    public default int Weight() {
        return this.getRarity().Weight();
    }

    List<StatMod> uniqueStats();

    @Override
    public default int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public default Rarity getRarity() {
        return Rarities.Items.get(getRarityRank());
    }

    public default boolean canGetSet() {
        return false;
    }

    @Override
    public default AutoLocGroup locNameGroup() {
        return AutoLocGroup.Unique_Items;
    }

    @Override
    public default AutoLocGroup locDescGroup() {
        return AutoLocGroup.Unique_Items;
    }

    @Override
    public default SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.UNIQUE_GEAR;
    }

}
