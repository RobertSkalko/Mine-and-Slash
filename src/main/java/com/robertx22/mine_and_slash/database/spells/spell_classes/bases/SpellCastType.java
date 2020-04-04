package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.CastAtSight;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.CastGiveEffect;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.CastProjectile;

public abstract class SpellCastType {

    public static CastAtSight AT_SIGHT = new CastAtSight();
    public static CastGiveEffect GIVE_EFFECT = new CastGiveEffect();
    public static CastProjectile PROJECTILE = new CastProjectile();

    public abstract boolean cast(SpellCastContext ctx);

}
