package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.AxeWeaponMechanic;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;

import java.util.HashMap;

public class Trident extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Trident();

    private Trident() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.SMALL);

    @Override
    public Item getBaseUniqueItem() {
        return null;
    }

    @Override
    public boolean isMeleeWeapon() {
        return false;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public String resourceID() {
        return "trident";
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "trident";
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof TridentItem;
    }

    @Override
    public Item getDefaultItem() {
        return Items.TRIDENT;
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return new HashMap<>();
    }

    @Override
    public int Weight() {
        return 800;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new AxeWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Trident";
    }
}