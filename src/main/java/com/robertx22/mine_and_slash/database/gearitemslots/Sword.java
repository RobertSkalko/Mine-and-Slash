package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.SwordWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemSword;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Sword extends BaseWeapon {

    @Override
    public String GUID() {
        return "Sword";
    }

    @Override
    public Item DefaultItem() {
        return ItemSword.Items.get(0);
    }

    @Override
    public String locNameForLangFile() {
        return "Sword";
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemSword.Items;
    }

    @Override
    public int Weight() {
        return 1500;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new SwordWeaponMechanic();
    }
}
