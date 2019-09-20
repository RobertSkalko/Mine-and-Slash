package com.robertx22.mine_and_slash.database.gearitemslots;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.HammerWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemHammer;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Hammer extends BaseWeapon {
    public static GearItemSlot INSTANCE = new Hammer();

    private Hammer() {

    }

    @Override
    public String GUID() {
        return "Hammer";
    }

    @Override
    public Item DefaultItem() {
        return ItemHammer.Items.get(0);
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return false;
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemHammer.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new HammerWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Hammer";
    }
}
