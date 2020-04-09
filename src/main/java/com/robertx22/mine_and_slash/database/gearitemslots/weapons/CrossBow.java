package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.data_generation.wrappers.StatModsHolder;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponDamageMulti;
import com.robertx22.mine_and_slash.database.gearitemslots.WeaponSwingCost;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.ArmorPenetrationFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.unique_items.StatReq;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;

public class CrossBow extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new CrossBow();

    private CrossBow() {

    }

    @Override
    public String resourceID() {
        return "crossbow";
    }

    static StatReq req = new StatReq(
        LvlPointStat.DEXTERITY, StatReq.Size.SMALL, LvlPointStat.STRENGTH, StatReq.Size.TINY);

    @Override
    public WeaponDamageMulti weaponDamageMulti() {
        return new WeaponDamageMulti(3);
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
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "crossbow";
    }

    @Override
    public Item getDefaultItem() {
        return Items.CROSSBOW;
    }

    @Override
    public StatModsHolder getPossibleSecondaryStats() {
        return new StatModsHolder(new CriticalDamageFlat(), new CriticalHitFlat(), new ArmorPenetrationFlat());
    }

    @Override
    public WeaponSwingCost getSwingCosts() {
        return new WeaponSwingCost(7);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.CrossBow;
    }

    @Override
    public HashMap<Integer, Item> getItemsForRaritiesMap() {
        return new HashMap<>();
    }

    @Override
    public int Weight() {
        return 750;
    }

    @Override
    public String locNameForLangFile() {
        return "Crossbow";
    }
}
