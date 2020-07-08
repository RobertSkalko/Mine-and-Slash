package com.robertx22.mine_and_slash.uncommon.utilityclasses;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class TeamUtils {

    public static List<PlayerEntity> getOnlineTeamMembers(PlayerEntity player) {

        List<PlayerEntity> players = new ArrayList<>();
        players.add(player);

        if (player.getTeam() != null) {
            try {
                player.getServer()
                    .getPlayerList()
                    .getPlayers()
                    .forEach(x -> {
                        if (player.getTeam()
                            .isSameTeam(x.getTeam())) {
                            players.add(x);
                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return players;

    }

    public static boolean areOnSameTeam(PlayerEntity p1, PlayerEntity p2) {

        boolean vanilla = p1.getTeam() != null && p2.getTeam() != null && p1.getTeam()
            .isSameTeam(p2.getTeam());

        return vanilla;

    }

}
