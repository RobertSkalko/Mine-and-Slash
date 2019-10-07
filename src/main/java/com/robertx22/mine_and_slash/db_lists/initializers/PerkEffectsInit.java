package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.Perk;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.PerkEffects;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.StartPerkEffects;
import com.robertx22.mine_and_slash.new_content_test.talent_tree.data.TraitPerkEffects;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PerkEffectsInit implements ISlashRegistryInit {

    public static List<Perk> all = new ArrayList<>();

    @Override
    public void registerAll() {

        PerkEffects.create();

        StartPerkEffects.create();
        TraitPerkEffects.create();

        PerkEffects.createCombined();

        genPerkListTutotorial();

    }

    private void genPerkListTutotorial() {

        List<String> list = SlashRegistry.PerkEffects()
                .getList()
                .stream()
                .map(x -> x.GUID())
                .collect(Collectors.toList());

        Collections.sort(list);

        String text = "// THIS FILE IS A TUTORIAL FILE, IT LETS YOU KNOW THE GUIDS/IDS OF ALL TALENT PERK EFFECTS\n" + String
                .join("\n", list);

        SerializationUtils.makeFileAndDirAndWrite("tutorials", "TalentPerkEffects.txt", text);

    }
}
