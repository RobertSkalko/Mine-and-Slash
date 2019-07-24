package com.robertx22.mine_and_slash.commands.bases;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public abstract class CommandSuggestions implements SuggestionProvider<CommandSource> {

    public abstract List<String> suggestions();

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandSource> context,
                                                         SuggestionsBuilder builder) throws CommandSyntaxException {

        List<String> list = new ArrayList();

        ISuggestionProvider.suggest(this.suggestions(), builder);

        return builder.buildFuture();
    }

}

