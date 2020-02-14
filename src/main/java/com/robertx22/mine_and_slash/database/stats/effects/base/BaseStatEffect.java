package com.robertx22.mine_and_slash.database.stats.effects.base;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.util.text.TextFormatting;

public abstract class BaseStatEffect<T extends EffectData> implements IStatEffect {

    Class<T> theclass;

    public BaseStatEffect(Class<T> theclass) {
        this.theclass = theclass;
    }

    public abstract T modifyEffect(T effect, StatData data, Stat stat);

    public abstract boolean canActivate(T effect, StatData data, Stat stat);

    public void log(String str) {
        if (MMORPG.RUN_DEV_TOOLS && MMORPG.statEffectDebuggingEnabled()) {
            System.out.println(TextFormatting.LIGHT_PURPLE + str);
        }
    }

    @Override
    public final EffectData TryModifyEffect(EffectData Effect, Unit Source, StatData statData, Stat stat) {

        try {
            if (Effect.getClass().isAssignableFrom(theclass.getClass())) {
                if (canActivate((T) Effect, statData, stat)) {
                    return modifyEffect((T) Effect, statData, stat);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }
}
