package com.rabbit.gui.component.display.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class DisplayEntity extends EntityCreature {

	// this entity is only for rendering

	private ResourceLocation texture = new ResourceLocation("textures/entity/steve.png");
	private int textureHeight = 64;

	public DisplayEntity(World worldIn) {
		super(worldIn);
		setSize(1, 2);
		tasks.addTask(1, new EntityAILookIdle(this));
	}

	@Override
	public String getName() {
		return "DisplayEntity";
	}

	public ResourceLocation getTexture() {
		return texture;
	}

	public int getTextureHeight() {
		return textureHeight;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound) {
		super.readEntityFromNBT(tagCompound);
		setTexture(new ResourceLocation(tagCompound.getString("texture")));

	}

	public void setTexture(ResourceLocation texture) {
		this.texture = texture;
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			InputStream inputstream = null;
			try {
				IResource iresource = Minecraft.getMinecraft().getResourceManager().getResource(texture);
				inputstream = iresource.getInputStream();
				BufferedImage bufferedimage = TextureUtil.readBufferedImage(inputstream);
				setTextureHeight(bufferedimage.getHeight());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (inputstream != null) {
					try {
						inputstream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	public void setTextureHeight(int textureHeight) {
		this.textureHeight = textureHeight;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound) {
		super.writeEntityToNBT(tagCompound);
		tagCompound.setString("texture", texture.toString());
	}
}
