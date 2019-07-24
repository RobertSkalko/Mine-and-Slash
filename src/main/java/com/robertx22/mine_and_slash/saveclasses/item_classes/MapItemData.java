package com.robertx22.mine_and_slash.saveclasses.item_classes;

import com.robertx22.mine_and_slash.config.ModConfig;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.database.rarities.MapRarity;
import com.robertx22.mine_and_slash.database.world_providers.IWP;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.db_lists.bases.IBonusLootMulti;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
import com.robertx22.mine_and_slash.dimensions.MapManager;
import com.robertx22.mine_and_slash.items.currency.CurrencyItem;
import com.robertx22.mine_and_slash.items.ores.ItemOre;
import com.robertx22.mine_and_slash.saveclasses.Unit;
import com.robertx22.mine_and_slash.saveclasses.gearitem.StatModData;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.mine_and_slash.saveclasses.mapitem.MapAffixData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap.UnitData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.enumclasses.AffectedEntities;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.*;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Storable
public class MapItemData implements ICommonDataItem<MapRarity>, IBonusLootMulti {

    @Store
    public int minutes = 30; // default

    @Store
    public int level = 1;

    @Store
    public int tier = 0;

    @Store
    public int rarity = 0;

    @Store
    public int maxPlayersInGroup = 0;
    @Store
    public boolean groupPlay = false;

    @Store
    public boolean isPermaDeath = false;

    @Store
    public List<MapAffixData> affixes = new ArrayList<MapAffixData>();

    @Store
    public String worldGeneratorName;

    @Nonnull
    public IWP getIWP() {

        return SlashRegistry.WorldProviders().get(this.worldGeneratorName);

    }

    @Override
    public float getBonusLootMulti() {

        return 1 * getTotalPercents() * getPermaDeathMultiplier();

    }

    public float getPermaDeathMultiplier() {
        return this.isPermaDeath ? 1.2F : 1;
    }

    public boolean increaseLevel(int i) {

        int lvl = level + i;

        if (lvl > ModConfig.INSTANCE.Server.MAXIMUM_PLAYER_LEVEL.get()) {
            return false;
        }

        level = lvl;

        return true;
    }

    public boolean increaseTier(int i) {

        int tier = this.tier + i;

        if (tier > 20) {
            return false;
        }

        this.tier = tier;

        return true;
    }

    private float getTotalPercents() {

        float total = 1;
        for (MapAffixData affix : affixes) {
            total += affix.getBonusLootMultiplier();
        }
        return total;
    }

    public static List<MapAffixData> getAllAffixesThatAffect(List<MapAffixData> affixes,
                                                             LivingEntity entity) {

        AffectedEntities affected = AffectedEntities.All;

        if (entity instanceof PlayerEntity) {
            affected = AffectedEntities.Players;
        } else if (EntityTypeUtils.isMob(entity)) {
            affected = AffectedEntities.Mobs;
        }

        return getAllAffixesThatAffect(affixes, affected);

    }

    public static List<MapAffixData> getAllAffixesThatAffect(List<MapAffixData> affixes,
                                                             AffectedEntities affected) {

        List<MapAffixData> list = new ArrayList<>();

        for (MapAffixData data : affixes) {
            if (data.affectedEntities.equals(affected)) {
                list.add(data);
            }
        }
        return list;
    }

    @Override
    public String getUniqueGUID() {
        return this.worldGeneratorName;
    }

    public List<MapAffixData> getAllAffixesThatAffect(AffectedEntities affected) {

        List<MapAffixData> list = new ArrayList<>();

        for (MapAffixData data : affixes) {
            if (data.affectedEntities.equals(affected)) {
                list.add(data);
            }
        }
        return list;
    }

    public DimensionType setupPlayerMapData(World ogworld, BlockPos pos,
                                            PlayerEntity player) {

        UnitData unit = Load.Unit(player);

        ParticleUtils.spawnEnergyRestoreParticles(player, 10);

        return MapManager.setupPlayerMapDimension(player, unit, this, pos);

    }

    @Override
    public ItemStack getSalvageResult(float salvageBonus) {

        int min = tryIncreaseAmount(salvageBonus, 1);
        int max = tryIncreaseAmount(salvageBonus, 3);

        ItemStack stack = ItemStack.EMPTY;

        if (RandomUtils.roll(this.getRarity().specialItemChance())) {

            Item item = RandomUtils.weightedRandom(ListUtils.SameTierOrLess(CurrencyItem.ITEMS, tier));

            stack = new ItemStack(item);
        } else {

            int amount = RandomUtils.RandomRange(min, max);

            ItemOre ore = (ItemOre) ItemOre.ItemOres.get(rarity);

            stack = new ItemStack(ore);
            stack.setCount(amount);

        }

        return stack;
    }

    @Override
    public DataItemType getDataType() {
        return DataItemType.MAP;
    }

    @Override
    public void BuildTooltip(ItemStack stack, ItemTooltipEvent event, Unit unit,
                             UnitData unitdata) {

        if (unitdata != null) {

            List<ITextComponent> tooltip = event.getToolTip();

            ItemRarity rarity = Rarities.Items.get(this.rarity);

            tooltip.add(TooltipUtils.level(this.level));
            Tooltip.add("", tooltip);

            addAffixTypeToTooltip(this, tooltip, AffectedEntities.Mobs);
            addAffixTypeToTooltip(this, tooltip, AffectedEntities.Players);
            addAffixTypeToTooltip(this, tooltip, AffectedEntities.All);

            Tooltip.add("", tooltip);

            try {
                tooltip.add(Styles.BLUECOMP()
                        .appendSibling(Words.World_Type.locName())
                        .appendText(": ")
                        .appendSibling(this.getIWP().locName()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.GOLDCOMP()
                    .appendSibling(Words.Tier.locName()
                            .appendText(": " + this.tier)), tooltip);

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.GREENCOMP()
                    .appendSibling(Words.Minutes.locName()
                            .appendText(": " + this.minutes)), tooltip);

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.YELLOWCOMP()
                    .appendSibling(Words.Bonus_Loot_Amount.locName()
                            .appendText(": " + this.getBonusLootAmountInPercent() + "%")), tooltip);

            Tooltip.add("", tooltip);
            Tooltip.add(TooltipUtils.rarity(rarity), tooltip);

            if (this.isPermaDeath) {
                Tooltip.add("", tooltip);
                Tooltip.add(Styles.REDCOMP()
                        .appendSibling(Words.Permadeath.locName()), tooltip);
            }

            if (this.groupPlay) {
                Tooltip.add("", tooltip);
                Tooltip.add(Styles.GREENCOMP()
                        .appendSibling(Words.GroupPlay.locName()
                                .appendText(", ")
                                .appendSibling(Words.PartySize.locName())
                                .appendText(": " + this.maxPlayersInGroup)), tooltip);
            }

            Tooltip.add("", tooltip);
            Tooltip.add(Styles.BLUECOMP()
                    .appendSibling(CLOC.tooltip("put_in_mapdevice")), tooltip);

            Tooltip.add("", tooltip);

            tooltip.add(new StringTextComponent(TextFormatting.RED + "[*]").appendSibling(Words.MapWorldsAreResetOnGameReload
                    .locName()));
            tooltip.add(new StringTextComponent(TextFormatting.RED + "").appendSibling(Words.DoNotBuildInMaps
                    .locName()));

            TooltipUtils.removeDoubleBlankLines(tooltip, 20);

        }

    }

    private int getBonusLootAmountInPercent() {

        return (int) ((this.getBonusLootMulti() - 1) * 100);

    }

    private static void addAffixTypeToTooltip(MapItemData data,
                                              List<ITextComponent> tooltip,
                                              AffectedEntities affected) {

        List<MapAffixData> affixes = new ArrayList<>(data.getAllAffixesThatAffect(affected));

        affixes.addAll(MapItemData.getAllAffixesThatAffect(data.getIWP()
                .getMapAffixes(), affected));

        if (affixes.size() == 0) {
            return;
        }

        ITextComponent str = new StringTextComponent("");

        if (affected.equals(AffectedEntities.Players)) {
            str.appendSibling(Words.Player_Affixes.locName());
        } else if (affected.equals(AffectedEntities.Mobs)) {
            str.appendSibling(Words.Mob_Affixes.locName());
        } else {
            str.appendSibling(Words.Affixes_Affecting_All.locName());
        }

        Tooltip.add(Styles.GREENCOMP().appendSibling(str), tooltip);

        for (MapAffixData affix : affixes) {

            for (StatModData statmod : affix.getAffix().Stats(affix.percent)) {

                TooltipInfo info = new TooltipInfo(new EntityCap.DefaultImpl(), data.getRarity()
                        .StatPercents(), data.level);

                for (ITextComponent statstring : statmod.GetTooltipString(info)) {

                    Tooltip.add(statstring, tooltip);

                }

            }

        }
    }

    @Override
    public int getRarityRank() {
        return this.rarity;
    }

    @Override
    public MapRarity getRarity() {
        return Rarities.Maps.get(rarity);
    }

    @Override
    public int Tier() {
        return this.tier;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public String getSpecificType() {
        return this.worldGeneratorName;
    }
}
