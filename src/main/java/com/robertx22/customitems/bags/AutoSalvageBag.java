package com.robertx22.customitems.bags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import com.robertx22.database.rarities.ItemRarity;
import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.db_lists.Rarities;
import com.robertx22.saveclasses.GearItemData;
import com.robertx22.saveclasses.ISalvagable;
import com.robertx22.saveclasses.MapItemData;
import com.robertx22.saveclasses.SpellItemData;
import com.robertx22.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.saveclasses.rune.RuneItemData;
import com.robertx22.uncommon.datasaving.Gear;
import com.robertx22.uncommon.datasaving.Map;
import com.robertx22.uncommon.datasaving.Rune;
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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class AutoSalvageBag extends Item implements IBauble {

    public static HashMap<Integer, Item> Items = new HashMap<Integer, Item>();

    public int rarity = 0;

    private final int defaul_gear_rarity_salvage = 0;
    private final int default_spell_rarity_salvage = 0;
    private final int default_map_rarity_salvage = -1;
    private final int default_rune_rarity_salvage = 1;

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
	    ItemStack bag = player.getHeldItem(hand);

	    NBTTagCompound nbt = bag.getTagCompound();

	    if (nbt == null) {
		nbt = new NBTTagCompound();
	    }

	    ItemStack stack = player.getHeldItemOffhand();

	    if (stack != null && !stack.isEmpty()) {

		GearItemData gear = Gear.Load(stack);
		if (gear != null) {
		    nbt.setInteger("gear", gear.Rarity);
		    successChat(player);
		}
		SpellItemData spell = Spell.Load(stack);
		if (spell != null) {
		    nbt.setInteger("spell", spell.rarity);
		    successChat(player);
		}
		MapItemData map = Map.Load(stack);
		if (map != null) {
		    nbt.setInteger("map", map.rarity);
		    successChat(player);
		}
		RuneItemData rune = Rune.Load(stack);
		if (rune != null) {
		    nbt.setInteger("rune", rune.rarity);
		    successChat(player);
		}

		bag.setTagCompound(nbt);

	    } else {
		nbt.setInteger("gear", -1);
		nbt.setInteger("spell", -1);
		nbt.setInteger("map", -1);
		nbt.setInteger("rune", -1);

		player.sendMessage(new TextComponentString("Bag Config Cleared"));
	    }
	}

	return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }

    private void successChat(EntityPlayer player) {
	player.sendMessage(new TextComponentString("You Have Successfully Modified your Automatic Salvaging Bag."));
    }

    public ItemStack getSalvageResultForItem(ISalvagable sal) {

	float salvageBonus = this.getBonusSalvageChance();
	return sal.getSalvageResult(salvageBonus);
    }

    public static ISalvagable getSalvagable(ItemStack st) {

	GearItemData gear = Gear.Load(st);
	if (gear != null && gear.isUnique == false) { // DO NOT SALVAGE UNIQUE ITEMS, their rarity was -1. should
						      // probably change that
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

	RuneItemData rune = Rune.Load(st);
	if (rune != null) {
	    return rune;
	}
	return null;

    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

	NBTTagCompound nbt = stack.getTagCompound();

	if (nbt == null) {
	    nbt = new NBTTagCompound();
	}

	tooltip.add("Automatically Salvages items! (Gears, Spells, Maps)");

	tooltip.add("");

	tooltip.add("Salvages Gears: ");
	tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Items), this.getGear(nbt)));

	tooltip.add("Salvages Spells: ");
	tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Spells), this.getSpell(nbt)));

	tooltip.add("Salvages Maps: ");
	tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Maps), this.getMap(nbt)));

	tooltip.add("Salvages Runes: ");
	tooltip.add(getSalvagedRarities(new ArrayList<Rarity>(Rarities.Runes), this.getRune(nbt)));

	tooltip.add("");

	tooltip.add("Bonus Salvage Item Chance: " + this.getBonusSalvageChance() + "%");
	tooltip.add("");

	tooltip.add("Place on your Baubles Chest Slot");
	tooltip.add("");

	tooltip.add(TextFormatting.GREEN + "How To Configure which Rarities are Salvaged: ");
	tooltip.add("Place An Item Of Maximum Rarity You want to");
	tooltip.add("salvage in your off-hand");
	tooltip.add("Then Right click with this bag.");
	tooltip.add("If you put an Uncommon Sword for example, that means");
	tooltip.add("Common and Uncommon Gear Items will be salvaged");
	tooltip.add("To Not Salvage Any Items, clear the config by");
	tooltip.add("Right Clicking the bag while shield slot is empty.");

    }

    public String getSalvagedRarities(List<Rarity> rarities, int rarity) {

	String text = "";

	for (ItemRarity rar : Rarities.Items) {
	    if (rar.Rank() <= rarity) {
		if (text.length() > 0) {
		    text += TextFormatting.GRAY + ", ";
		}
		text += rar.Color() + rar.GUID();
	    }
	}

	if (text.length() == 0) {
	    text += "None";
	}

	return text;

    }

    public boolean shouldSalvageItem(ISalvagable sal, NBTTagCompound nbt) {

	int rarity = sal.getSalvagedRarity();

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

	return false;
    }

    private int getGear(NBTTagCompound nbt) {

	if (nbt != null) {
	    if (nbt.hasKey("gear")) {
		return nbt.getInteger("gear");
	    }
	}
	return this.defaul_gear_rarity_salvage;

    }

    private int getSpell(NBTTagCompound nbt) {
	if (nbt != null) {
	    if (nbt.hasKey("spell")) {
		return nbt.getInteger("spell");
	    }
	}
	return this.default_spell_rarity_salvage;

    }

    private int getRune(NBTTagCompound nbt) {
	if (nbt != null) {
	    if (nbt.hasKey("rune")) {
		return nbt.getInteger("rune");
	    }
	}
	return this.default_rune_rarity_salvage;

    }

    private int getMap(NBTTagCompound nbt) {
	if (nbt != null) {

	    if (nbt.hasKey("map")) {
		return nbt.getInteger("map");
	    }
	}
	return this.default_map_rarity_salvage;

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPickupItem(EntityItemPickupEvent event) {

	if (event.getEntityPlayer() != null) {

	    EntityPlayer player = event.getEntityPlayer();

	    if (!player.world.isRemote) {

		ItemStack stack = event.getItem().getItem();

		IBaublesItemHandler handler = BaublesApi.getBaublesHandler(player);
		for (int a = 0; a < handler.getSlots(); a++) {
		    if (!handler.getStackInSlot(a).isEmpty()
			    && handler.getStackInSlot(a).getItem() instanceof AutoSalvageBag) {

			AutoSalvageBag salvageBag = (AutoSalvageBag) handler.getStackInSlot(a).getItem();

			ISalvagable sal = getSalvagable(stack);

			if (sal != null) {

			    if (salvageBag.shouldSalvageItem(sal, handler.getStackInSlot(a).getTagCompound())) {

				ItemStack result = salvageBag.getSalvageResultForItem(sal);

				stack.setCount(0);

				EntityItem item = new EntityItem(player.world, player.posX, player.posY, player.posZ,
					result);
				item.setNoPickupDelay();
				player.world.spawnEntity(item);

			    }
			}

			return;

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
