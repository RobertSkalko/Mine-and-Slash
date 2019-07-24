package com.robertx22.mine_and_slash.database.stats.stat_effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IHasSpellEffect;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class BonusSpecificSpellEffect implements IStatEffect {

    public BonusSpecificSpellEffect(BaseSpell spell) {
        this.spell = spell;
    }

    public BaseSpell spell;

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof IHasSpellEffect) {

                IHasSpellEffect dmg = (IHasSpellEffect) Effect;

                if (dmg.getSpell().GUID().equals(spell.GUID())) {
                    Effect.number *= data.getMultiplier();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
