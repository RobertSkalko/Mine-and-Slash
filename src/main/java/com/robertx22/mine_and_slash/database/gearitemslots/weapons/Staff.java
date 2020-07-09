package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.NormalWeaponMechanic;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics.WeaponMechanic;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class Staff extends BaseWeapon {
    public static GearItemSlot INSTANCE = new Staff();

    private Staff() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(2, 7, new WeaponDamage(Elements.Physical), StatModTypes.Flat),
            new StatModifier(3, 10, CriticalHit.getInstance(), StatModTypes.Flat)

        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public String resourceID() {
        return "staff";
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.MageWeapon, SlotTag.Staff);
    }

    @Override
    public Item getItem() {
        return ModItems.STAFF.get();
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.MAGE;
    }

    @Override
    public String GUID() {
        return "staff";
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Staff;
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Staff";
    }
}