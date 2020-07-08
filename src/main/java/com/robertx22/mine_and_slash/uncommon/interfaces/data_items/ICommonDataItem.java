package com.robertx22.mine_and_slash.uncommon.interfaces.data_items;

import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.ITooltip;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public interface ICommonDataItem<R extends Rarity> extends ITiered, ISalvagable, ITooltip, IType, ILevel, IRarity {

    DataItemType getDataType();

    @Override
    default boolean isSalvagable(SalvageContext context) {
        if (context == SalvageContext.AUTO_SALVAGE_BAG) {
            return this.isUnique() == false;

        }
        return true;
    }

    void saveToStack(ItemStack stack);

    @Nullable
    static ICommonDataItem load(ItemStack stack) {

        GearItemData gear = Gear.Load(stack);
        if (gear != null) {
            return gear;
        }

        return null;
    }

    String getUniqueGUID();
}
