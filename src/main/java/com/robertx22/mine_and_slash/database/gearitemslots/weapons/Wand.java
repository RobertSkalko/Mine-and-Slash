package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.ISpecificStatReq;
import com.robertx22.mine_and_slash.database.items.unique_items.StatReq;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.mods.flat.offense.PhysicalDamageFlat;
import com.robertx22.mine_and_slash.database.stats.mods.generated.ElementalAttackDamageFlat;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics.WandWeaponMechanic;
import com.robertx22.mine_and_slash.items.gearitems.weapons.ItemWand;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Wand extends BaseWeapon implements ISpecificStatReq {
    public static GearItemSlot INSTANCE = new Wand();

    private Wand() {

    }

    @Override
    public String resourceID() {
        return "wand";
    }

    @Override
    public List<StatMod> PrimaryStats() {
        return Arrays.asList(new PhysicalDamageFlat().multi(primaryStatMulti()), new PhysicalDamageFlat()
                .multi(primaryStatMulti()), new PhysicalDamageFlat().multi(primaryStatMulti()), new PhysicalDamageFlat()
                .multi(primaryStatMulti()), new PhysicalDamageFlat().multi(primaryStatMulti()), new PhysicalDamageFlat()
                .multi(primaryStatMulti()), new PhysicalDamageFlat().multi(primaryStatMulti()), new ElementalAttackDamageFlat(Elements.Water)
                .multi(primaryStatMulti()), new ElementalAttackDamageFlat(Elements.Fire).multi(primaryStatMulti()), new ElementalAttackDamageFlat(Elements.Thunder)
                .multi(primaryStatMulti()), new ElementalAttackDamageFlat(Elements.Nature)
                .multi(primaryStatMulti()));
    }

    @Override
    public int cooldownTicks() {
        return 40;
    }

    static StatReq req = new StatReq(LvlPointStat.WISDOM, StatReq.Size.MEDIUM);

    @Override
    public int primaryStatsAmount() {
        return 2;
    }

    @Override
    public StatReq getRequirements() {
        return req;
    }

    @Override
    public String GUID() {
        return "Wand";
    }

    @Override
    public Item DefaultItem() {
        return ItemWand.Items.get(0);
    }

    @Override
    public HashMap<Integer, Item> ItemsForRarities() {
        return ItemWand.Items;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public WeaponMechanic mechanic() {
        return new WandWeaponMechanic();
    }

    @Override
    public String locNameForLangFile() {
        return "Staff";
    }
}