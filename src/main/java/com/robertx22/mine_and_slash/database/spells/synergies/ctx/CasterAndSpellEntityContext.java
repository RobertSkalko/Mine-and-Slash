package com.robertx22.mine_and_slash.database.spells.synergies.ctx;

import net.minecraft.entity.LivingEntity;

public class CasterAndSpellEntityContext<T> extends CasterContext {

    public T spellEntity;

    public CasterAndSpellEntityContext(LivingEntity caster, T spellEntity) {
        super(caster);
        this.spellEntity = spellEntity;

    }
}
