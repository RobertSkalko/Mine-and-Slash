package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class CalculatedSpellConfigs {

    @Store
    public final SpellCalcData calc;

    @Store
    public final int manaCost;
    @Store
    public final int castTimeTicks;
    @Store
    public final int timesToCast;
    @Store
    public final int projectileCount;
    @Store
    public final int cooldownTicks;
    @Store
    public final float shootSpeed;
    @Store
    public final float duration;
    @Store
    public final float radius;
    @Store
    public final int summonedEntities;
    @Store
    public final int maxSpellLevel;

    public CalculatedSpellConfigs(SpellCastContext ctx) {
        PreCalcSpellConfigs pre = ctx.spell.getPreCalcConfig();

        this.manaCost = (int) Mana.getInstance()
            .calculateScalingStatGrowth(pre.manaCost.getValueFor(ctx), ctx.spellLevel);

        this.castTimeTicks = (int) pre.castTimeTicks.getValueFor(ctx);

        this.calc = SpellCalcData.scaleWithAttack(
            pre.spellAttackScalingValue.getValueFor(ctx),
            pre.spellBaseValue.getValueFor(ctx)
        );

        this.timesToCast = (int) pre.timesToCast.getValueFor(ctx);

        this.projectileCount = (int) pre.projectileCount.getValueFor(ctx);

        this.cooldownTicks = (int) pre.cooldownTicks.getValueFor(ctx);

        this.shootSpeed = (int) pre.shootSpeed.getValueFor(ctx);

        this.duration = (int) pre.duration.getValueFor(ctx);

        this.summonedEntities = (int) pre.summonedEntities.getValueFor(ctx);

        this.radius = pre.radius.getValueFor(ctx);

        this.maxSpellLevel = pre.maxSpellLevel;

    }

}
