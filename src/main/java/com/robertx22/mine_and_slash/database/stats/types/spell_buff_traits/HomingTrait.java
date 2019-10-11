package com.robertx22.mine_and_slash.database.stats.types.spell_buff_traits;

import com.robertx22.mine_and_slash.database.stats.effects.spell_buffs.HomingBuff;
import com.robertx22.mine_and_slash.database.stats.types.spell_buff_traits.base.SpellBuffTrait;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class HomingTrait extends SpellBuffTrait {

    public static String GUID = "Homing";

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public IStatEffect getEffect() {
        return new HomingBuff();
    }

    @Override
    public String locNameForLangFile() {
        return "Homing Projectile";
    }

    @Override
    public String locDescForLangFile() {
        return "Your Single Target Spell Projectiles now Home in on the target.";
    }
}
