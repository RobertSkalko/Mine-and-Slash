package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.saveclasses.spells.IAbility;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerSpellCap;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class EntityCalcSpellConfigs {

    @Store
    public final SpellCalcData calc;

    @Store
    public final Integer maxSpellLevel;
    @Store
    public final Integer summonedEntities;
    @Store
    public final Integer manaCost;
    @Store
    public final Integer castTimeTicks;
    @Store
    public final Integer timesToCast;
    @Store
    public final Integer projectileCount;
    @Store
    public final Integer cooldownTicks;

    @Store
    public final Float shootSpeed;
    @Store
    public final Float duration;
    @Store
    public final Float radius;

    public EntityCalcSpellConfigs(EntityCap.UnitData data, PlayerSpellCap.ISpellsCap spellsCap, IAbility ability) {
        int lvl = spellsCap != null ? spellsCap.getLevelOf(ability) : 1;
        int userLvl = data.getLevel();

        PreCalcSpellConfigs pre = ability.getPreCalcConfig();

        if (ability.getAbilityType() == IAbility.Type.SPELL) {
            pre.modifyBySynergies(ability.getSpell(), spellsCap);
        }

        this.manaCost = (int) Mana.getInstance()
            .calculateScalingStatGrowth(pre.manaCost.get(lvl, ability), userLvl);

        this.castTimeTicks = (int) pre.castTimeTicks.get(lvl, ability);

        this.calc = SpellCalcData.scaleWithAttack(
            pre.spellAttackScalingValue.get(lvl, ability),
            pre.spellBaseValue.get(lvl, ability)
        );

        this.timesToCast = (int) pre.timesToCast.get(lvl, ability);

        this.projectileCount = (int) pre.projectileCount.get(lvl, ability);

        this.cooldownTicks = (int) pre.cooldownTicks.get(lvl, ability);

        this.shootSpeed = pre.shootSpeed.get(lvl, ability);

        this.duration = pre.duration.get(lvl, ability);

        this.summonedEntities = (int) pre.summonedEntities.get(lvl, ability);

        this.radius = pre.radius.get(lvl, ability);

        this.maxSpellLevel = pre.maxSpellLevel;

    }
}
