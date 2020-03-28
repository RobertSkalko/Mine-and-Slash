package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;

public class Trident extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Trident();

    private Trident() {

    }

    static StatReq req = new StatReq(LvlPointStat.STAMINA, StatReq.Size.SMALL);

    @Override
    public boolean isMeleeWeapon() {
        return false;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(2);
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(8.5F);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Trident;
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
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
    public String locNameForLangFile() {
        return "Trident";
    }
}