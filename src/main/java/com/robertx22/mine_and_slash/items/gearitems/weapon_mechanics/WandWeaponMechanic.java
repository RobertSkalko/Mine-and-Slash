package com.robertx22.mine_and_slash.items.gearitems.weapon_mechanics;

import com.robertx22.mine_and_slash.items.gearitems.bases.WeaponMechanic;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
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

public class WandWeaponMechanic extends WeaponMechanic {

    @Override
    public ITextComponent tooltipDesc() {
        return new StringTextComponent(Styles.GREEN + "Normal Damage");
    }

    @Override
    public float GetEnergyCost() {
        return 1;
    }

    @Override
    public float GetManaCost() {
        return 3;
    }

    @Override
    public WeaponTypes weaponType() {
        return WeaponTypes.Wand;
    }

    @Override
    public boolean Attack(LivingHurtEvent event, LivingEntity source, LivingEntity target,
                          UnitData unitsource, UnitData targetUnit) {

        double RANGE = 4;

        List<LivingEntity> entities = Utilities.getEntitiesWithinRadius(RANGE, target, LivingEntity.class);

        if (entities.size() == 1) {

        } else if (entities.size() > 1) {
            unitsource.consumeMana(this.GetManaCost());
            unitsource.consumeEnergy(this.GetEnergyCost());
            ElementalParticleUtils.SpawnNovaParticle(Elements.Physical, target, RANGE, 200);
        }

        for (LivingEntity entity : entities) {
            EntityCap.UnitData targetdata = Load.Unit(entity);
            super.multiplyDamage(event, source, target, unitsource, targetdata, 1);
        }

        return true;

    }
}

