package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Storable
public class PlayerTeamsData {

    @Store
    public HashMap<String, String> playerIDxTeamIDMap = new HashMap<>();

    @Store
    public HashMap<String, Team> teamIDxTeamDataMap = new HashMap<>();

    public String getTeamId(PlayerEntity player) {
        return playerIDxTeamIDMap.getOrDefault(getPlayerId(player), "");
    }

    public static String getPlayerId(PlayerEntity player) {
        return player.getUniqueID()
            .toString();
    }

    @Storable
    public static class Team {

        public Team() {

        }

        public void invite(ServerPlayerEntity player) {
            invites.add(getPlayerId(player));

            player.sendMessage(getPlayer(owner).getDisplayName()
                .appendText(" invited you to a party. Do /slash party join ")
                .appendSibling(getPlayer(owner).getDisplayName())
                .appendText(" to join."));
        }

        public boolean tryJoin(ServerPlayerEntity player) {
            String playerID = getPlayerId(player);

            if (players.contains(playerID)) {
                player.sendMessage(new SText("You are already inside the team."));
                return false;
            } else if (invites.contains(playerID)) {
                invites.removeIf(x -> playerID.equals(x));
                players.add(playerID);
                player.sendMessage(new SText("Team joined."));
                return true;
            } else {
                player.sendMessage(new SText("Can't join team, you aren't invited."));
                return false;
            }
        }

        public void addPlayer(ServerPlayerEntity player) {
            players.add(getPlayerId(player));

            if (owner.isEmpty()) {
                owner = getPlayerId(player);
            }

        }

        public PlayerEntity getPlayer(String id) {
            return MapManager.getServer()
                .getPlayerList()
                .getPlayerByUUID(UUID.fromString(id));
        }

        public void removePlayer(ServerPlayerEntity player) {
            players.removeIf(x -> getPlayerId(player).equals(x));
        }

        public Set<String> getPlayerIds() {
            return players;
        }

        @Store
        String owner = "";

        @Store
        Set<String> players = new HashSet<>();
        @Store
        Set<String> invites = new HashSet<>();
    }

}
