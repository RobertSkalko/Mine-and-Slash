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

public class Axe extends BaseWeapon {
    public static GearItemSlot INSTANCE = new Axe();

    private Axe() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(2, 8, new WeaponDamage(Elements.Physical), StatModTypes.Flat),
            new StatModifier(4, 15, CriticalHit.getInstance(), StatModTypes.Flat)

        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
    }

    @Override
    public WeaponMechanic getWeaponMechanic() {
        return new NormalWeaponMechanic();
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.WARRIOR;
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Axe);
    }

    @Override
    public Item getItem() {
        return ModItems.AXE.get();
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Axe;
    }

    @Override
    public String GUID() {
        return "axe";
    }

    @Override
    public int Weight() {
        return 1000;
    }

    @Override
    public String locNameForLangFile() {
        return "Axe";
    }

}