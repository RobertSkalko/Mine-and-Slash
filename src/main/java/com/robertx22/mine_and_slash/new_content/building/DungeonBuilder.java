package com.robertx22.mine_and_slash.new_content.building;

import com.google.common.base.Preconditions;
import com.robertx22.mine_and_slash.new_content.BuiltRoom;
import com.robertx22.mine_and_slash.new_content.RoomRotation;
import com.robertx22.mine_and_slash.new_content.UnbuiltRoom;
import com.robertx22.mine_and_slash.new_content.enums.RoomType;
import com.robertx22.mine_and_slash.new_content.registry.DungeonRoom;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;
import net.minecraft.util.math.ChunkPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonBuilder {

    public DungeonBuilder(long worldSeed, ChunkPos cpos) {

        // worldSeed = 500000; // TODO FOR TEST
        //cpos = new ChunkPos(0, 0); // TODO TEST

        int chunkX = getStartChunk(cpos).x;
        int chunkZ = getStartChunk(cpos).z;

        long newSeed = (worldSeed + (long) (chunkX * chunkX * 4987142) + (long) (chunkX * 5947611) + (long) (chunkZ * chunkZ) * 4392871L + (long) (chunkZ * 389711) ^ worldSeed);
        rand = new Random(newSeed);

    }

    public static ChunkPos getStartChunk(ChunkPos pos) {
        int chunkX = pos.x;
        int chunkZ = pos.z;
        int distToEntranceX = 8 - (chunkX % 50);
        int distToEntranceZ = 11 - (chunkZ % 50);
        chunkX += distToEntranceX;
        chunkZ += distToEntranceZ;

        return new ChunkPos(chunkX, chunkZ);
    }

    boolean debug = false;
    public Dungeon dungeon;
    Random rand;
    public int size = 25;
    public boolean isTesting = false;

    public void build() {
        dungeon = new Dungeon(size);

        setupEntrance();

        dungeon.setupBarriers();

        while (!dungeon.isFinished()) {

            dungeon.getUnbuiltCopy()
                .forEach(x -> {

                    try {
                        UnbuiltRoom unbuilt = dungeon.getUnbuiltFor(x.left, x.right);

                        Preconditions.checkNotNull(unbuilt);

                        RoomRotation rot = randomDungeonRoom(unbuilt);

                        Preconditions.checkNotNull(rot);

                        DungeonRoom dRoom = rot.type.getRandomRoom(rand, debug);

                        Preconditions.checkNotNull(dRoom);

                        BuiltRoom room = new BuiltRoom(rot, dRoom.loc);

                        Preconditions.checkNotNull(room);

                        dungeon.addRoom(x.left, x.right, room);

                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                });

        }

    }

    public RoomRotation randomDungeonRoom(UnbuiltRoom unbuilt) {

        if (dungeon.shouldStartFinishing()) {
            List<RoomRotation> pos = RoomType.END.getPossibleFor(unbuilt);
            if (pos.isEmpty()) { //its not possible to set end for all of them
                return randomRoom(unbuilt);
            } else {
                return random(pos);
            }
        } else {
            return randomRoom(unbuilt);
        }
    }

    public RoomRotation randomRoom(UnbuiltRoom unbuilt) {
        List<RoomType> types = new ArrayList<>();
        types.add(RoomType.CURVED_HALLWAY);
        types.add(RoomType.STRAIGHT_HALLWAY);
        types.add(RoomType.FOUR_WAY);
        types.add(RoomType.TRIPLE_HALLWAY);

        List<RoomRotation> possible = new ArrayList<>();

        types.forEach(x -> {
            possible.addAll(x.getPossibleFor(unbuilt));
        });

        if (possible.isEmpty()) {
            throw new RuntimeException("No possible rooms at all for unbuilt room, this is horrible.");
        }

        return random(possible);
    }

    public RoomRotation random(List<RoomRotation> list) {
        return RandomUtils.weightedRandom(list, rand.nextDouble());
    }

    private void setupEntrance() {
        DungeonRoom entranceRoom = RoomType.ENTRANCE.getRandomRoom(rand, debug);

        List<RoomRotation> possible = new ArrayList<>();
        possible.addAll(RoomType.ENTRANCE.getRotations());
        RoomRotation rotation = random(possible);

        BuiltRoom entrance = new BuiltRoom(rotation, entranceRoom.loc);

        int mid = dungeon.getMiddle();
        dungeon.addRoom(mid, mid, entrance);
    }

}
