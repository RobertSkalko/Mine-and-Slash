package com.robertx22.mine_and_slash.db_lists;

import com.robertx22.mine_and_slash.database.rarities.RarityTypeEnum;
import com.robertx22.mine_and_slash.database.rarities.containers.*;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.packets.RarityPacket;
import net.minecraft.entity.player.ServerPlayerEntity;

public class Rarities {

    public static final RunedGearRarities RunedGears = new RunedGearRarities();
    public static final RuneRarities Runes = new RuneRarities();
    public static final GearRarities Gears = new GearRarities();
    public static final MapRarities Maps = new MapRarities();
    public static final MobRarities Mobs = new MobRarities();

    public static void sendAllPacketsToClientOnLogin(ServerPlayerEntity player) {

        MMORPG.sendToClient(new RarityPacket(RarityTypeEnum.GEAR), player);

    }
}
