package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import net.minecraft.entity.LivingEntity;

public class HealData {

    public HealData(LivingEntity target, UnitData data, int num) {
        this.target = target;
        this.source = target;
        this.number = num;
        this.sourceData = data;
        this.targetData = data;
    }

    public HealData(LivingEntity source, UnitData sourceData, LivingEntity target,
                    UnitData targetData, int num) {
        this.target = target;
        this.source = source;
        this.number = num;
        this.sourceData = sourceData;
        this.targetData = targetData;
    }

    public HealData bySpell(BaseSpell spell) {
        this.spell = spell;
        return this;
    }

    public BaseSpell spell;

    public UnitData sourceData;
    public UnitData targetData;

    public LivingEntity source;
    public LivingEntity target;

    public float number = 0;

}
