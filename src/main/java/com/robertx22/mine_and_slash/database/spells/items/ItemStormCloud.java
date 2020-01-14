package com.robertx22.mine_and_slash.database.spells.items;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellStormCloud;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import com.robertx22.mine_and_slash.uncommon.interfaces.IGenerated;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemStormCloud extends BaseSpellItem implements IGenerated<BaseSpellItem> {

    public static final HashMap<Elements, ItemStormCloud> MAP = new HashMap();

    public ItemStormCloud(Elements element) {
        super(element);

    }

    @Override
    public String GUID() {
        return "spells/storm_cloud_" + element.dmgName.toLowerCase();
    }

    @Override
    public BaseSpell Spell() {
        return new SpellStormCloud(element);
    }

    @Override
    public String locNameForLangFile() {
        return TextFormatting.RED + element.dmgName + " Storm Cloud";
    }

    @Override
    public List<BaseSpellItem> generateAllPossibleStatVariations() {
        Elements.getAllSingleElements().forEach(x -> MAP.put(x, new ItemStormCloud(x)));
        return new ArrayList<>(MAP.values());

    }
}
