package com.robertx22.mine_and_slash.database.unique_items.bases;

import com.robertx22.mine_and_slash.database.gearitemslots.Shield;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.IUnique;
import net.minecraft.item.ShieldItem;

public abstract class BaseUniqueShield extends ShieldItem implements IUnique {

    public BaseUniqueShield() {
        super(new Properties().maxStackSize(1).defaultMaxDamage(1000));

    }

    @Override
    public String locDescLangFileGUID() {
        return this.getRegistryName().toString() + ".desc";
    }

    @Override
    public GearItemSlot getGearSlot() {
        return new Shield();
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Unique_Items;
    }

}

