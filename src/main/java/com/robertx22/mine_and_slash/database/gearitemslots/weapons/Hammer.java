package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.HammerWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemHammer;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Hammer extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Hammer();

    private Hammer() {

    }

    @Override
    public String resourceID() {
        return "hammer";
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Hammer;
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new HammerWeaponMechanic();
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(10);
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    static StatReq req = new StatReq(
        LvlPointStat.STRENGTH, StatReq.Size.SMALL, LvlPointStat.VITALITY, StatReq.Size.SMALL);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "hammer";
    }

    @Override
    public Item getDefaultItem() {
        return ItemHammer.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemHammer.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Hammer";
    }
}
