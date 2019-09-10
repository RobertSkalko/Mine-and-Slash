package com.robertx22.mine_and_slash.mmorpg;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.DirUtils;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModelCreator {

    static String DEFAULT_MODEL = "{\n" + "    \"parent\": \"item/generated\",\n" + "    \"textures\": {\n" + "        \"layer0\": \"mmorpg:REPLACE\"\n" + "    }\n" + "}";

    public static void createDefaultModelsAndDirs() {
        for (Item item : ForgeRegistries.ITEMS) {

            if (item.getRegistryName().getNamespace().equals(Ref.MODID)) {

                ResourceLocation loc = item.getRegistryName();
                String path = loc.getPath();

                String modelpath = DirUtils.modDir() + "\\main\\resources\\assets\\mmorpg\\models\\item\\" + path + ".json";
                String texturepath = DirUtils.modDir() + "\\main\\resources\\assets\\mmorpg\\textures\\items\\" + path + ".png";

                File model = new File(modelpath);
                File texture = new File(texturepath);

                if (!Files.exists(Paths.get(model.getParent()))) {
                    try {
                        Files.createDirectories(Paths.get(model.getParent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (!Files.exists(Paths.get(texture.getParent()))) {
                    try {
                        Files.createDirectories(Paths.get(texture.getParent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (!Files.exists(Paths.get(model.getPath()))) {
                    try {
                        Path created = Files.createFile(Paths.get(model.getPath()));

                        System.out.println("Creating file at: " + created.getFileName()
                                .toString());

                        FileWriter fw = new FileWriter(created.toFile());
                        fw.write(DEFAULT_MODEL.replace("REPLACE", "items/" + path));
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

}
