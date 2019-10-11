package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class BowWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Quadruple Damage");
    }

    @Override
    public float GetEnergyCost(int lvl) {

        return Energy.INSTANCE.calculateScalingStatGrowth(18, lvl);

    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Bow;
    }

    @Override
    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target,
                          EntityCap.UnitData unitsource, EntityCap.UnitData targetUnit) {

        super.multiplyDamage(event, source, target, unitsource, targetUnit, 4);

        return true;
    }
}
