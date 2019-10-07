package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.flat.offense.CriticalHitFlat;
import com.robertx22.mine_and_slash.database.stats.stat_mods.generated.ElementalPeneFlat;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.CrossBowWeaponMechanic;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CrossBow extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new CrossBow();

    private CrossBow() {

    }

    @Override
    public String resourceID() {
        return "crossbow";
    }

    static StatReq req = new StatReq(LvlPointStat.DEXTERITY, StatReq.Size.SMALL, LvlPointStat.STRENGTH, StatReq.Size.TINY);

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "Crossbow";
    }

    @Override
    public Item DefaultItem() {
        return Items.CROSSBOW;
    }

    @Override
    public boolean isGearOfThisType(Item item) {
        return item instanceof CrossbowItem;
    }

    @Override
    public List<StatMod> PossibleSecondaryStats() {
        return Arrays.asList(new CriticalDamageFlat(), new CriticalHitFlat(), new ElementalPeneFlat(Elements.Physical));
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return new HashMap<>();
    }

    @Override
    public int Weight() {
        return 750;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new CrossBowWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Crossbow";
    }
}
