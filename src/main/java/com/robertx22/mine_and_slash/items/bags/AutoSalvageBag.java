package com.robertx22.mine_and_slash.items.bags;

import com.robertx22.mine_and_slash.a_libraries.curios.interfaces.ISalvageBag;
import com.robertx22.mine_and_slash.database.rarities.ItemRarity;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.db_lists.Rarities;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.MapItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.RuneItemData;
import com.robertx22.mine_and_slash.saveclasses.item_classes.SpellItemData;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocMultiLore;
import com.robertx22.mine_and_slash.uncommon.interfaces.IAutoLocName;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.DataItemType;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ICommonDataItem;
import com.robertx22.mine_and_slash.uncommon.interfaces.data_items.ISalvagable;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.Tooltip;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AutoSalvageBag extends Item implements ISalvageBag, IAutoLocName, IAutoLocMultiLore {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public int rarity = 0;

    private final int defaul_gear_rarity_salvage = 0;
    private final int default_spell_rarity_salvage = 0;
    private final int default_map_rarity_salvage = -1;
    private final int default_rune_rarity_salvage = 1;

    public AutoSalvageBag(int rarity) {

        super(new Properties().group(CreativeTabs.MyModTab));

        this.rarity = rarity;

        RegisterItemUtils.RegisterItemName(this, "auto_salvage_bag" + rarity);
    }

    private List<Float> BonusSalvageValues = Arrays.asList(0F, 2.5F, 5F, 10F, 15F, 20F, 25F);

    public float getBonusSalvageChance() {
        return BonusSalvageValues.get(rarity);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player,
                                                    Hand hand) {

        if (!world.isRemote) {
            ItemStack bag = player.getHeldItem(hand);

            CompoundNBT nbt = bag.getTag();

            if (nbt == null) {
                nbt = new CompoundNBT();
            }

            ItemStack stack = player.getHeldItemOffhand();

            if (stack != null && !stack.isEmpty()) {

                ICommonDataItem data = ICommonDataItem.load(stack);

                if (data != null) {
                    nbt.putInt(data.getDataType().nbtGUID, data.getRarityRank());
                    successChat(player);
                } else {
                    for (DataItemType type : DataItemType.values()) {
                        nbt.putInt(type.nbtGUID, -1);
                    }
                    player.sendMessage(new StringTextComponent("Bag Config Cleared"));
                }

            }
            bag.setTag(nbt);
        }

        return new ActionResult<ItemStack>(ActionResultType.PASS, player.getHeldItem(hand));
    }

    private void successChat(PlayerEntity player) {
        player.sendMessage(new StringTextComponent("You Have Successfully Modified your Automatic Salvaging Bag."));
    }

    public ItemStack getSalvageResultForItem(ISalvagable sal) {

        float salvageBonus = this.getBonusSalvageChance();
        return sal.getSalvageResult(salvageBonus);
    }

    public static ISalvagable getSalvagable(ItemStack st) {

        ICommonDataItem data = ICommonDataItem.load(st);

        if (data != null) {
            if (data.isSalvagable(ISalvagable.SalvageContext.AUTO_SALVAGE_BAG)) {
                return data;
            }
        }

        return null;

    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn,
                               List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        CompoundNBT nbt = stack.getTag();

        if (nbt == null) {
            nbt = new CompoundNBT();
        }

        Tooltip.add(Styles.GREENCOMP()
                .appendSibling(Words.Automatically_salvages_items.locName())
                .appendText("!"), tooltip);

        Tooltip.add("", tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(Words.Gears.locName().appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Items.getRarities()), this
                .getGear(nbt)), tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(Words.Spells.locName().appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Spells.getRarities()), this
                .getSpell(nbt)), tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(Words.Maps.locName().appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Maps.getRarities()), this
                .getMap(nbt)), tooltip);

        Tooltip.add(Styles.YELLOWCOMP()
                .appendSibling(Words.Runes.locName().appendText(":")), tooltip);
        Tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Runes.getRarities()), this
                .getRune(nbt)), tooltip);

        Tooltip.add("", tooltip);

        Tooltip.add(Styles.LIGHT_PURPLECOMP()
                .appendSibling(Words.Bonus_Salvage_Chance.locName())
                .appendText(": " + this.getBonusSalvageChance() + "%"), tooltip);

        Tooltip.add("", tooltip);

        Tooltip.add(Styles.GREENCOMP()
                .appendSibling(Words.Works_when_equipped.locName()), tooltip);
        Tooltip.add("", tooltip);

        if (Screen.hasShiftDown() == false) {

            Tooltip.add(Styles.GREENCOMP()
                    .appendSibling(Words.Press_Shift_For_Setup_Info.locName()), tooltip);

        } else {

            for (ITextComponent lore : this.getComponents()) {
                tooltip.add(Styles.GREENCOMP().appendSibling(lore));
            }

        }

    }

    public ITextComponent getSalvagedRarities(List<Rarity> rarities, int rarity) {

        ITextComponent text = new StringTextComponent("");

        for (ItemRarity rar : Rarities.Items.rarities()) {
            if (rar.Rank() <= rarity) {

                if (text.getSiblings().size() > 0) {
                    text.appendText(TextFormatting.GRAY + ", ");
                }
                text.appendText(rar.textFormatColor() + "").appendSibling(rar.locName());
            }
        }

        if (text.getSiblings().size() < 1) {
            text.appendSibling(Words.None.locName());
        }

        return text;

    }

    public boolean shouldSalvageItem(ISalvagable sal, CompoundNBT nbt) {

        int rarity = sal.getRarityRank();

        if (sal.isSalvagable(ISalvagable.SalvageContext.AUTO_SALVAGE_BAG)) {
            if (sal instanceof GearItemData) {
                if (rarity <= getGear(nbt)) {
                    return true;
                }
            } else if (sal instanceof SpellItemData) {
                if (rarity <= getSpell(nbt)) {
                    return true;
                }
            } else if (sal instanceof MapItemData) {
                if (rarity <= getMap(nbt)) {
                    return true;
                }
            } else if (sal instanceof RuneItemData) {
                if (rarity <= getRune(nbt)) {
                    return true;
                }

            }
        }

        return false;
    }

    private int getGear(CompoundNBT nbt) {

        if (nbt != null) {
            if (nbt.contains("gear")) {
                return nbt.getInt("gear");
            }
        }
        return this.defaul_gear_rarity_salvage;

    }

    private int getSpell(CompoundNBT nbt) {
        if (nbt != null) {
            if (nbt.contains("spell")) {
                return nbt.getInt("spell");
            }
        }
        return this.default_spell_rarity_salvage;

    }

    private int getRune(CompoundNBT nbt) {
        if (nbt != null) {
            if (nbt.contains("rune")) {
                return nbt.getInt("rune");
            }
        }
        return this.default_rune_rarity_salvage;

    }

    private int getMap(CompoundNBT nbt) {
        if (nbt != null) {

            if (nbt.contains("map")) {
                return nbt.getInt("map");
            }
        }
        return this.default_map_rarity_salvage;

    }

    @Override
    public AutoLocGroup locLoresGroup() {
        return AutoLocGroup.Misc;
    }

    @Override
    public List<String> loreLines() {
        return Arrays.asList("Place An Item Of Maximum Rarity You want to", "salvage in your off-hand.", "Then right click with this bag.", "To Not Salvage Any Items, clear the config by", "Right Clicking the bag while shield slot is empty.");
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
        return Rarities.Items.get(this.rarity).Color() + "Auto Salvage Bag";
    }

    @Override
    public String GUID() {
        return "auto_salvage_bag";
    }

    @Override
    public String locMultiLoreLangFileGUID() {
        return Ref.MODID + ".auto_salvage_bag";
    }
}