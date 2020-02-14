package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseStatEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.ModifyResourceEffect;

public class HarmonyEffect extends BaseStatEffect<ModifyResourceEffect> {

    public static final HarmonyEffect INSTANCE = new HarmonyEffect();

    private HarmonyEffect() {
        super(ModifyResourceEffect.class);
    }

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public ModifyResourceEffect activate(ModifyResourceEffect effect, StatData data, Stat stat) {

        effect.ctx.amount /= 2;

        float restored = effect.ctx.amount / 2;

        ResourcesData.Context ctx = new ResourcesData.Context(effect.ctx.sourceData, effect.ctx.source,
                                                              ResourcesData.Type.MAGIC_SHIELD, restored,
                                                              ResourcesData.Use.RESTORE
        );
        effect.ctx.targetData.getResources().modify(ctx);

        return effect;
    }

    @Override
    public boolean canActivate(ModifyResourceEffect effect, StatData data, Stat stat) {
        if (effect.ctx.use == ResourcesData.Use.RESTORE) {
            if (effect.ctx.amount > 0) {
                if (effect.ctx.type == ResourcesData.Type.HEALTH) {
                    return true;
                }
            }
        }
        return false;
    }

}