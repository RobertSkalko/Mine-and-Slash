package com.robertx22.mine_and_slash.database.stats.effects.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.base.BaseDamageEffect;
import com.robertx22.mine_and_slash.database.stats.effects.defense.BlockEffect;
import com.robertx22.mine_and_slash.database.stats.types.resources.Energy;
import com.robertx22.mine_and_slash.saveclasses.ResourcesData;
import com.robertx22.mine_and_slash.saveclasses.StatData;
import com.robertx22.mine_and_slash.uncommon.effectdatas.DamageEffect;
import com.robertx22.mine_and_slash.uncommon.effectdatas.EffectData.EffectTypes;
import com.robertx22.mine_and_slash.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;

public class BlockReflectEffect extends BaseDamageEffect {

    public Elements element = Elements.Physical;

    public BlockReflectEffect(Elements element) {
        this.element = element;
    }

    @Override
    public int GetPriority() {
        return Priority.afterThis(new BlockEffect().GetPriority());
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Target;
    }

    public float getEnergyCost(int lvl) {
        return Energy.getInstance()
            .getScaling()
            .scale(1, lvl);
    }

    @Override
    public DamageEffect activate(DamageEffect effect, StatData data, Stat stat) {

        float cost = getEnergyCost(effect.targetData.getLevel());

        ResourcesData.Context ctx = new ResourcesData.Context(effect.targetData, effect.target,
            ResourcesData.Type.ENERGY, cost,
            ResourcesData.Use.SPEND
        );

        effect.targetData.getResources()
            .modify(ctx);

        float dmg = data.val;

        DamageEffect dmgeffect = new DamageEffect(null, effect.target, effect.source, (int) dmg, effect.targetData,
            effect.sourceData, EffectTypes.REFLECT, WeaponTypes.None
        );

        dmgeffect.element = stat.getElement();

        dmgeffect.Activate();

        return effect;

    }

    @Override
    public boolean canActivate(DamageEffect effect, StatData data, Stat stat) {
        if (effect.getEffectType()
            .equals(EffectTypes.BASIC_ATTACK) && effect.isBlocked()) {

            float cost = getEnergyCost(effect.targetData.getLevel());

            ResourcesData.Context ctx = new ResourcesData.Context(effect.targetData, effect.target,
                ResourcesData.Type.ENERGY, cost,
                ResourcesData.Use.SPEND
            );
            return effect.targetData.getResources()
                .hasEnough(ctx);
        }
        return false;
    }

}
