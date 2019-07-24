package com.robertx22.mine_and_slash.items.spells;

import com.robertx22.mine_and_slash.spells.SpellBonusEleBasicDmg;
import com.robertx22.mine_and_slash.spells.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemBonusEleAtkDmg extends BaseSpellItem implements IGenerated<BaseSpellItem> {

    public static final HashMap<Elements, BaseSpellItem> MAP = new HashMap();

    public ItemBonusEleAtkDmg(Elements element) {
        super(element);

    }

    @Override
    public String GUID() {
        return "spells/extra_" + element.dmgName.toLowerCase() + "_dmg";
    }

    @Override
    public BaseSpell Spell() {
        return new SpellBonusEleBasicDmg(element);
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.RED + "Extra " + element.dmgName + " Damage";
    }

    @Override
    public List<BaseSpellItem> generateAllPossibleStatVariations() {

        Elements.getAllSingleElements()
                .forEach(x -> MAP.put(x, new ItemBonusEleAtkDmg(x)));

        return new ArrayList<>(MAP.values());

    }
}
