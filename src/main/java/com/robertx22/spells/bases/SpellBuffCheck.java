package com.robertx22.spells.bases;

import com.robertx22.effectdatas.interfaces.IBuffableSpell;
import com.robertx22.spells.bases.BaseSpell.SpellType;

public class SpellBuffCheck implements IBuffableSpell {

    public SpellBuffCheck(SpellType type) {
	this.type = type;
    }

    SpellBuffType buff = SpellBuffType.None;
    SpellType type;

    @Override
    public void setBuff(SpellBuffType buff) {
	this.buff = buff;

    }

    @Override
    public SpellBuffType getBuff() {
	return buff;
    }

    @Override
    public void setType(SpellType type) {
	this.type = type;

    }

    @Override
    public SpellType getType() {
	return this.type;
    }

}
