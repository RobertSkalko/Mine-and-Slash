package com.robertx22.mine_and_slash.db_lists.initializers;

import com.robertx22.mine_and_slash.database.runewords.RuneWord;
import com.robertx22.mine_and_slash.database.runewords.slots_2.*;
import com.robertx22.mine_and_slash.database.runewords.slots_3.RuneWordRadiance;
import com.robertx22.mine_and_slash.database.runewords.slots_3.RuneWordThief;
import com.robertx22.mine_and_slash.database.runewords.slots_4.*;
import com.robertx22.mine_and_slash.database.runewords.slots_5.*;
import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryInit;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistryType;

import java.util.ArrayList;
import java.util.List;

public class RuneWords implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        List<RuneWord> All = new ArrayList<RuneWord>() {
            {
                {

                    add(new RuneWordStone());
                    add(new RuneWordHoming());
                    add(new RuneWordGhost());

                    add(new RuneWordScales());
                    add(new RuneWordRadiance());
                    add(new RuneWordThief());
                    add(new RuneWordMagician());
                    add(new RuneWordMountain());
                    add(new RuneWordZephyr());
                    add(new RuneWordLight());

                    add(new RuneWordMagma());
                    add(new RuneWordBloom());
                    add(new RuneWordZeal());
                    add(new RuneWordPurity());
                    add(new RuneWordInfinity());

                    add(new RuneWordProfoundSea());
                    add(new RuneWordLimitless());

                }
            }
        };

        All.forEach(x -> SlashRegistry.getRegistry(SlashRegistryType.RUNEWORD)
                .register(x));

    }
}
