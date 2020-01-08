package com.robertx22.mine_and_slash.quests.quest_rewards;

import com.robertx22.mine_and_slash.database.loot_crates.bases.LootCrate;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.quests.base.QuestResult;
import com.robertx22.mine_and_slash.quests.base.QuestReward;
import com.robertx22.mine_and_slash.quests.data.QuestSaveData;
import com.robertx22.mine_and_slash.uncommon.capability.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class MapQuestReward extends QuestReward {

    public static MapQuestReward INSTANCE = new MapQuestReward();

    private MapQuestReward() {

    }

    @Override
    public void giveReward(PlayerEntity player, QuestSaveData data) {

        if (data.questResult == QuestResult.COMPLETED) {

            PlayerMapCap.IPlayerMapData mapdata = Load.playerMapData(player);

            mapdata.onQuestFinished();

            LootCrate crate = SlashRegistry.LootCrates()
                    .get(mapdata.getMap().rewardCrateGUID);

            ItemStack stack = crate.getCrateStack(mapdata.getLevel(), mapdata.getTier(), data.reward.score.number);

            player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Map Completed! Further exploration seems to give little benefit."));

            SoundUtils.playSoundAtPlayer(player, SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1, 1);

            ParticleUtils.spawnParticles(ParticleTypes.HAPPY_VILLAGER, player, 50);

            player.entityDropItem(stack, 1F);
        }

    }

    @Override
    public String GUID() {
        return "map_quest_reward";
    }

}
