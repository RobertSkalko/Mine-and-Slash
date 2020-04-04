package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.spell_classes.ManaCost;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.level_based_numbers.LevelBased;
import com.robertx22.mine_and_slash.database.stats.types.resources.Mana;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SpellConfig {

    public SpellSchools school;
    private ManaCost manaCost;
    private SpellCalcData calc;
    public int maxSpellLevel;

    public SpellConfig(SpellCastType castType, SpellSchools school, ManaCost manaCost, SpellCalcData calc, int maxSpellLevel) {
        this.school = school;
        this.manaCost = manaCost;
        this.calc = calc;
        this.maxSpellLevel = maxSpellLevel;
        this.castType = castType;
    }

    public SpellCastType castType;

    public Function<World, Entity> newEntitySummoner;

    public List<SpellPredicate> castRequirements = new ArrayList<>();
    public SoundEvent sound = SoundEvents.ENTITY_ARROW_SHOOT;
    public BaseSpell.AllowedAsRightClickOn allowedAsRightClickOn = BaseSpell.AllowedAsRightClickOn.NONE;

    public LevelBased projectileCount = new LevelBased(1, 1);
    public LevelBased castTimeTicks = new LevelBased(10, 10);
    public LevelBased shootSpeed = new LevelBased(1, 1.5F);
    public LevelBased summonedEntities = new LevelBased(1, 1);
    public LevelBased cooldownTicks = new LevelBased(30, 25);
    public LevelBased timesCasted = new LevelBased(1, 1);//most spells its casted once at end of cast time, others are casted during the whole cast duration

    public BasePotionEffect potionEffect = null;

    public boolean goesOnCooldownIfCanceled = false;

    public final int getCalculatedManaCost(BaseSpell spell, EntityCap.UnitData data) {
        return (int) Mana.getInstance()
            .calculateScalingStatGrowth(manaCost.cost, data.getSpellLevel(spell));
    }

}
