package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.types.game_changers.RefreshingBreeze;
import com.robertx22.mine_and_slash.database.stats.types.resources.EnergyRegen;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;

public class RefreshingBreezeEffect extends BaseDamageEffect {

    public static final RefreshingBreezeEffect INSTANCE = new RefreshingBreezeEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float energy = effect.targetData.getUnit()
            .peekAtStat(EnergyRegen.GUID)
            .getAverageValue() * RefreshingBreeze.PERCENT / 100;

        ResourcesData.Use use;

        if (effect.isDodged) {
            use = ResourcesData.Use.RESTORE;
        } else {
            use = ResourcesData.Use.SPEND;
            energy /= 2;
        }

        ResourcesData.Context ene = new ResourcesData.Context(
            effect.targetData, effect.target, ResourcesData.Type.ENERGY, energy, use);
        effect.targetData.getResources()
            .modify(ene);

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        return true;
    }

}
