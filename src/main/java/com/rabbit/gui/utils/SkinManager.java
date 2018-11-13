package com.rabbit.gui.utils;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.rabbit.gui.RabbitGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SkinManager {
	// key is minecraft username and value is the skin texture
	private static Map<String, UUID> playerSkin = new HashMap<>();
	private static Map<String, Integer> textureSize = new HashMap<>();

	public static void addSkin(EntityPlayer player, String skin) {
		SkinManager.addSkin(player.getName(), skin);
	}

	public static void addSkin(String player, ResourceLocation skin) {
		if (skin != null) {
			UUID textureId = UUID.randomUUID();
			// incase its a url the texture helper will sort it all out
			TextureHelper.addStaticTexture(textureId, skin);
			SkinManager.playerSkin.put(player, textureId);
		}
	}

	public static void addSkin(String player, String skin) {
		if ((skin != null) && !skin.isEmpty()) {
			UUID textureId = UUID.randomUUID();
			// incase its a url the texture helper will sort it all out
			TextureHelper.addTexture(textureId, skin);
			SkinManager.playerSkin.put(player, textureId);
		}
	}

	public static boolean bindSkinTexture(EntityPlayer player) {
		return SkinManager.bindSkinTexture(player.getName());
	}

	public static boolean bindSkinTexture(String player) {
		if (SkinManager.playerSkin.containsKey(player)) {
			TextureHelper.bindTexture(SkinManager.playerSkin.get(player));
			return true;
		}
		return false;
	}

	public static String getSkinTexture(EntityPlayer player) {
		return SkinManager.getSkinTexture(player.getName());
	}

	public static String getSkinTexture(String player) {
		if (SkinManager.playerSkin.containsKey(player)) {
			// not sure we can do anything about dynamic textures currently
			if (TextureHelper.getDynamicTexture(SkinManager.playerSkin.get(player)) == null) {
				ResourceLocation tex = TextureHelper.getStaticTexture(SkinManager.playerSkin.get(player));
				if (tex != null) {
					return tex.toString();
				}
			}
		}
		return null;
	}

	public static int getSkinTextureHeight(EntityPlayer player) {
		return SkinManager.getSkinTextureHeight(player.getName());
	}

	public static int getSkinTextureHeight(String player) {
		if (SkinManager.textureSize.containsKey(player)) {
			return SkinManager.textureSize.get(player);
		} else {
			return 64;
		}
	}

	public static boolean hasSkinTexture(EntityPlayer player) {
		return SkinManager.hasSkinTexture(player.getName());
	}

	public static boolean hasSkinTexture(String player) {
		return SkinManager.playerSkin.containsKey(player);
	}

	public static void removeSkinTexture(EntityPlayer player) {
		SkinManager.removeSkinTexture(player.getName());
	}

	public static void removeSkinTexture(String player) {
		SkinManager.playerSkin.remove(player);
		SkinManager.textureSize.remove(player);
	}

	public static void setSkinTexture(EntityPlayer player, ResourceLocation skin) {
		SkinManager.setSkinTexture(player.getName(), skin);
	}

	public static void setSkinTexture(EntityPlayer player, String skin) {
		SkinManager.setSkinTexture(player.getName(), skin);
	}

	public static void setSkinTexture(String player, ResourceLocation skin) {
		if (skin != null) {
			if (SkinManager.playerSkin.containsKey(player)) {
				UUID textureId = UUID.randomUUID();
				// incase its a url the texture helper will sort it all out
				TextureHelper.addStaticTexture(textureId, skin);
				SkinManager.playerSkin.replace(player, textureId);
			} else {
				SkinManager.addSkin(player, skin);
			}
			SkinManager.setTextureDimension(player, skin);
		}
	}

	public static void setSkinTexture(String player, String skin) {
		if ((skin != null) && !skin.isEmpty()) {
			if (SkinManager.playerSkin.containsKey(player)) {
				UUID textureId = UUID.randomUUID();
				// incase its a url the texture helper will sort it all out
				TextureHelper.addTexture(textureId, skin);
				SkinManager.playerSkin.replace(player, textureId);
			} else {
				SkinManager.addSkin(player, skin);
			}
			SkinManager.setTextureDimension(player, skin);
		}
	}

	private static void setTextureDimension(String player, ResourceLocation skin) {
		InputStream inputstream = null;
		try {
			IResource iresource = Minecraft.getMinecraft().getResourceManager().getResource(skin);
			inputstream = iresource.getInputStream();
			BufferedImage bufferedimage = TextureUtil.readBufferedImage(inputstream);

			if (SkinManager.textureSize.containsKey(player)) {
				SkinManager.textureSize.replace(player, bufferedimage.getHeight());
			} else {
				SkinManager.textureSize.put(player, bufferedimage.getHeight());
			}
		} catch (FileNotFoundException fnfe) {
			RabbitGui.logger.error("Could not determine texture size", fnfe);
		} catch (IOException e) {
			RabbitGui.logger.error("Could not determine texture size", e);
		} finally {
			if (inputstream != null) {
				try {
					inputstream.close();
				} catch (IOException e) {
					RabbitGui.logger.error("Failed during player render, could not close inputstream", e);
				}
			}
		}
	}

	private static void setTextureDimension(String player, String skin) {
		try {
			BufferedImage bufferedimage = ImageCacheHelper.fetchImage(new URL(skin));
			if (bufferedimage != null) {
				if (SkinManager.textureSize.containsKey(player)) {
					SkinManager.textureSize.replace(player, bufferedimage.getHeight());
				} else {
					SkinManager.textureSize.put(player, bufferedimage.getHeight());
				}
			}
		} catch (MalformedURLException e) {
			RabbitGui.logger.error(e.getLocalizedMessage()
					+ "\nCould not determine texture size from url, attempting resource location method");
			SkinManager.setTextureDimension(player, new ResourceLocation(skin));
		}
	}
}
