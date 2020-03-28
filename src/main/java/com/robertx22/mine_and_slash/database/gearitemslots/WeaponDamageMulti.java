package com.robertx22.mine_and_slash.database.gearitemslots;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;
import java.util.List;

public class WeaponDamageMulti {
    public float normalDmgMulti = 1;
    public float poweredDmgMulti = 1;

    public WeaponDamageMulti(float normalDmgMulti, float poweredDmgMulti) {
        this.normalDmgMulti = normalDmgMulti;
        this.poweredDmgMulti = poweredDmgMulti;
    }

    public WeaponDamageMulti(float multi) {
        this.normalDmgMulti = multi;
        this.poweredDmgMulti = multi;
    }

    public List<ITextComponent> tooltipDesc() {
        return Arrays.asList(
            new StringTextComponent(TextFormatting.GREEN + "" + normalDmgMulti + "x DMG (normal)"),
            new StringTextComponent(TextFormatting.GREEN + "" + poweredDmgMulti + "x DMG (powered)")
        );
    }
}
