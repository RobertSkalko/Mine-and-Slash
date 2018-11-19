package com.libraries.rabbit.gui.utils;

import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class AssetsBrowser {
	public static String getRoot(String asset) {
		String mod = "minecraft";
		int index = asset.indexOf(":");
		if (index > 0) {
			mod = asset.substring(0, index);
			asset = asset.substring(index + 1);
		}
		if (asset.startsWith("/")) {
			asset = asset.substring(1);
		}
		String location = "/" + mod + "/" + asset;
		index = location.lastIndexOf("/");
		if (index > 0) {
			location = location.substring(0, index);
		}
		return location;
	}

	public boolean isRoot;
	private int depth;
	private String folder;
	public HashSet<String> folders;
	public HashSet<String> files;

	private String[] extensions;

	public AssetsBrowser(String folder, String[] extensions) {
		folders = new HashSet<>();
		files = new HashSet<>();
		this.extensions = extensions;
		setFolder(folder);
	}

	public AssetsBrowser(String[] extensions) {
		folders = new HashSet<>();
		files = new HashSet<>();
		this.extensions = extensions;
	}

	private void checkFile(String name) {
		if (!name.startsWith("/")) {
			name = "/" + name;
		}
		if (!name.startsWith(folder)) {
			return;
		}
		String[] split = name.split("/");
		int count = split.length;
		if (count == (depth + 1)) {
			if (validExtension(name)) {
				files.add(split[depth]);
			}
		} else if ((depth + 1) < count) {
			folders.add(split[depth]);
		}
	}

	private void checkFolder(File file, int length) {
		File[] files = file.listFiles();
		if (files == null) {
			return;
		}
		for (File f : files) {
			String name = f.getAbsolutePath().substring(length);
			name = name.replace("\\", "/");
			if (!name.startsWith("/")) {
				name = "/" + name;
			}
			if (f.isDirectory() && (folder.startsWith(name) || name.startsWith(folder))) {
				checkFile(name + "/");
				checkFolder(f, length);
			} else {
				checkFile(name);
			}
		}
	}

	private void decompressFile(File file) {
		try {
			if (!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
				ZipFile zip = new ZipFile(file);
				Enumeration<? extends ZipEntry> entries = zip.entries();
				while (entries.hasMoreElements()) {
					ZipEntry zipentry = entries.nextElement();
					String entryName = zipentry.getName();
					checkFile(entryName);
				}
				zip.close();
			} else if (file.isDirectory()) {
				int length = file.getAbsolutePath().length();
				checkFolder(file, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAsset(String asset) {
		String[] split = folder.split("/");
		if (split.length < 3) {
			return null;
		}
		String texture = split[2] + ":";
		texture = texture + folder.substring(texture.length() + 8) + asset;
		return texture;
	}

	private void getFiles() {
		folders.clear();
		files.clear();
		Minecraft.getMinecraft().getResourcePackRepository();
		SimpleReloadableResourceManager simplemanager = (SimpleReloadableResourceManager) Minecraft.getMinecraft()
				.getResourceManager();
		Map<String, IResourceManager> map = (Map<String, IResourceManager>) ObfuscationReflectionHelper
				.getPrivateValue((Class) SimpleReloadableResourceManager.class, simplemanager, 2);
		HashSet<String> set = new HashSet<>();
		for (String name : map.keySet()) {
			if (!(map.get(name) instanceof FallbackResourceManager)) {
				continue;
			}
			FallbackResourceManager manager = (FallbackResourceManager) map.get(name);
			List<IResourcePack> list = (List<IResourcePack>) ObfuscationReflectionHelper
					.getPrivateValue((Class) FallbackResourceManager.class, manager, 1);
			for (IResourcePack pack : list) {
				if (pack instanceof AbstractResourcePack) {
					AbstractResourcePack p = (AbstractResourcePack) pack;
					File file = new File((String) p.getResourceDomains().toArray()[0], p.getPackName());
					if (file == null) {
						continue;
					}
					set.add(file.getAbsolutePath());
				}
			}
		}
		for (String file2 : set) {
			File pFile = new File(file2);
			if (pFile.exists()) {
				decompressFile(pFile);
			}
		}
		for (ModContainer mod : Loader.instance().getModList()) {
			if (mod.getSource().exists()) {
				decompressFile(mod.getSource());
			}
		}
	}

	public void setFolder(String folder) {
		if (!folder.endsWith("/")) {
			folder += "/";
		}
		isRoot = (folder.length() <= 1);
		this.folder = "/assets" + folder;
		depth = StringUtils.countMatches(this.folder, "/");
		getFiles();
	}

	private boolean validExtension(String entryName) {
		int index = entryName.indexOf(".");
		if (index < 0) {
			return false;
		}
		String extension = entryName.substring(index + 1);
		for (String ex : extensions) {
			if (ex.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		return false;
	}
}
