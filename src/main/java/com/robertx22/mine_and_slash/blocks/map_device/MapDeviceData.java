package com.robertx22.mine_and_slash.blocks.map_device;

import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.List;

@Storable
public class MapDeviceData {

    public MapDeviceData() {

    }

    public MapDeviceData(MapItemData map, PlayerEntity player) {
        this.players.clear();
        this.mapUsed = map.clone();
        addPlayer(player);
    }

    public void addPlayer(PlayerEntity player) {
        players.add(player.getUniqueID()
            .toString());
    }

    public boolean canPlayerUse(PlayerEntity player) {

        for (String s : players) {
            if (player.getUniqueID()
                .toString()
                .equals(s)) {
                player.sendMessage(new StringTextComponent("You can't use this map ticket. You either sacrificed the map or already took 1 ticket."));
                return false;
            }
        }

        player.sendMessage(new StringTextComponent("Ticket used. You now have the same map as the player who sacrificed it and will join the same world upon teleportation."));
        return true;
    }

    @Store
    public List<String> players = new ArrayList<>();
    @Store
    public MapItemData mapUsed = MapItemData.empty();

}
