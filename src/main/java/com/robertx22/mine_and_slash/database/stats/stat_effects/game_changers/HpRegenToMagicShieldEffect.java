package com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.ModifyResourceEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;

public class HpRegenToMagicShieldEffect implements IStatEffect {

    public static final HpRegenToMagicShieldEffect INSTANCE = new HpRegenToMagicShieldEffect();

    @Override
    public int GetPriority() {
        return Priority.First.priority;
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

                if (eff.ctx.use == ResourcesData.Use.RESTORE) {
                    if (eff.ctx.amount > 0) {
                        if (eff.ctx.type == ResourcesData.Type.HEALTH) {
                            eff.ctx.type = ResourcesData.Type.MAGIC_SHIELD;
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