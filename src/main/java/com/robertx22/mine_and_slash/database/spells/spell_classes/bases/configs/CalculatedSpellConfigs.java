package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
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

    public CalculatedSpellConfigs(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap spellsCap, IAbility ability) {
        int lvl = spellsCap != null ? spellsCap.getLevelOf(ability) : 1;
        int userLvl = data.getLevel();

        PreCalcSpellConfigs pre = ability.getPreCalcConfig();

        this.manaCost = (int) Mana.getInstance()
            .calculateScalingStatGrowth(pre.manaCost.getValueFor(lvl, ability), userLvl);

        this.castTimeTicks = (int) pre.castTimeTicks.getValueFor(lvl, ability);

        this.calc = SpellCalcData.scaleWithAttack(
            pre.spellAttackScalingValue.getValueFor(lvl, ability),
            pre.spellBaseValue.getValueFor(lvl, ability)
        );

        this.timesToCast = (int) pre.timesToCast.getValueFor(lvl, ability);

        this.projectileCount = (int) pre.projectileCount.getValueFor(lvl, ability);

        this.cooldownTicks = (int) pre.cooldownTicks.getValueFor(lvl, ability);

        this.shootSpeed = (int) pre.shootSpeed.getValueFor(lvl, ability);

        this.duration = (int) pre.duration.getValueFor(lvl, ability);

        this.summonedEntities = (int) pre.summonedEntities.getValueFor(lvl, ability);

        this.radius = pre.radius.getValueFor(lvl, ability);

        this.maxSpellLevel = pre.maxSpellLevel;

    }
}
