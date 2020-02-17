package com.robertx22.mine_and_slash.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.robertx22.mine_and_slash.config.compatible_items.OldCompatibleItemSerial;
import com.robertx22.mine_and_slash.config.compatible_items.OldConfigItems;
import com.robertx22.mine_and_slash.data_packs.compatible_items.CompatibleItem;
import com.robertx22.mine_and_slash.onevent.data_gen.providers.SlashDataProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertCompItemsToNewFormat {
    public static void register(CommandDispatcher<CommandSource> commandDispatcher) {
        commandDispatcher.register(
            Commands.literal(COMMAND)
                .requires(e -> e.hasPermissionLevel(2))
                .executes(ctx -> run(ctx.getSource())));
    }

    public static final String COMMAND = "convert_compatible_items_to_new_format";

    private static int run(CommandSource source) {

        try {

            OldCompatibleItemSerial.INSTANCE.loadOnServer();
            OldConfigItems.newFormatList.forEach(x -> output(x));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

    static void output(CompatibleItem item) {
        try {
            Path path = Paths.get(OldCompatibleItemSerial.INSTANCE.folder())
                .resolve("datapacks/" + item.datapackFolder() + "/" + item.guid + ".json");

            String json = SlashDataProvider.GSON.toJson(item.toJson());
            Files.createDirectories(path.getParent());
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

