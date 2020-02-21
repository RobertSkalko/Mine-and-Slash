package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.entity.LivingEntity;

public class AfterDamageContext extends SynergyContext {

    public LivingEntity caster, target;
    public EntityCap.UnitData casterData, targetData;

    public DamageEffect dmg;

    public AfterDamageContext(LivingEntity caster, LivingEntity target, DamageEffect dmg) {
        this.caster = caster;
        this.target = target;
        this.dmg = dmg;
        this.casterData = Load.Unit(caster);
        this.targetData = Load.Unit(target);
    }
}

