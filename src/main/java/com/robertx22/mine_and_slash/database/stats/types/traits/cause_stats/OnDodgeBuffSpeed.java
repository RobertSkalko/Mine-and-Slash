package com.robertx22.mine_and_slash.database.stats.types.traits.cause_stats;

import com.robertx22.mine_and_slash.database.stats.StatMod;
import com.robertx22.mine_and_slash.database.stats.Trait;
import com.robertx22.mine_and_slash.database.stats.effects.cause_effects.GivePotionEffect;
import com.robertx22.mine_and_slash.database.stats.effects.cause_effects.OnCauseDoEffect;
import com.robertx22.mine_and_slash.database.stats.effects.causes.OnAttackDodgedCause;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.potion.Effects;

import java.util.Arrays;
import java.util.List;

public class OnDodgeBuffSpeed extends Trait implements IStatEffects {

    @Override
    public Elements getElement() {
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
        return new OnCauseDoEffect(
            new OnAttackDodgedCause(), 100, IStatEffect.EffectSides.Target, new GivePotionEffect(Effects.SPEED, 5),
            IStatEffect.EffectSides.Target
        );
    }

    @Override
    public List<StatMod> getStats() {
        return Arrays.asList();
    }
}


