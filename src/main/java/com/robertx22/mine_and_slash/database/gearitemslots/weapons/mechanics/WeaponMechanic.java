package com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public abstract class WeaponMechanic {

    protected float normalDmgMulti = 1;
    protected float poweredDmgMulti = 1;

    public WeaponMechanic(float normalDmgMulti, float poweredDmgMulti) {
        this.normalDmgMulti = normalDmgMulti;
        this.poweredDmgMulti = poweredDmgMulti;
    }

    public WeaponMechanic(float multi) {
        this.normalDmgMulti = multi;
        this.poweredDmgMulti = multi;
    }

    public List<ITextComponent> tooltipDesc() {
        return Arrays.asList(
            new StringTextComponent(TextFormatting.GREEN + "" + normalDmgMulti + "x DMG (normal)"),
            new StringTextComponent(TextFormatting.GREEN + "" + poweredDmgMulti + "x DMG (powered)")
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
            data.multiplier = poweredDmgMulti;
            doSpecialAttack(data);
        } else {
            data.multiplier = normalDmgMulti;
            doNormalAttack(data);
        }
    }
}
