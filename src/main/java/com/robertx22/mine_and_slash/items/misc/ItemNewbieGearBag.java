package com.robertx22.mine_and_slash.items.misc;

import com.robertx22.mine_and_slash.database.gearitemslots.bases.GearItemSlot;
import com.robertx22.mine_and_slash.database.gearitemslots.curios.Ring;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateBoots;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateChest;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlateHelmet;
import com.robertx22.mine_and_slash.database.gearitemslots.plate.PlatePants;
import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Sword;
import com.robertx22.mine_and_slash.db_lists.CreativeTabs;
import com.robertx22.mine_and_slash.items.BaseItem;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.gens.util.GearCreationUtils;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.RegisterItemUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemNewbieGearBag extends BaseItem {

    @ObjectHolder(Ref.MODID + ":newbie_gear_bag")
    public static final Item ITEM = null;

    public ItemNewbieGearBag() {
        super(new Properties().group(CreativeTabs.MyModTab));

        RegisterItemUtils.RegisterItemName(this, "newbie_gear_bag");
    }

    public static int ITEMS_AMOUNT = 6;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (!worldIn.isRemote) {
            try {

                List<GearItemSlot> list = new ArrayList<>();

                list.add(PlatePants.INSTANCE);
                list.add(PlateChest.INSTANCE);
                list.add(PlateHelmet.INSTANCE);
                list.add(PlateBoots.INSTANCE);

                list.add(Sword.INSTANCE);

                list.add(Ring.INSTANCE);
                list.add(Ring.INSTANCE);

                list.forEach(x -> {
                    GearItemData data = getBlueprint(x).createData();
                    data.isSalvagable = false;
                    ItemStack weaponStack = GearCreationUtils.CreateStack(data);
                    playerIn.dropItem(weaponStack, false, true);
                });

                playerIn.getHeldItem(handIn)
                    .shrink(1);

                return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }

    private static GearBlueprint getBlueprint(GearItemSlot type) {
        GearBlueprint print = new GearBlueprint(1);
        print.gearItemSlot.set(type);
        print.level.LevelRange = false;
        print.rarity.setSpecificRarity(0);

        return print;

    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
                               ITooltipFlag flagIn) {

        tooltip.add(CLOC.tooltip("newbie_gear_bag"));

        tooltip.add(CLOC.lore("newbie_gear_bag1"));
        tooltip.add(CLOC.lore("newbie_gear_bag2"));

    }

}
