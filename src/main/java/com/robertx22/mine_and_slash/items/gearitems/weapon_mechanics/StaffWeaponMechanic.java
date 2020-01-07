package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class StaffWeaponMechanic extends WeaponMechanic {

    public static StaffWeaponMechanic INSTANCE = new StaffWeaponMechanic();

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Double Damage");
    }

    @Override
    public float GetEnergyCost(int lvl) {
        return Energy.INSTANCE.calculateScalingStatGrowth(6, lvl);

    }

    @Override
    public float GetManaCost(int lvl) {
        return Mana.INSTANCE.calculateScalingStatGrowth(3, lvl);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Staff;
    }

    public boolean powerAttack(LivingHurtEvent event, LivingEntity source,
                               LivingEntity target, UnitData unitsource,
                               UnitData targetUnit, float multi) {
        super.multiplyDamage(event, source, target, unitsource, targetUnit, 2 * multi);

        return true;
    }

    @Override
    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target,
                          UnitData unitsource, UnitData targetUnit) {

        super.multiplyDamage(event, source, target, unitsource, targetUnit, 2);

        return true;
    }
}
