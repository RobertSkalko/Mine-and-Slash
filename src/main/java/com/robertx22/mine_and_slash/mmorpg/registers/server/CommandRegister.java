package com.robertx22.mine_and_slash.mmorpg.registers.server;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.commands.dev.LogDungeonRoom;
import com.robertx22.mine_and_slash.commands.dev.TpOut;
import com.robertx22.mine_and_slash.commands.dev.TpRandomDungeon;
import com.robertx22.mine_and_slash.commands.dev.unique_dungeon.ExportUniqueDungeon;
import com.robertx22.mine_and_slash.commands.dev.unique_dungeon.SetUniqueDunPos;
import com.robertx22.mine_and_slash.commands.entity.*;
import com.robertx22.mine_and_slash.commands.giveitems.*;
import com.robertx22.mine_and_slash.commands.misc.*;
import com.robertx22.mine_and_slash.commands.open_gui.OpenHub;
import com.robertx22.mine_and_slash.commands.party.PartyCommand;
import com.robertx22.mine_and_slash.commands.reset.ResetSpellCooldowns;
import com.robertx22.mine_and_slash.commands.reset.ResetSpells;
import com.robertx22.mine_and_slash.commands.reset.ResetTalents;
import com.robertx22.mine_and_slash.commands.stats.*;
import net.minecraft.command.CommandSource;
import net.minecraft.server.MinecraftServer;

public class CommandRegister {

    public static void Register(MinecraftServer server) {
        System.out.println("Registering Mine and Slash Commands.");

        CommandDispatcher<CommandSource> dispatcher = server.getCommandManager()
            .getDispatcher();

        SetLevel.register(dispatcher);
        RestoreLevel.register(dispatcher);
        GiveExp.register(dispatcher);
        GiveAwakenRuneword.register(dispatcher);
        GiveAbilityLevels.register(dispatcher);
        GiveExactUnique.register(dispatcher);
        GiveGear.register(dispatcher);
        GiveMap.register(dispatcher);
        GiveRune.register(dispatcher);
        GiveRunedGear.register(dispatcher);
        GiveUniqueGear.register(dispatcher);
        SetEntityLevel.register(dispatcher);
        SetEntityRarity.register(dispatcher);
        SummonBoss.register(dispatcher);
        TpOut.register(dispatcher);
        LogDungeonRoom.register(dispatcher);
        TpRandomDungeon.register(dispatcher);
        SetUniqueDunPos.register(dispatcher);
        ExportUniqueDungeon.register(dispatcher);

        GiveStatMod.register(dispatcher);
        RemoveStatMod.register(dispatcher);
        ClearStatMods.register(dispatcher);
        ResetSpells.register(dispatcher);
        ResetSpellCooldowns.register(dispatcher);

        GiveStat.register(dispatcher);
        RemoveStat.register(dispatcher);
        ClearStats.register(dispatcher);
        GiveAllStats.register(dispatcher);

        ReloadConfigs.register(dispatcher);
        ModifyItem.register(dispatcher);
        GiveUniqueRune.register(dispatcher);
        ResetTalents.register(dispatcher);
        OpenHub.register(dispatcher);

        GenDefaultCompItemsOfMod.register(dispatcher);

        GiveCrate.register(dispatcher);

        PartyCommand.register(dispatcher);

        ConvertCompItemsToNewFormat.register(dispatcher);

    }
}