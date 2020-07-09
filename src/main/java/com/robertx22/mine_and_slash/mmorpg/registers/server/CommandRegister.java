package com.robertx22.mine_and_slash.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.entity.SetEntityRarity;
import com.robertx22.mine_and_slash.commands.giveitems.GiveExactUnique;
import com.robertx22.mine_and_slash.commands.giveitems.GiveGear;
import com.robertx22.mine_and_slash.commands.giveitems.GiveUniqueGear;
import com.robertx22.mine_and_slash.commands.misc.GenDefaultCompItemsOfMod;
import com.robertx22.mine_and_slash.commands.misc.ReloadConfigs;
import com.robertx22.mine_and_slash.commands.open_gui.OpenHub;
import com.robertx22.mine_and_slash.commands.reset.ResetSpellCooldowns;
import com.robertx22.mine_and_slash.commands.stats.ClearStats;
import com.robertx22.mine_and_slash.commands.stats.GiveAllStats;
import com.robertx22.mine_and_slash.commands.stats.GiveStat;
import com.robertx22.mine_and_slash.commands.stats.RemoveStat;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;

public class CommandRegister {

    public static void Register(MinecraftServer server) {
        System.out.println("Registering Mine and Slash Commands.");

        CommandDispatcher<CommandSource> dispatcher = server.getCommandManager()
            .getDispatcher();

        GiveExactUnique.register(dispatcher);
        GiveGear.register(dispatcher);
        GiveUniqueGear.register(dispatcher);
        SetEntityRarity.register(dispatcher);

        ResetSpellCooldowns.register(dispatcher);

        GiveStat.register(dispatcher);
        RemoveStat.register(dispatcher);
        ClearStats.register(dispatcher);
        GiveAllStats.register(dispatcher);

        ReloadConfigs.register(dispatcher);
        OpenHub.register(dispatcher);

        GenDefaultCompItemsOfMod.register(dispatcher);

    }
}