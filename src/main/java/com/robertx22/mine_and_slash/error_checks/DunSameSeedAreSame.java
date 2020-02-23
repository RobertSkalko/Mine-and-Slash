package com.robertx22.mine_and_slash.error_checks;

import com.robertx22.mine_and_slash.error_checks.base.IErrorCheck;
import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.RoomSides;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.new_content.building.DungeonBuilder;
import com.robertx22.mine_and_slash.new_content.enums.RoomSide;
import net.minecraft.util.math.ChunkPos;
import org.apache.commons.lang3.Validate;

public class DunSameSeedAreSame implements IErrorCheck {

    @Override
    public void check() {

        long seed = 50000;
        ChunkPos pos = new ChunkPos(0, 0);
        DungeonBuilder one = new DungeonBuilder(seed, pos);
        DungeonBuilder two = new DungeonBuilder(seed, pos);

        RoomRotation r1 = one.randomRoom(new UnbuiltRoom(new RoomSides(RoomSide.DOOR, RoomSide.NONE, RoomSide.NONE, RoomSide.NONE)));
        RoomRotation r2 = two.randomRoom(new UnbuiltRoom(new RoomSides(RoomSide.DOOR, RoomSide.NONE, RoomSide.NONE, RoomSide.NONE)));

        //Validate.isTrue(r1.equals(r2), "Random weighted of same rand seed MUST BE THE SAME.");

        one.build();
        two.build();

        try {
            Validate.isTrue(one.dungeon.equals(two.dungeon), "Two dungeons with same seed and position MUST be the same!!!");
        } catch (Exception e) {
            e.printStackTrace();
            one.dungeon.printDungeonAsSymbolsForDebug();
            two.dungeon.printDungeonAsSymbolsForDebug();
        }

    }
}
