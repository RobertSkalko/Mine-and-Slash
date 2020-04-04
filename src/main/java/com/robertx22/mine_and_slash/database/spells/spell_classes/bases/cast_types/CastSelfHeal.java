package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.SpellHealEffect;

public class CastSelfHeal extends SpellCastType {

    @Override
    public boolean cast(SpellCastContext ctx) {

        SpellHealEffect heal = new SpellHealEffect(
            new ResourcesData.Context(ctx.data, ctx.caster, ResourcesData.Type.HEALTH,
                ctx.finishedConfig.calc.getCalculatedValue(ctx.data), ResourcesData.Use.RESTORE,
                ctx.spell
            ));

        heal.Activate();

        return true;
    }
}
