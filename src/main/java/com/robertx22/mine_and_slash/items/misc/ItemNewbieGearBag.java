package com.robertx22.mine_and_slash.items.misc;

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

                GearBlueprint weaponPrint = new GearBlueprint(1);
                weaponPrint.SetSpecificType(Sword.INSTANCE.GUID());
                weaponPrint.level.LevelRange = false;

                GearItemData wepData = weaponPrint.createData();
                wepData.isSalvagable = false;

                ItemStack weaponStack = GearCreationUtils.CreateStack(wepData);
                playerIn.dropItem(weaponStack, false, true);

                for (int i = 0; i < ITEMS_AMOUNT; i++) {

                    GearBlueprint blueprint = new GearBlueprint(1);
                    blueprint.level.LevelRange = false;

                    GearItemData data = blueprint.createData();
                    data.isSalvagable = false;
                    ItemStack stack = GearCreationUtils.CreateStack(data);

                    playerIn.dropItem(stack, false, true);

                }

                playerIn.getHeldItem(handIn).shrink(1);

                return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
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
