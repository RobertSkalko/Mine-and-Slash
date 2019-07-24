package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

public class HammerWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Aoe Attack");
    }

    @Override
    public float GetEnergyCost() {
        return 10;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Hammer;
    }

    float radius = 1.5F;

    @Override
    public boolean Attack(LivingEntity source, LivingEntity target, UnitData unitsource,
                          UnitData targetUnit) {

        List<LivingEntity> entities = new ArrayList<LivingEntity>();

        for (Entity en : target.world.getEntitiesWithinAABBExcludingEntity(source, new AxisAlignedBB(target.posX - radius, target.posY - radius, target.posZ - radius, target.posX + radius, target.posY + radius, target.posZ + radius))) {
            if (en instanceof LivingEntity) {
                entities.add((LivingEntity) en);
            }
        }
        int num = (int) unitsource.getUnit().getStat(PhysicalDamage.GUID).Value;

        for (LivingEntity entity : entities) {

            DamageEffect dmg = new DamageEffect(source, entity, num, unitsource, targetUnit, EffectData.EffectTypes.BASIC_ATTACK, WeaponTypes.Hammer);
            dmg.Activate();
        }

        return true;
    }

}
