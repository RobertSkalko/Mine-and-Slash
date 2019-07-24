package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;

public abstract class WeaponMechanic {

    public abstract ITextComponent tooltipDesc();

    public abstract float GetEnergyCost();

    public abstract WeaponTypes weaponType();

    public boolean Attack(LivingEntity source, LivingEntity target, UnitData unitsource,
                          UnitData targetUnit) {

        int num = (int) unitsource.getUnit().getStat(PhysicalDamage.GUID).Value;
        DamageEffect dmg = new DamageEffect(source, target, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, weaponType());

        dmg.Activate();

        return true;
    }

    public boolean multiplyDamage(LivingEntity source, LivingEntity target,
                                  UnitData unitsource, UnitData targetUnit,
                                  float multiplier) {

        int num = (int) unitsource.getUnit().getStat(PhysicalDamage.GUID).Value;
        DamageEffect dmg = new DamageEffect(source, target, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, weaponType());

        dmg.setMultiplier(multiplier);

        dmg.Activate();

        return true;
    }

}
