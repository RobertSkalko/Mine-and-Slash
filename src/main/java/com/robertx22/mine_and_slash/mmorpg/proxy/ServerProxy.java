package com.robertx22.mine_and_slash.mmorpg.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.network.NetworkEvent.Context;

import java.util.function.Supplier;

public class ServerProxy implements IProxy {

    @Override
    public PlayerEntity getPlayerEntityFromContext(Supplier<Context> ctx) {
        return ctx.get().getSender();
    }

    @Override
    public String translate(ITextComponent comp) {
        return "error";
    }

}