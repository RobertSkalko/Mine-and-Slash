package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.database.rarities.items.UniqueItem;
import com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials.*;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

public abstract class BaseArmorItem extends ArmorItem implements IAutoLocName, IGearItem {

    public int rarity = 0;

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    public static int MAX_GEAR_DURABILITY = 1000;

    static int Enchantability = 10;

    public abstract String Name();

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    public BaseArmorItem(int rarity, EquipmentSlotType slot) {

        super(GetMat(rarity), slot, new Properties());
        this.rarity = rarity;
    }

    private static IArmorMaterial GetMat(int rarity) {

        if (rarity == 0) {
            return new CommonMat();
        } else if (rarity == 1) {
            return new UncommonMat();
        } else if (rarity == 2) {
            return new RareMat();
        } else if (rarity == 3) {
            return new EpicMat();
        } else if (rarity == 4) {
            return new LegendaryMat();
        } else if (rarity == 5) {
            return new MythicalMat();
        } else if (rarity == new UniqueItem().Rank()) {
            return new UniqueMat();
        }

        return new CommonMat();

    }

}