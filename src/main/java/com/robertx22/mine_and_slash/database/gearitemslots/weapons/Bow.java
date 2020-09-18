package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemBow;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Bow extends BaseWeapon implements ISpecificStatReq {

    public static Bow INSTANCE = new Bow();

    private Bow() {

    }

    @Override
    public String resourceID() {
        return "bow";
    }

    static StatReq req = new StatReq(LvlPointStat.DEXTERITY, StatReq.Size.MEDIUM);

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(3.5F);
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.THIEF;
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(20);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Bow;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "bow";
    }

    @Override
    public Item getDefaultItem() {
        return ItemBow.Items.get(0);
    }

    @Override
    public String locNameForLangFile() {
        return "Bow";
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return ItemBow.Items;
    }

}
