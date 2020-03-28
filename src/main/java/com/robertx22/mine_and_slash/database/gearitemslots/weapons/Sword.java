package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemSword;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Sword extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Sword();

    private Sword() {

    }

    @Override
    public String resourceID() {
        return "sword";
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.TINY);

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(2);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Sword;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.THIEF;
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(1);
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "sword";
    }

    @Override
    public Item getDefaultItem() {
        return ItemSword.Items.get(0);
    }

    @Override
    public String locNameForLangFile() {
        return "Sword";
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemSword.Items;
    }

    @Override
    public int Weight() {
        return 1500;
    }

}
