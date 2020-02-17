package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.spells.spell_tree.data.SpellPerkEffects;
import com.robertx22.mine_and_slash.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpellPerkEffectsInit implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        SpellPerkEffects.register();

        genPerkListTutotorial();

    }

    private void genPerkListTutotorial() {

        List<String> list = SlashRegistry.SpellPerkEffects()
                .getList()
                .stream()
                .map(x -> x.GUID())
                .collect(Collectors.toList());

        Collections.sort(list);

        String text =
                "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW THE GUIDS/IDS OF ALL SPELL PERK EFFECTS\n" + String
                .join("\n", list);

        SerializationUtils.makeFileAndDirAndWrite("tutorials", "SpellPerkEffects.txt", text);

    }
}

