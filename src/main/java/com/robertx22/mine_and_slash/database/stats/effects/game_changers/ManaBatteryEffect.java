package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.defense.MagicShieldEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import net.minecraft.util.math.MathHelper;

public class ManaBatteryEffect implements IStatEffect {

    public static final ManaBatteryEffect INSTANCE = new ManaBatteryEffect();

    @Override
    public int GetPriority() {
        return Priority.beforeThis(MagicShieldEffect.INSTANCE.GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    @Override
    public EffectData TryModifyEffect(EffectData Effect, Unit source, StatData data,
                                      Stat stat) {

        try {
            if (Effect instanceof DamageEffect) {

                float currentMana = Effect.targetData.getResources().getMana();

                if (currentMana / Effect.targetData.getUnit().manaData().Value > 0.25F) {

                    float maxMana = Effect.targetData.getUnit().manaData().Value;

                    float dmgReduced = MathHelper.clamp(Effect.number / 2, 0, currentMana - (maxMana * 0.25F));

                    if (dmgReduced > 0) {

                        Effect.number -= dmgReduced;

                        ResourcesData.Context ctx = new ResourcesData.Context(Effect.targetData, Effect.target, ResourcesData.Type.MANA, dmgReduced, ResourcesData.Use.SPEND);

                        Effect.targetData.getResources().modify(ctx);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Effect;
    }

}

