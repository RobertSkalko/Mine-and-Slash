package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public abstract class WeaponMechanic {

    public abstract ITextComponent tooltipDesc();

    public abstract float energyCostLevelOne();

    public float manaCostLevelOne() {
        return 0;
    }

    public final float GetManaCost(int lvl) {
        return Mana.getInstance()
            .calculateScalingStatGrowth(manaCostLevelOne(), lvl);
    }

    public final float GetEnergyCost(int lvl) {
        return Energy.getInstance()
            .calculateScalingStatGrowth(energyCostLevelOne(), lvl);
    }

    public abstract WeaponTypes weaponType();

    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target, UnitData unitsource,
                          UnitData targetUnit) {

        int num = (int) unitsource.getUnit()
            .getCreateStat(PhysicalDamage.GUID).val;
        DamageEffect dmg = new DamageEffect(
            event, source, target, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, weaponType());

        dmg.Activate();

        return true;
    }

    public boolean multiplyDamage(LivingHurtEvent event, LivingEntity source, LivingEntity target, UnitData unitsource,
                                  UnitData targetUnit, float multiplier) {

        int num = (int) unitsource.getUnit()
            .getCreateStat(PhysicalDamage.GUID).val;
        DamageEffect dmg = new DamageEffect(
            event, source, target, num, unitsource, targetUnit, EffectTypes.BASIC_ATTACK, weaponType());
        dmg.setMultiplier(multiplier);
        dmg.Activate();

        return true;
    }

}
