package com.robertx22.mine_and_slash.database.stats.stat_types.traits.cause_stats;

import com.robertx22.mine_and_slash.database.stats.stat_effects.OnCauseDoEffect;
import com.robertx22.mine_and_slash.database.stats.stat_effects.cause_effects.GivePotionEffect;
import com.robertx22.mine_and_slash.database.stats.stat_effects.causes.OnAttackDodgedCause;
import com.robertx22.mine_and_slash.database.stats.stat_types.BaseTrait;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.potion.Effects;

public class OnDodgeBuffSpeed extends BaseTrait implements IStatEffects {

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public String locDescForLangFile() {
        return "On Dodge Buff Speed";
    }

    @Override
    public String locNameForLangFile() {
        return "Fleeting Wind";
    }

    @Override
    public String GUID() {
        return "fleeting_wind";
    }

    @Override
    public IStatEffect getEffect() {
        return new OnCauseDoEffect(new OnAttackDodgedCause(), 100, IStatEffect.EffectSides.Target, new GivePotionEffect(Effects.SPEED, 5), IStatEffect.EffectSides.Target);
    }
}


