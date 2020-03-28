package com.robertx22.mine_and_slash.items.gearitems.bases;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import net.minecraft.util.text.ITextComponent;

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

    public float getNormalDamageMulti() {
        return 1;
    }

    public float getPoweredAttackDamageMulti() {
        return 1;
    }

    public final float GetEnergyCost(int lvl) {
        return Energy.getInstance()
            .calculateScalingStatGrowth(energyCostLevelOne(), lvl);
    }

    public abstract WeaponTypes weaponType();

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
            data.event, data.source, data.target, num, data.sourceData, data.targetData, EffectTypes.BASIC_ATTACK, weaponType());

        dmg.setMultiplier(data.multiplier);

        dmg.Activate();

    }

    public void attack(DamageEventData data) {
        if (isPoweredAttack(data)) {
            data.multiplier = getPoweredAttackDamageMulti();
            doSpecialAttack(data);
        } else {
            data.multiplier = getNormalDamageMulti();
            doNormalAttack(data);
        }
    }
}
