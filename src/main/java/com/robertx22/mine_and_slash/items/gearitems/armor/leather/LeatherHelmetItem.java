package com.robertx22.mine_and_slash.items.gearitems.armor.leather;

import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.items.gearitems.bases.BaseArmorItem;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;

import java.util.HashMap;

public class LeatherHelmetItem extends BaseArmorItem {
    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    /*
    @Override
    public int getColor(ItemStack stack) {
        CompoundNBT compoundnbt = stack.getChildTag("display");
        return compoundnbt != null && compoundnbt.contains("color", 99) ? compoundnbt.getInt("color") : TextFormatting.RED
                .getColor();
    }

     */

    public LeatherHelmetItem(int rarity) {
        super(Type.LEATHER, rarity, EquipmentSlotType.HEAD);

    }

    @Override
    public String locNameForLangFile() {
        Rarity rar = Rarities.Items.get(rarity);
        return rar.textFormatColor() + "Leather Hat";
    }
}
