package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import com.robertx22.mine_and_slash.database.spells.blocks.base.BaseSpellBlock;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ImmutableSpellConfigs {

    public BaseSpellBlock spellBlockToSpawn = null;
    public BasePotionEffect potionEffect = null;
    public boolean goesOnCooldownIfCanceled = false;
    public SpellSchools school;
    public SpellCastType castType;
    public Function<World, Entity> newEntitySummoner;
    public List<SpellPredicate> castRequirements = new ArrayList<>();
    public SoundEvent sound = SoundEvents.ENTITY_ARROW_SHOOT;
    public BaseSpell.AllowedAsRightClickOn allowedAsRightClickOn = BaseSpell.AllowedAsRightClickOn.NONE;
    public int maxSpellLevel;

    public ImmutableSpellConfigs(SpellCastType castType, SpellSchools school, int maxSpellLevel) {
        this.school = school;
        this.castType = castType;
        this.maxSpellLevel = maxSpellLevel;
    }

}
