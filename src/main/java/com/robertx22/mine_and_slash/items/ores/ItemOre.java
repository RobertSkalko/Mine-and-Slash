package com.robertx22.mine_and_slash.items.ores;

import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.IWeighted;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemOre extends Item implements IWeighted, IAutoLocMultiLore, IAutoLocName {
    public static HashMap<Integer, Item> ItemOres = new HashMap<Integer, Item>();
    public static HashMap<Integer, BlockItem> ItemBlocks = new HashMap<Integer, BlockItem>();
    public static HashMap<Integer, Block> Blocks = new HashMap<Integer, Block>();

    int rarity;

    public List<Integer> RepairValues = Arrays.asList(20, 30, 75, 125, 300, 600);

    public int GetFuelValue() {

        return RepairValues.get(rarity);

    }

    @Override
    public int Weight() {
        return Rarities.Items.get(rarity).Weight();
    }

    public ItemOre(String name, int rarity) {

        super(new Properties().group(CreativeTabs.MyModTab));

        RegisterItemUtils.RegisterItemName(this, name);

        this.rarity = rarity;

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        for (ITextComponent lore : this.getComponents()) {
            tooltip.add(Styles.GREENCOMP().appendSibling(lore));
        }

    }

    public static void RegisterItems(RegistryEvent.Register<Item> event) {

        Rarities.Items.rarities()
                .forEach((x) -> ItemOres.put(x.Rank(), new ItemOre("ore" + x.Rank(), x.Rank())));

        ItemOres.values().forEach((x) -> event.getRegistry().register(x));
        ItemBlocks.values().forEach((x) -> event.getRegistry().register(x));

    }

    public static void RegisterBlocks(RegistryEvent.Register<Block> event) {

        for (ItemRarity rarity : Rarities.Items.rarities()) {

            int i = rarity.Rank();

            BlockOre block = new BlockOre(i, Material.ROCK);
            Blocks.put(i, block);

            BlockItem itemblock = (BlockItem) new BlockItem(block, new Properties().group(CreativeTabs.MyModTab))
                    .setRegistryName(Ref.MODID + ":ore_block" + i);
            ItemBlocks.put(i, itemblock);
        }

        Blocks.values().forEach((x) -> event.getRegistry().register(x));

    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Misc;
    }

    @Override
    public String locNameLangFileGUID() {
        return this.getRegistryName().toString();
    }

    @Override
    public String locNameForLangFile() {
        return Rarities.Items.get(rarity).textFormatColor() + Rarities.Items.get(rarity)
                .locNameForLangFile() + " Ore";
    }

    @Override
    public String locMultiLoreLangFileGUID() {
        return Ref.MODID + ".ores";
    }

    @Override
    public String GUID() {
        return "ore" + this.rarity;
    }

    @Override
    public AutoLocGroup locLoresGroup() {
        return AutoLocGroup.Misc;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Precious Ingredient that can be used in many ways:", " * Crafting Currency Items", " * Repairing Gear", " * Crafting new Gear");
    }
}
