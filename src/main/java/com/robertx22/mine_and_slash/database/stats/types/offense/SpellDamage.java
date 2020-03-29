package com.robertx22.mine_and_slash.database.stats.types.offense;

import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.effects.offense.AllSpellDamageEffect;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffect;
import com.robertx22.mine_and_slash.uncommon.interfaces.IStatEffects;
import net.minecraft.util.text.TextFormatting;

public class SpellDamage extends Stat implements IStatEffects {

    private SpellDamage() {

    }

    public static String GUID = "spell_damage";

    public static SpellDamage getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String getIconPath() {
        return "spell_dmg";
    }

    @Override
    public TextFormatting getIconFormat() {
        return TextFormatting.GOLD;
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
    public Elements getElement() {
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

    private static class SingletonHolder {
        private static final SpellDamage INSTANCE = new SpellDamage();
    }
}
