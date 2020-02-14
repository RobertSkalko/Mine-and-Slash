package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseStatEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.ModifyResourceEffect;

public class BloodMageRestoreManaEffect extends BaseStatEffect<ModifyResourceEffect> {

    private BloodMageRestoreManaEffect() {
        super(ModifyResourceEffect.class);
    }

    public static BloodMageRestoreManaEffect getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public ModifyResourceEffect activate(ModifyResourceEffect effect, StatData data, Stat stat) {

        float bloodrestored = effect.ctx.amount / 2;

        ResourcesData.Context blood = new ResourcesData.Context(effect.ctx.targetData, effect.ctx.target,
                                                                ResourcesData.Type.BLOOD, bloodrestored,
                                                                ResourcesData.Use.RESTORE
        );
        effect.ctx.targetData.getResources().modify(blood);

        return effect;
    }

    @Override
    public boolean canActivate(ModifyResourceEffect effect, StatData data, Stat stat) {
        if (effect.ctx.use == ResourcesData.Use.RESTORE) {
            if (effect.ctx.amount > 0) {
                if (effect.ctx.type == ResourcesData.Type.HEALTH) {
                    if (effect.ctx.spell == null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class SingletonHolder {
        private static final BloodMageRestoreManaEffect INSTANCE = new BloodMageRestoreManaEffect();
    }
}

