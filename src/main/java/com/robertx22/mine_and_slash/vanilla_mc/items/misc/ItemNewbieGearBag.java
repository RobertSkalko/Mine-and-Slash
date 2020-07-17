package com.robertx22.mine_and_slash.vanilla_mc.items.misc;

import com.robertx22.mine_and_slash.database.base.CreativeTabs;
import com.robertx22.mine_and_slash.database.data.gearitemslots.bases.BaseGearType;
import com.robertx22.mine_and_slash.database.data.gearitemslots.weapons.melee.GemstoneSword;
import com.robertx22.mine_and_slash.loot.blueprints.GearBlueprint;
import com.robertx22.mine_and_slash.loot.generators.util.GearCreationUtils;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import com.robertx22.mine_and_slash.vanilla_mc.items.BaseItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ItemNewbieGearBag extends BaseItem {

    public ItemNewbieGearBag() {
        super(new Properties().group(CreativeTabs.MyModTab));

    }

    public static int ITEMS_AMOUNT = 6;

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {

        if (!worldIn.isRemote) {
            try {

                List<BaseGearType> list = new ArrayList<>();

                list.add(GemstoneSword.INSTANCE);

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

    private static GearBlueprint getBlueprint(BaseGearType type) {
        GearBlueprint print = new GearBlueprint(1);
        print.gearItemSlot.set(type);
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
