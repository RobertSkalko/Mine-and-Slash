package com.robertx22.mine_and_slash.commands.bases;

import com.robertx22.mine_and_slash.database.bosses.base.Boss;
import com.robertx22.mine_and_slash.registry.SlashRegistry;

import java.util.ArrayList;
import java.util.List;

public class BossSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {

        List<String> list = new ArrayList();
        for (Boss boss : SlashRegistry.Bosses().getAll().values()) {
            list.add(boss.GUID());
        }

        return list;
    }

}

