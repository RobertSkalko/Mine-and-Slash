package com.robertx22.mine_and_slash.spells.bases;

import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class DamageData {

    public DamageData(LivingEntity entity, SpellItemData spellItem) {
        // this.effect = e;
        this.caster = entity;
        this.spellItem = spellItem;
        this.casterUnit = Load.Unit(entity);

    }

    public EntityCap.UnitData casterUnit;
    // public BaseSpellEffect effect;
    public LivingEntity caster;
    public SpellItemData spellItem;

}
