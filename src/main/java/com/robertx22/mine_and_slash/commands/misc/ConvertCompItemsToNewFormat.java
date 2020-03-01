package com.robertx22.mine_and_slash.commands.misc;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.robertx22.mine_and_slash.commands.CommandRefs;
import com.robertx22.mine_and_slash.config.compatible_items.OldCompatibleItemSerial;
import com.robertx22.mine_and_slash.config.compatible_items.OldConfigItems;
import com.robertx22.mine_and_slash.data_generation.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SerializationUtils;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static net.minecraft.command.Commands.literal;

public class ConvertCompItemsToNewFormat {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            literal(CommandRefs.ID)
                .then(literal("old").requires(e -> e.hasPermissionLevel(2))
                    .then(literal("convert")
                        .then(literal("compatible_items")
                            .executes(ctx -> run(ctx.getSource()))))));
    }

    private static int run(CommandSource source) {

        try {
            OldConfigItems.newFormatList.clear();
            OldCompatibleItemSerial.INSTANCE.loadOnServer();
            OldConfigItems.newFormatList.forEach(x -> output(x));

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            source.asPlayer()
                .sendMessage(new StringTextComponent("Outputed new format to: " + SerializationUtils.CONFIG_PATH + " in output folder"));
        } catch (CommandSyntaxException e) {
            e.printStackTrace();
        }

        return 1;
    }

    static void output(CompatibleItem item) {
        try {
            Path path = Paths.get(SerializationUtils.CONFIG_PATH + "output/")
                .resolve("datapacks/" + item.datapackFolder() + "/" + item.getFileName() + ".json");

            String json = SlashDataProvider.GSON.toJson(item.toJson());
            Files.createDirectories(path.getParent());

            SerializationUtils.makeFileAndDirAndWrite(path.getParent()
                .toString(), path.getFileName()
                .toString(), json, true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

