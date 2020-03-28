package com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;
import java.util.List;

public abstract class WeaponMechanic implements IGUID {

    public List<ITextComponent> tooltipDesc() {
        return Arrays.asList(

        );
    }

    protected boolean isPoweredAttack(DamageEventData data) {
        return data.sourceData.isAttackCooldownInSweepRange();
    }

    protected void doSpecialAttack(DamageEventData data) {
        doNormalAttack(data);
    }

    protected void doNormalAttack(DamageEventData data) {

        int num = (int) data.sourceData.getUnit()
            .getCreateStat(PhysicalDamage.GUID).val;
        DamageEffect dmg = new DamageEffect(
            data.event, data.source, data.target, num, data.sourceData, data.targetData, EffectTypes.BASIC_ATTACK, data.weaponData.GetBaseGearType()
            .weaponType());

        dmg.setMultiplier(data.multiplier);

        dmg.Activate();

    }

    public void attack(DamageEventData data) {
        if (isPoweredAttack(data)) {
            data.multiplier = data.weaponData.GetBaseGearType()
                .weaponDamageMulti().poweredDmgMulti;
            doSpecialAttack(data);
        } else {
            data.multiplier = data.weaponData.GetBaseGearType()
                .weaponDamageMulti().normalDmgMulti;
            doNormalAttack(data);
        }
    }
}
