package com.robertx22.mine_and_slash.database.items.unique_items;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IGearSlotType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ITiered;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public interface IUnique extends ISpecificStatReq, IRarity, IGearSlotType, ITiered, IAutoLocName, IAutoLocDesc,
    ISlashRegistryEntry<IUnique> {

    @Override
    public default int Weight() {
        return this.getRarity()
            .Weight();
    }

    List<StatMod> uniqueStats();

    List<StatMod> primaryStats();

    @Override
    public default int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public default Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    default String getGeneratedResourceID() {
        return getGeneratedResourceFolderPath() + GUID();
    }

    default String getGeneratedResourceFolderPath() {
        return "uniques/" + getGearSlot().
            resourceID() + "/";
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

    default Item getItemForRegistration() {
        return getGearSlot().getBaseUniqueItem()
            .setRegistryName(Ref.MODID, getGeneratedResourceID());
    }

    default Item getUniqueItem() {
        return ForgeRegistries.ITEMS.getValue(new ResourceLocation(Ref.MODID, getGeneratedResourceID()));
    }

    @Override
    default String locDescLangFileGUID() {
        return getUniqueItem().getRegistryName()
            .toString() + ".desc";
    }

    @Override
    default String locNameLangFileGUID() {
        return getUniqueItem().getRegistryName()
            .toString();
    }

}
