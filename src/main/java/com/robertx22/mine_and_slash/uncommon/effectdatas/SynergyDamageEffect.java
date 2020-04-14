package com.robertx22.mine_and_slash.uncommon.effectdatas;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.synergies.base.Synergy;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import net.minecraft.entity.LivingEntity;

public class SynergyDamageEffect extends SpellDamageEffect {

    public Synergy synergy;

    public SynergyDamageEffect(Synergy synergy, LivingEntity source, LivingEntity target, int dmg, EntityCap.UnitData sourceData, EntityCap.UnitData targetData, BaseSpell spell) {
        super(source, target, dmg, sourceData, targetData, spell);

        this.doNotActivateSynergies();
        this.synergy = synergy;

    }
}
