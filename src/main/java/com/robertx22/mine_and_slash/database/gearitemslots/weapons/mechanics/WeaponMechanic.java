package com.robertx22.mine_and_slash.database.gearitemslots.weapons.mechanics;

import com.robertx22.mine_and_slash.database.IGUID;
import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import net.minecraft.util.text.ITextComponent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public abstract class WeaponMechanic implements IGUID {

    private static HashMap<String, WeaponMechanic> ALL = new HashMap<String, WeaponMechanic>() {
        {
            {
                put(new HammerWeaponMechanic().GUID(), new HammerWeaponMechanic());
                put(new NormalWeaponMechanic().GUID(), new NormalWeaponMechanic());
            }
        }
    };

    public static WeaponMechanic get(String id) {
        return ALL.getOrDefault(id, new NormalWeaponMechanic());
    }

    public List<ITextComponent> tooltipDesc() {
        return Arrays.asList();
    }

    protected boolean isPoweredAttack(DamageEventData data) {
        return data.sourceData.isAttackCooldownInSweepRange();
    }

    protected void doSpecialAttack(DamageEventData data) {
        doNormalAttack(data);
    }

    protected void doNormalAttack(DamageEventData data) {

        int num = (int) data.sourceData.getUnit()
            .getCreateStat(PhysicalDamage.GUID)
            .getRandomRangeValue();
        DamageEffect dmg = new DamageEffect(
            data.event, data.source, data.target, num, data.sourceData, data.targetData, EffectTypes.BASIC_ATTACK, data.weaponData.GetBaseGearType()
            .weaponType());

        dmg.setMultiplier(data.multiplier);

        dmg.Activate();

    }

    public void attack(DamageEventData data) {

        data.multiplier = 1;

    }
}
