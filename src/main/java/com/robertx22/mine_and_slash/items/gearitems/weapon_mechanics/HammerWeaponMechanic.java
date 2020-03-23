package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

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
    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target, UnitData unitsource,
                          UnitData targetUnit) {

        int num = (int) unitsource.getUnit()
            .getCreateStat(PhysicalDamage.GUID).val;

        List<LivingEntity> targets = EntityFinder.start(source, LivingEntity.class, target.getPositionVector())
            .radius(radius)
            .build();

        if (unitsource.isAttackCooldownInSweepRange()) {
            if (targets.size() == 1) {
                num *= 2;
            }

            for (LivingEntity en : targets) {

                if (en.equals(target)) {
                    DamageEffect dmg = new DamageEffect(event, source, en, num, unitsource, targetUnit,
                        EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer
                    );
                    dmg.Activate();
                } else {
                    DamageEffect dmg = new DamageEffect(null, source, en, num, unitsource, targetUnit,
                        EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer
                    );
                    dmg.Activate();
                }

            }
        }

        return true;
    }

}
