package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.StatModTypes;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class Sword extends BaseWeapon {
    public static GearItemSlot INSTANCE = new Sword();

    private Sword() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(1, 3, 2, 5, new WeaponDamage(Elements.Physical), StatModTypes.Flat),
            new StatModifier(2, 6, CriticalHit.getInstance(), StatModTypes.Flat)

        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList();
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Sword);
    }

    @Override
    public Item getItem() {
        return ModItems.SWORD.get();
    }

    @Override
    public boolean isMeleeWeapon() {
        return true;
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
    public String GUID() {
        return "sword";
    }

    @Override
    public String locNameForLangFile() {
        return "Sword";
    }

    @Override
    public int Weight() {
        return 1500;
    }

}
