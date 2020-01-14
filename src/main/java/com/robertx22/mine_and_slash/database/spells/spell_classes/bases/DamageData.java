package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;

public class DamageData {

    public DamageData(LivingEntity caster, SpellItemData spellItemData) {
        this.caster = caster;
        this.spellItemData = spellItemData;
        this.casterData = Load.Unit(caster);

    }

    public EntityCap.UnitData casterData;
    public LivingEntity caster;
    public SpellItemData spellItemData;

}
