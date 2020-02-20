package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.packets.particles.ParticleEnum;
import com.robertx22.mine_and_slash.packets.particles.ParticlePacketData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityFinder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;

public class WandWeaponMechanic extends WeaponMechanic {

    public static WandWeaponMechanic INSTANCE = new WandWeaponMechanic();

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Normal Damage");
    }

    @Override
    public float energyCostLevelOne() {
        return 3;
    }

    @Override
    public float manaCostLevelOne() {
        return 1;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Wand;
    }

    public boolean powerAttack(LivingHurtEvent event, LivingEntity source, LivingEntity target, UnitData unitsource,
                               UnitData targetUnit, float multi) {

        double RADIUS = 1.5F;

        List<LivingEntity> entities = EntityFinder.start(source, LivingEntity.class, target.getPositionVector())
            .radius(RADIUS)
            .addPredicate(x -> x != target)
            .build();

        ParticleEnum.sendToClients(
            target, new ParticlePacketData(target.getPosition(), ParticleEnum.NOVA).type(ParticleTypes.CRIT)
                .amount(20));

        int val = (int) unitsource.getUnit()
            .getCreateStat(PhysicalDamage.GUID).val;
        DamageEffect dmg1 = new DamageEffect(
            event, source, target, val, unitsource, targetUnit, EffectData.EffectTypes.BASIC_ATTACK, weaponType());
        dmg1.setMultiplier(multi);
        dmg1.Activate();

        for (LivingEntity entity : entities) {
            EntityCap.UnitData targetdata = Load.Unit(entity);

            int num = (int) unitsource.getUnit()
                .getCreateStat(PhysicalDamage.GUID).val;
            DamageEffect dmg = new DamageEffect(
                null, source, entity, num, unitsource, targetdata, EffectData.EffectTypes.SPELL, weaponType());
            dmg.setMultiplier(multi);
            dmg.Activate();
        }

        return true;
    }

    @Override
    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target, UnitData unitsource,
                          UnitData targetUnit) {

        int val = (int) unitsource.getUnit()
            .getCreateStat(PhysicalDamage.GUID).val;
        DamageEffect dmg1 = new DamageEffect(
            event, source, target, val, unitsource, targetUnit, EffectData.EffectTypes.BASIC_ATTACK, weaponType());
        dmg1.setMultiplier(1);
        dmg1.Activate();

        return true;

    }
}

