package com.robertx22.mine_and_slash.database.spells.spell_classes.bases.configs;

import com.robertx22.mine_and_slash.database.spells.blocks.base.BaseSpellBlock;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.SpellPredicate;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.mine_and_slash.potion_effects.bases.BasePotionEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.enumclasses.SpellSchools;
import net.minecraft.entity.Entity;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class ImmutableSpellConfigs {

    public abstract SpellSchools school();

    public abstract SpellCastType castType();

    public abstract SoundEvent sound();

    public abstract int maxSpellLevel();

    public abstract Elements element();

    public BaseSpellBlock spellBlockToSpawn() {
        return null;
    }

    public BasePotionEffect potionEffect() {
        return null;
    }

    public boolean goesOnCooldownIfCanceled() {
        return false;
    }

    public Function<World, Entity> newEntitySummoner() {
        return null;
    }

    public List<SpellPredicate> castRequirements() {
        return new ArrayList<>();
    }

    public BaseSpell.AllowedAsRightClickOn allowedAsRightClickOn() {
        return BaseSpell.AllowedAsRightClickOn.NONE;
    }

}
