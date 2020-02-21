package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class CasterTargetContext extends SynergyContext {
    public LivingEntity caster, target;
    public EntityCap.UnitData casterData, targetData;

    public CasterTargetContext(LivingEntity caster, LivingEntity target) {
        this.caster = caster;
        this.target = target;
        this.casterData = Load.Unit(caster);
        this.targetData = Load.Unit(target);
    }
}
