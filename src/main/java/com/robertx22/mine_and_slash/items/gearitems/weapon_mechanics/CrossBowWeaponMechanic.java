package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CrossBowWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Triple Damage");
    }

    @Override
    public float energyCostLevelOne() {
        return 8;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.CrossBow;
    }

    @Override
    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target,
                          EntityCap.UnitData unitsource, EntityCap.UnitData targetUnit) {

        super.multiplyDamage(event, source, target, unitsource, targetUnit, 3);

        return true;
    }

}
