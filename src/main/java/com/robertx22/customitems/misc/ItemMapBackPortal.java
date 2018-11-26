package com.robertx22.customitems.misc;

import com.robertx22.db_lists.CreativeTabList;
import com.robertx22.mmorpg.Ref;
import com.robertx22.uncommon.capability.WorldData.IWorldData;
import com.robertx22.uncommon.datasaving.Load;
import com.robertx22.uncommon.utilityclasses.RegisterItemUtils;
import com.robertx22.uncommon.utilityclasses.RegisterUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class ItemMapBackPortal extends Item {

	@GameRegistry.ObjectHolder(Ref.MODID + ":map_back_portal")
	public static final Item ITEM = null;

	public ItemMapBackPortal() {

		RegisterItemUtils.RegisterItemName(this, "map_back_portal");
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabList.MyModTab);

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		try {
			if (!worldIn.isRemote && entityIn instanceof EntityPlayer) {

				NBTTagCompound nbt = stack.getTagCompound();

				if (nbt == null) {
					nbt = new NBTTagCompound();
				}

				if (nbt.getBoolean("porting")) {

					BlockPos pos = BlockPos.fromLong(nbt.getLong("pos"));

					if (pos.distanceSq(entityIn.getPosition()) > 2) {

						nbt.setBoolean("porting", false);
						nbt.setInteger("ticks", 0);

						entityIn.sendMessage(new TextComponentString(
								"Teleport canceled, please don't move while attempting to teleport!"));

					} else {

						if (nbt.hasKey("ticks")) {

							int ticks = nbt.getInteger("ticks");
							nbt.setInteger("ticks", ticks + 1);

							if (ticks > 100) {

								nbt.setInteger("ticks", 0);
								nbt.setBoolean("porting", false);

								IWorldData data = Load.World(worldIn);
								data.teleportPlayerBack((EntityPlayer) entityIn);

								stack.setCount(stack.getCount() - 1);

							}
						} else {
							nbt.setInteger("ticks", 1);
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand) {

		if (!worldIn.isRemote) {
			try {

				IWorldData data = Load.World(worldIn);
				if (data != null) {
					if (data.isMapWorld()) {

						if (!player.getHeldItem(hand).hasTagCompound()) {
							player.getHeldItem(hand).setTagCompound(new NBTTagCompound());
						}

						player.getHeldItem(hand).getTagCompound().setBoolean("porting", true);
						player.getHeldItem(hand).getTagCompound().setLong("pos", player.getPosition().toLong());

						player.sendMessage(new TextComponentString("The teleport has begun, please stay put."));

						return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));

					} else {
						player.sendMessage(new TextComponentString(
								"You are not inside a map world, if this is false, contact the developer please."));

					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemMapBackPortal());
	}

	@SubscribeEvent
	public static void onModelRegistry(ModelRegistryEvent event) {
		RegisterUtils.registerRender(ITEM);
	}

}
