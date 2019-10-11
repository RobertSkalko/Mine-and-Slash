package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.ModifyResourceEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class BloodMageEffect implements IStatEffect {

    public static final BloodMageEffect INSTANCE = new BloodMageEffect();

    @Override
    public int GetPriority() {
        return Priority.Last.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof ModifyResourceEffect) {

                ModifyResourceEffect eff = (ModifyResourceEffect) Effect;

                if (eff.ctx.use == ResourcesData.Use.SPEND) {
                    if (eff.ctx.amount > 0) {
                        if (eff.ctx.type == ResourcesData.Type.MANA) {
                            eff.ctx.type = ResourcesData.Type.BLOOD;
                        }
                    }
                }

                if (eff.ctx.use == ResourcesData.Use.RESTORE) {
                    if (eff.ctx.amount > 0) {
                        if (eff.ctx.type == ResourcesData.Type.HEALTH) {
                            if (eff.ctx.spell == null) {
                                float bloodrestored = eff.ctx.amount / 2;
                                ResourcesData.Context blood = new ResourcesData.Context(eff.ctx.targetData, eff.ctx.target, ResourcesData.Type.BLOOD, bloodrestored, ResourcesData.Use.RESTORE);
                                eff.ctx.targetData.getResources().modify(blood);
                            }
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

