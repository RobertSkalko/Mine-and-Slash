package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.ModifyResourceEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class HarmonyEffect implements IStatEffect {

    public static final HarmonyEffect INSTANCE = new HarmonyEffect();

    @Override
    public int GetPriority() {
        return Priority.First.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data, Stat stat) {

        try {
            if (Effect instanceof ModifyResourceEffect) {

                ModifyResourceEffect eff = (ModifyResourceEffect) Effect;

                if (eff.ctx.use == ResourcesData.Use.RESTORE) {
                    if (eff.ctx.amount > 0) {
                        if (eff.ctx.type == ResourcesData.Type.HEALTH) {

                            eff.ctx.amount /= 2;

                            float restored = eff.ctx.amount / 2;

                            ResourcesData.Context ctx = new ResourcesData.Context(eff.ctx.sourceData, eff.ctx.source,
                                                                                  ResourcesData.Type.MAGIC_SHIELD,
                                                                                  restored, ResourcesData.Use.RESTORE
                            );
                            eff.ctx.targetData.getResources().modify(ctx);

                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}