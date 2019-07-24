package com.robertx22.mine_and_slash.database.stats.stat_effects.defense;

import com.robertx22.mine_and_slash.database.stats.IUsableStat;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IArmorReducable;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.IPenetrable;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.util.math.MathHelper;

public class ArmorEffect implements IStatEffect {

    @Override
    public int GetPriority() {
        return Priority.Third.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof IArmorReducable) {

                int pene = 0;

                if (Effect instanceof IPenetrable) {
                    IPenetrable ipen = (IPenetrable) Effect;
                    pene = ipen.GetArmorPenetration();
                }

                IUsableStat armor = (IUsableStat) stat;

                float EffectiveArmor = armor.GetUsableValue(Effect.targetData.getLevel(), (int) (data.Value - pene));

                EffectiveArmor = MathHelper.clamp(EffectiveArmor, 0, 1);

                Effect.number -= EffectiveArmor * Effect.number;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}
