package com.robertx22.mine_and_slash.database.gearitemslots.weapons;

import com.robertx22.mine_and_slash.database.StatModifier;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.BaseWeapon;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.stats.types.generated.WeaponDamage;
import com.robertx22.mine_and_slash.database.stats.types.offense.CriticalHit;
import com.robertx22.mine_and_slash.database.stats.types.resources.Lifesteal;
import com.robertx22.mine_and_slash.mmorpg.registers.common.ModItems;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.ModType;
import net.minecraft.item.Item;

import java.util.Arrays;
import java.util.List;

public class GemstoneSword extends BaseWeapon {
    public static GearItemSlot INSTANCE = new GemstoneSword();

    private GemstoneSword() {

    }

    @Override
    public List<StatModifier> BaseStats() {
        return Arrays.asList(
            new StatModifier(1, 3, 2, 4, new WeaponDamage(Elements.Physical), ModType.FLAT),
            new StatModifier(2, 6, CriticalHit.getInstance(), ModType.FLAT)

        );
    }

    @Override
    public List<StatModifier> ImplicitStats() {
        return Arrays.asList(new StatModifier(1, 3, Lifesteal.getInstance(), ModType.FLAT));
    }

    @Override
    public List<SlotTag> getTags() {
        return Arrays.asList(SlotTag.Sword, SlotTag.MeleeWeapon);
    }

    @Override
    public Item getItem() {
        return ModItems.GEMSTONE_SWORD.get();
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Sword;
    }

    @Override
    public PlayStyle getPlayStyle() {
        return PlayStyle.DEX;
    }

    @Override
    public String GUID() {
        return "gemstone_sword";
    }

    @Override
    public String locNameForLangFile() {
        return "Gemstone Sword";
    }

    @Override
    public int Weight() {
        return 1500;
    }

}
