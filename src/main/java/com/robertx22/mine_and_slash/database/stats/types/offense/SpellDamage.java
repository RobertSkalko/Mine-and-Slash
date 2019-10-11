package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.util.text.TextFormatting;

public class SpellDamage extends Stat implements IStatEffects {

    public static Stat INSTANCE = new SpellDamage();

    private SpellDamage() {

    }

    public static String GUID = "spell_damage";

    @Override
    public String getIconPath() {
        return "spell_dmg";
    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.AQUA;
    }

    @Override
    public String getIcon() {
        return "\u262F";
    }

    @Override
    public String locDescForLangFile() {
        return "Increases DMG of all spells no matter the element";
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.SpellDamage;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public boolean ScalesToLevel() {
        return true;
    }

    @Override
    public Elements Element() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return false;
    }

    @Override
    public IStatEffect getEffect() {
        return AllSpellDamageEffect.INSTANCE;
    }

    @Override
    public String locNameForLangFile() {
        return "Spell Damage";
    }
}
