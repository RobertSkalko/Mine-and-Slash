package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class AxeWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Double Damage");
    }

    @Override
    public float energyCostLevelOne() {
        return 8.5F;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Axe;
    }

    @Override
    public float getNormalDamageMulti() {
        return 2;
    }

    @Override
    public float getPoweredAttackDamageMulti() {
        return 2;
    }
}
