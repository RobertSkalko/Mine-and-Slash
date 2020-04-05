package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.CalculatedSpellConfigs;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class SpellCastContext {

    public final LivingEntity caster;
    public final EntityCap.UnitData data;
    public final PlayerSpellCap.ISpellsCap spellsCap;
    public final int ticksInUse;
    public final BaseSpell spell;
    public final IAbility ability;
    public final CalculatedSpellConfigs finishedConfig;
    public final boolean isLastCastTick;
    public final int spellLevel;

    public SpellCastContext(LivingEntity caster, int ticksInUse, IAbility ability) {
        this.caster = caster;
        this.ticksInUse = ticksInUse;

        this.ability = ability;

        if (ability.getAbilityType()
            .equals(IAbility.Type.SPELL)) {
            this.spell = (BaseSpell) ability;
        } else {
            this.spell = null;
        }

        this.data = Load.Unit(caster);

        if (caster instanceof PlayerEntity) {
            this.spellsCap = Load.spells((PlayerEntity) caster);
        } else {
            this.spellsCap = new PlayerSpellCap.DefaultImpl();
        }

        this.finishedConfig = new CalculatedSpellConfigs(this);

        this.spellLevel = spellsCap.getLevelOf(spell);

        this.isLastCastTick = finishedConfig.castTimeTicks == ticksInUse;
    }
}
