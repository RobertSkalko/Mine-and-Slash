package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.onevent.entity.damage.DamageEventData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class HammerWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Aoe Attack/Single Double Damage");
    }

    @Override
    public float energyCostLevelOne() {
        return 10;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Hammer;
    }

    float radius = 1.2F;

    @Override
    public float getNormalDamageMulti() {
        return 2;
    }

    @Override
    public float getPoweredAttackDamageMulti() {
        return 1;
    }

    @Override
    protected boolean isPoweredAttack(DamageEventData data) {

        if (data.sourceData.isAttackCooldownInSweepRange()) {
            List<LivingEntity> targets = EntityFinder.start(data.source, LivingEntity.class, data.target.getPositionVector())
                .radius(radius)
                .build();

            if (targets.size() > 1) {
                return true;
            }
        }

        return false;

    }

    @Override
    public void doSpecialAttack(DamageEventData data) {

        int num = (int) data.sourceData.getUnit()
            .getCreateStat(PhysicalDamage.GUID).val;

        List<LivingEntity> targets = EntityFinder.start(data.source, LivingEntity.class, data.target.getPositionVector())
            .radius(radius)
            .build();

        for (LivingEntity en : targets) {

            if (en.equals(data.target)) {
                DamageEffect dmg = new DamageEffect(data.event, data.source, en, num, data.sourceData, data.targetData,
                    EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer
                );
                dmg.Activate();
            } else {
                DamageEffect dmg = new DamageEffect(null, data.source, en, num, data.sourceData, data.targetData,
                    EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer
                );
                dmg.Activate();
            }

        }
    }

}
