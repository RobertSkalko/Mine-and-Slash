package com.robertx22.mine_and_slash.database.item_modifications.bases;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.registry.SlashRegistryType;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.text.ITextComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseItemModification implements IWeighted, IRarity, ISlashRegistryEntry<BaseItemModification> {

    @Override
    public Rarity getRarity() {
        return Rarities.Gears.get(getRarityRank());
    }

    @Override
    public int Weight() {
        return getRarity().Weight();
    }

    @Override
    public int getRarityRank() {
        return IRarity.Uncommon;
    }

    @Override
    public SlashRegistryType getSlashRegistryType() {
        return SlashRegistryType.ITEM_MODIFICATION;
    }

    public boolean canModify(ICommonDataItem data) {
        return isRightDataType(data) && canModifyPRIVATE(data);
    }

    public abstract DataItemType getDataType();

    public boolean isRightDataType(ICommonDataItem data) {
        return getDataType().isType(data);
    }

    public abstract ItemModType getItemModType();

    public abstract ITextComponent locName();

    protected abstract boolean canModifyPRIVATE(ICommonDataItem data);

    public abstract void modify(ICommonDataItem data);

    public static List<BaseItemModification> randomList(int amount) {

        List<BaseItemModification> list = new ArrayList<>();
        List<ItemModType> types = new ArrayList<>();

        int count = 0;

        int tries = 0;

        while (count < amount) {
            tries++;

            BaseItemModification mod = SlashRegistry.ItemModifications()
                .random();
            if (types.contains(mod.getItemModType()) == false) {
                types.add(mod.getItemModType());
                list.add(mod);
                count++;
            }

            if (tries > 100) {
                break;
            }
        }

        return list;

    }

}
