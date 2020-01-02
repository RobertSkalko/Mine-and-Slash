package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.database.stats.types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ElementalParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Utilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.List;
import java.util.stream.Collectors;

public class WandWeaponMechanic extends WeaponMechanic {

    public static WandWeaponMechanic INSTANCE = new WandWeaponMechanic();

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Normal Damage");
    }

    @Override
    public float GetEnergyCost(int lvl) {

        return Energy.INSTANCE.calculateScalingStatGrowth(2, lvl);

    }

    @Override
    public float GetManaCost(int lvl) {
        return Mana.INSTANCE.calculateScalingStatGrowth(2, lvl);
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Wand;
    }

    public boolean powerAttack(LivingHurtEvent event, LivingEntity source,
                               LivingEntity target, UnitData unitsource,
                               UnitData targetUnit, float multi) {

        double RANGE = 1.5F;

        List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(RANGE, target, LivingEntity.class)
                .stream()
                .filter(x -> x.equals(source) == false && x.equals(target) == false)
                .collect(Collectors.toList());

        ElementalParticleUtils.SpawnNovaParticle(Elements.Physical, target, RANGE, 200);

        int val = (int) unitsource.getUnit().getStat(PhysicalDamage.GUID).Value;
        DamageEffect dmg1 = new DamageEffect(event, source, target, val, unitsource, targetUnit, EffectData.EffectTypes.BASIC_ATTACK, weaponType());
        dmg1.setMultiplier(multi);
        dmg1.Activate();

        for (LivingEntity entity : entities) {
            EntityCap.UnitData targetdata = Load.Unit(entity);

            int num = (int) unitsource.getUnit().getStat(PhysicalDamage.GUID).Value;
            DamageEffect dmg = new DamageEffect(null, source, entity, num, unitsource, targetdata, EffectData.EffectTypes.SPELL, weaponType());
            dmg.setMultiplier(multi);
            dmg.Activate();
        }

        return true;
    }

    @Override
    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target,
                          UnitData unitsource, UnitData targetUnit) {

        int val = (int) unitsource.getUnit().getStat(PhysicalDamage.GUID).Value;
        DamageEffect dmg1 = new DamageEffect(event, source, target, val, unitsource, targetUnit, EffectData.EffectTypes.BASIC_ATTACK, weaponType());
        dmg1.setMultiplier(1);
        dmg1.Activate();

        return true;

    }
}

