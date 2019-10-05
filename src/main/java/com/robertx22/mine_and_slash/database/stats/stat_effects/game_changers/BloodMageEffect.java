package com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers;

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
                    if (eff.ctx.type == ResourcesData.Type.MANA) {
                        eff.ctx.type = ResourcesData.Type.HEALTH;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

