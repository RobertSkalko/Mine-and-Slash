package com.robertx22.mine_and_slash.database.stats.stat_effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.stat_types.offense.PhysicalDamage;
import com.robertx22.mine_and_slash.potion_effects.all.BleedPotion;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.potion.EffectInstance;

public class BleedMasteryEffect implements IStatEffect {

    public static final BleedMasteryEffect INSTANCE = new BleedMasteryEffect();

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
            if (Effect instanceof DamageEffect) {

                DamageEffect dmg = (DamageEffect) Effect;

                if (dmg.getEffectType() == EffectData.EffectTypes.BASIC_ATTACK) {
                    if (dmg.isDmgAllowed()) {

                        int val = (int) (Effect.sourceData.getUnit()
                                .getStat(PhysicalDamage.GUID).Value / 5);

                        Effect.target.addPotionEffect(new EffectInstance(BleedPotion.INSTANCE, 100, val));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

