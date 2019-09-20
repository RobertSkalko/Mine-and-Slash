package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials.*;
import com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials.cloth.CommonClothMat;
import com.robertx22.mine_and_slash.items.gearitems.bases.armor_materials.leather.CommonLeatherMat;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGearItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.IRarity;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ItemUtils;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;

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

    private static IArmorMaterial GetMat(Type type, int rarity) {

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
            return new CommonLeatherMat(); // TEMP TODO

        } else if (type.equals(Type.CLOTH)) {
            return new CommonClothMat(); // TEMP TODO
        }

        return new CommonMat();

    }

}