package com.robertx22.mine_and_slash.saveclasses.spells;

import com.robertx22.mine_and_slash.database.spells.spell_classes.SpellStormCloud;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.uncommon.enumclasses.Elements;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

import java.util.HashMap;

@Storable
public class PlayerSpellsData {

    @Store
    public HashMap<Integer, String> hotbar = new HashMap<>();

    @Store
    public HashMap<Integer, String> secondHotbar = new HashMap<>();

    public BaseSpell getSpellByKeybind(int key) {

        return new SpellStormCloud(Elements.Thunder); // todo

    }
}
