package com.robertx22.mine_and_slash.database.spells.spell_classes;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SpellTooltips {

    public static ITextComponent singleTargetProjectile() {
        return new StringTextComponent("Throw a projectile, damaging first enemy hit");
    }

    public static ITextComponent buff() {
        return new StringTextComponent("Applies buff to caster");
    }
}
