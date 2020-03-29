package com.robertx22.mine_and_slash.database.stats.effects.game_changers;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.defense.MagicShieldEffect;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import net.minecraft.util.math.MathHelper;

public class ManaBatteryEffect extends BaseDamageEffect {

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
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {
        float currentMana = effect.targetData.getResources()
            .getMana();

        float maxMana = effect.targetData.getUnit()
            .manaData()
            .getAverageValue();

        float dmgReduced = MathHelper.clamp(effect.number / 4, 0, currentMana - (maxMana * 0.5F));

        if (dmgReduced > 0) {

            effect.number -= dmgReduced;

            ResourcesData.Context ctx = new ResourcesData.Context(effect.targetData, effect.target,
                ResourcesData.Type.MANA, dmgReduced * 2,
                ResourcesData.Use.SPEND
            );

            effect.targetData.getResources()
                .modify(ctx);

        }

        return effect;
    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        float currentMana = effect.targetData.getResources()
            .getMana();

        return currentMana / effect.targetData.getUnit()
            .manaData()
            .getAverageValue() > 0.5F;
    }

}

