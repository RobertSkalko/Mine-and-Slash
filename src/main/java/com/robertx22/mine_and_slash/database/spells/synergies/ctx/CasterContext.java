package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class CasterContext extends SynergyContext {

    public LivingEntity caster;
    public EntityCap.UnitData casterData;

    public CasterContext(LivingEntity caster) {
        this.caster = caster;
        this.casterData = Load.Unit(caster);
    }
}
