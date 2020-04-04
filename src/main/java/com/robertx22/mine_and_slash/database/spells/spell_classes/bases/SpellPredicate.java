package com.robertx22.mine_and_slash.database.spells.spell_classes.bases;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;

import java.util.function.Predicate;

public class SpellPredicate {
    public Predicate<LivingEntity> predicate;
    public ITextComponent text;

    public SpellPredicate(Predicate<LivingEntity> predicate, ITextComponent text) {
        this.predicate = predicate;
        this.text = text;

    }

}


