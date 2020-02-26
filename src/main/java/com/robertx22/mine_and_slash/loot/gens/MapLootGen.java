package com.robertx22.mine_and_slash.loot.gens;

import com.robertx22.mine_and_slash.config.forge.ModConfig;
import com.robertx22.mine_and_slash.loot.LootInfo;
import com.robertx22.mine_and_slash.loot.blueprints.MapBlueprint;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerMapCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.LootType;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.WorldUtils;
import net.minecraft.item.ItemStack;

public class MapLootGen extends BaseLootGen<MapBlueprint> {

    public MapLootGen(LootInfo info) {
        super(info);
    }

    @Override
    public float baseDropChance() {
        float chance = ModConfig.INSTANCE.DropRates.MAP_DROPRATE.get()
            .floatValue();

        if (info.killer != null) {
            PlayerMapCap.IPlayerMapData map = Load.playerMapData(info.killer);
            chance *= map.getMapLootMultiplierForTime();
            if (WorldUtils.isMapWorldClass(info.killer.world)) {
                chance *= 0.2F;
            } else {
                chance *= 1.2F;
            }
        }

        return chance;

    }

    @Override
    public LootType lootType() {
        return LootType.Map;
    }

    @Override
    public boolean condition() {
        return info.level >= ModConfig.INSTANCE.Server.MAPS_DROP_AFTER_LEVEL.get();
    }

    @Override
    public boolean hasLevelDistancePunishment() {
        return false;
    }

    @Override
    public ItemStack generateOne() {
        MapBlueprint blueprint = new MapBlueprint(info.level, info.tier);

        if (info.killer != null) {
            info.killer.getCapability(PlayerMapCap.Data)
                .ifPresent(x -> x
                    .onMapDropped());
        }

        return blueprint.createStack();
    }

}
