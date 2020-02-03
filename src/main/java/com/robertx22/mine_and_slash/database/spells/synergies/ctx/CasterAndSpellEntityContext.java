package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class CasterAndSpellEntityContext extends CasterContext {

    public Entity spellEntity;

    public CasterAndSpellEntityContext(LivingEntity caster, Entity spellEntity) {
        super(caster);
        this.spellEntity = spellEntity;
    }
}
