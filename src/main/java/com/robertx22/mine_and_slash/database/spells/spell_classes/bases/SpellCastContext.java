package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs.CalculatedSpellConfigs;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashMap;

public class SpellCastContext {

    public final LivingEntity caster;
    public final EntityCap.UnitData data;
    public final PlayerSpellCap.ISpellsCap spellsCap;
    public final int ticksInUse;
    public final BaseSpell spell;
    public final IAbility ability;
    public final boolean isLastCastTick;

    private HashMap<String, CalculatedSpellConfigs> cacheMap = new HashMap<>();

    public CalculatedSpellConfigs getConfigFor(IAbility ability) {

        if (!cacheMap.containsKey(ability.GUID())) {
            cacheMap.put(ability.GUID(), new CalculatedSpellConfigs(data, spellsCap, ability));
        }

        return cacheMap.get(ability.GUID());
    }

    public SpellCastContext(LivingEntity caster, int ticksInUse, IAbility ability) {
        this.caster = caster;
        this.ticksInUse = ticksInUse;

        this.ability = ability;

        this.spell = ability.getSpell();

        this.data = Load.Unit(caster);

        if (caster instanceof PlayerEntity) {
            this.spellsCap = Load.spells((PlayerEntity) caster);
        } else {
            this.spellsCap = new PlayerSpellCap.DefaultImpl();
        }

        this.isLastCastTick = getConfigFor(ability.getSpell()).castTimeTicks == ticksInUse;
    }
}
