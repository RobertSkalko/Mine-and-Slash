package com.robertx22.customitems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.ISalvagable;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.datasaving.Spell;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class AutoSalvageBag extends Item implements IBauble {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public int rarity = 0;

    public AutoSalvageBag(int rarity) {
	this.rarity = rarity;

	this.setMaxDamage(0);
	this.setCreativeTab(CreativeTabList.MyModTab);

	RegisterItemUtils.RegisterItemName(this, "auto_salvage_bag" + rarity);
    }

    private List<Float> BonusSalvageValues = Arrays.asList(5F, 10F, 20F, 30F, 40F, 50F, 100F);

    public float getBonusSalvageChance() {
	return BonusSalvageValues.get(rarity);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
	return BaubleType.BODY;
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

	if (!world.isRemote) {
	    ItemStack stack = player.getHeldItem(hand);

	    NBTTagCompound nbt = stack.serializeNBT();

	}

	return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    public ItemStack getSalvageResultForItem(ISalvagable sal) {

	float salvageBonus = this.getBonusSalvageChance();
	return sal.getSalvageResult(salvageBonus);
    }

    public static ISalvagable getSalvagable(ItemStack st) {

	GearItemData gear = Gear.Load(st);
	if (gear != null) {
	    return gear;
	}

	SpellItemData spell = Spell.Load(st);
	if (spell != null) {
	    return spell;
	}

	MapItemData map = Map.Load(st);
	if (map != null) {
	    return map;
	}

	return null;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	// tooltip.add(CLOC.tooltip("Right Click To Configure Auto Salvaging."));

	tooltip.add("Right Click To Configure Auto Salvaging.");

	tooltip.add("Place on your Baubles Chest Slot");

    }

    @SubscribeEvent
    public static void onPickupItem(EntityItemPickupEvent event) {

	if (event.getEntityPlayer() != null) {

	    EntityPlayer player = event.getEntityPlayer();

	    IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
	    for (int a = 0; a < handler.getSlots(); a++) {
		if (!handler.getStackInSlot(a).isEmpty()
			&& handler.getStackInSlot(a).getItem() instanceof AutoSalvageBag) {

		    AutoSalvageBag salvageBag = (AutoSalvageBag) handler.getStackInSlot(a).getItem();

		    ItemStack stack = event.getItem().getItem();
		    ISalvagable sal = getSalvagable(stack);

		    if (sal != null) {

			ItemStack result = salvageBag.getSalvageResultForItem(sal);

			stack.setCount(0);

			// we drop the item and pick it up so the events for picking item works, that
			// means it works with currency bags
			EntityItem item = new EntityItem(player.world, player.posX, 0, player.posZ, result);
			int hook = net.minecraftforge.event.ForgeEventFactory.onItemPickup(item, player);
			// dont remove

		    }

		}
	    }

	}
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
	Rarities.Items.forEach((x) -> Items.put(x.Rank(), new AutoSalvageBag(x.Rank())));
	Items.values().forEach((x) -> event.getRegistry().register(x));
    }

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
	Items.values().forEach((x) -> RegisterUtils.registerRender(x));
    }

}
