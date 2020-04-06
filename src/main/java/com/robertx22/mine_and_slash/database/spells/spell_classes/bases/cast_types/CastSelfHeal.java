package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types;

import com.robertx22.mine_and_slash.database.spells.SpellUtils;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;

public class CastSelfHeal extends SpellCastType {

    @Override
    public boolean cast(SpellCastContext ctx) {

        SpellUtils.healCaster(ctx);

        return true;
    }
}
