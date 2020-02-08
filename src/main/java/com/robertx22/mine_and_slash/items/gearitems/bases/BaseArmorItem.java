package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials.*;
import com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials.cloth.*;
import com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials.leather.*;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ItemUtils;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;

public abstract class BaseArmorItem extends ArmorItem implements IAutoLocName, IGearItem {

    public enum Type {
        CLOTH,
        LEATHER,
        PLATE
    }

    public int rarity = 0;

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    public BaseArmorItem(Type type, int rarity, EquipmentSlotType slot) {

        super(GetMat(type, rarity), slot, ItemUtils.getDefaultGearProperties());
        this.rarity = rarity;
    }

    public static BaseMat GetMat(Type type, int rarity) {

        if (type.equals(Type.PLATE)) {
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
            } else if (rarity == IRarity.Unique) {
                return new UniqueMat();
            }

        } else if (type.equals(Type.LEATHER)) {

            if (rarity == 0) {
                return new CommonLeatherMat();
            } else if (rarity == 1) {
                return new UncommonLeatherMat();
            } else if (rarity == 2) {
                return new RareLeatherMat();
            } else if (rarity == 3) {
                return new EpicLeatherMat();
            } else if (rarity == 4) {
                return new LegendaryLeatherMat();
            } else if (rarity == 5) {
                return new MythicLeatherMat();
            } else if (rarity == IRarity.Unique) {
                return new UniqueLeatherMat();
            }

        } else if (type.equals(Type.CLOTH)) {

            if (rarity == 0) {
                return new CommonClothMat();
            } else if (rarity == 1) {
                return new UncommonClothMat();
            } else if (rarity == 2) {
                return new RareClothMat();
            } else if (rarity == 3) {
                return new EpicClothMat();
            } else if (rarity == 4) {
                return new LegendaryClothMat();
            } else if (rarity == 5) {
                return new MythicClothMat();
            } else if (rarity == IRarity.Unique) {
                return new UniqueClothMat();
            }
        }

        return new CommonMat();

    }

}