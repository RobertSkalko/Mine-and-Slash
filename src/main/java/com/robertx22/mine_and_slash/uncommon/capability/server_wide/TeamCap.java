package com.robertx22.mine_and_slash.uncommon.capability.server_wide;

import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.PlayerTeamsData;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseProvider;
import com.robertx22.mine_and_slash.uncommon.capability.bases.BaseStorage;
import com.robertx22.mine_and_slash.uncommon.capability.bases.ICommonCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import com.robertx22.mine_and_slash.uncommon.wrappers.SText;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber
public class TeamCap {

    static ITeamData data;

    public static ITeamData getCapability() {
        if (data == null) {
            data = new DefaultImpl();
        }
        return data;
    }

    public static final ResourceLocation RESOURCE = new ResourceLocation(Ref.MODID, "teams");

    @CapabilityInject(ITeamData.class)
    public static final Capability<ITeamData> Data = null;

    public interface ITeamData extends ICommonCap {

        boolean isOnSameTeam(ServerPlayerEntity p1, ServerPlayerEntity p2);

        void joinTeam(ServerPlayerEntity player, String teamID);

        void createTeam(ServerPlayerEntity player);

        boolean isPlayerInATeam(ServerPlayerEntity player);

        void invite(ServerPlayerEntity player, String teamID);

        void leaveTeam(ServerPlayerEntity player);

        String getTeamId(ServerPlayerEntity player);

        List<PlayerEntity> getPlayersInTeam(ServerPlayerEntity player);
    }

    @Mod.EventBusSubscriber
    public static class EventHandler {
        @SubscribeEvent
        public static void onEntityConstruct(AttachCapabilitiesEvent<World> event) {
            //event.addCapability(RESOURCE, new Provider());
        }
    }

    public static class Provider extends BaseProvider<ITeamData> {

        @Override
        public ITeamData defaultImpl() {
            return new DefaultImpl();
        }

        @Override
        public Capability<ITeamData> dataInstance() {
            return Data;
        }
    }

    static String DATA_LOC = Ref.MODID + ":data";

    public static class DefaultImpl implements ITeamData {

        PlayerTeamsData teams = new PlayerTeamsData();

        @Override
        public CompoundNBT saveToNBT() {

            CompoundNBT nbt = new CompoundNBT();

            if (teams != null) {
                LoadSave.Save(teams, nbt, DATA_LOC);
            }

            return nbt;

        }

        @Override
        public void loadFromNBT(CompoundNBT nbt) {

            teams = LoadSave.Load(PlayerTeamsData.class, new PlayerTeamsData(), nbt, DATA_LOC);

            if (teams == null) {
                teams = new PlayerTeamsData();
            }
        }

        @Override
        public boolean isOnSameTeam(ServerPlayerEntity p1, ServerPlayerEntity p2) {
            try {

                String id1 = teams.getTeamId(p1);
                String id2 = teams.getTeamId(p2);

                if (id1.isEmpty() || id2.isEmpty()) {
                    return false;
                }

                return id1.equals(id2);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        public void joinTeam(ServerPlayerEntity player, String teamID) {

            try {
                PlayerTeamsData.Team team = teams.teamIDxTeamDataMap.get(teamID);

                if (team.tryJoin(player)) {
                    teams.playerIDxTeamIDMap.put(teams.getPlayerId(player), teamID);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void createTeam(ServerPlayerEntity player) {

            try {
                if (isPlayerInATeam(player)) {
                    player.sendMessage(new SText("Can't create a team if you're already in one. Leave first."));
                } else {

                    PlayerTeamsData.Team team = new PlayerTeamsData.Team();
                    team.addPlayer(player);

                    String teamID = UUID.randomUUID()
                        .toString();

                    teams.playerIDxTeamIDMap.put(teams.getPlayerId(player), teamID);
                    teams.teamIDxTeamDataMap.put(teamID, team);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public boolean isPlayerInATeam(ServerPlayerEntity player) {
            try {
                PlayerTeamsData.Team team = teams.teamIDxTeamDataMap.get(teams.getTeamId(player));

                String id = teams.getPlayerId(player);
                return team != null && team.getPlayerIds()
                    .contains(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        public void invite(ServerPlayerEntity player, String teamID) {
            try {
                teams.teamIDxTeamDataMap.get(teamID)
                    .invite(player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void leaveTeam(ServerPlayerEntity player) {
            try {
                if (!isPlayerInATeam(player)) {
                    player.sendMessage(new SText("You are not inside a team."));
                } else {
                    teams.playerIDxTeamIDMap.remove(teams.getTeamId(player));
                    teams.teamIDxTeamDataMap.get(teams.getTeamId(player))
                        .removePlayer(player);
                    player.sendMessage(new SText("Left team."));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String getTeamId(ServerPlayerEntity player) {
            return teams.getTeamId(player);
        }

        @Override
        public List<PlayerEntity> getPlayersInTeam(ServerPlayerEntity player) {

            List<PlayerEntity> list = Arrays.asList(player);

            try {
                teams.teamIDxTeamDataMap.get(teams.getTeamId(player))
                    .getPlayerIds()
                    .stream()
                    .forEach(x -> {
                        if (x != null) {
                            PlayerEntity p2 = MapManager.getServer()
                                .getPlayerList()
                                .getPlayerByUUID(UUID.fromString(x));

                            if (p2 != null && p2 != player) {
                                list.add(p2);
                            }
                        }
                    });

            } catch (Exception e) {
                e.printStackTrace();
            }

            return list;
        }
    }

    public static class Storage extends BaseStorage<ITeamData> {

    }

}
