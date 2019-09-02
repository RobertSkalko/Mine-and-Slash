package com.robertx22.mine_and_slash.db_lists.registry.empty_entries;

import com.robertx22.mine_and_slash.database.gearitemslots.Boots;
import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.items.unique_items.IUnique;
import com.robertx22.mine_and_slash.database.stats.StatMod;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EmptyUnique implements IUnique {
    @Override
    public List<StatMod> uniqueStats() {
        return new ArrayList<>();
    }

    @Override
    public List<StatMod> primaryStats() {
        return null;
    }

    @Override
    public String locDescLangFileGUID() {
        return "";
    }

    @Override
    public String locDescForLangFile() {
        return "";
    }

    @Override
    public String locNameLangFileGUID() {
        return "";
    }

    @Override
    public String locNameForLangFile() {
        return "";
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public int Tier() {
        return 0;
    }

    @Override
    public GearItemSlot getGearSlot() {
        return new Boots();
    }

    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public Set<ToolType> getToolTypes(ItemStack stack) {
        return null;
    }

    @Override
    public int getHarvestLevel(ItemStack stack, ToolType tool,
                               @Nullable PlayerEntity player,
                               @Nullable BlockState blockState) {
        return 0;
    }

    @Override
    public ItemStackTileEntityRenderer getTileEntityItemStackRenderer() {
        return null;
    }

    @Override
    public Set<ResourceLocation> getTags() {
        return null;
    }
}
