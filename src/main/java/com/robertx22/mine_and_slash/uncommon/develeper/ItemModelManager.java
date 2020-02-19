package com.robertx22.mine_and_slash.uncommon.develeper;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;

import static net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;

public class ItemModelManager extends ItemModelProvider {

    public ItemModelManager(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Ref.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ItemModelBuilder test = generated(Items.BLAZE_POWDER);

        this.generatedModels.put(Items.BLAZE_POWDER.getRegistryName(), test);

    }

    @Override
    public String getName() {
        return "Mine and Slash Item Models";
    }

    public String modid(Item item) {
        return item
            .asItem()
            .getRegistryName()
            .getNamespace();
    }

    public String name(Item item) {
        return item
            .asItem()
            .getRegistryName()
            .getPath();
    }

    public ItemModelBuilder generated(Item item) {
        return generated(item, itemTexture(item));
    }

    public ItemModelBuilder generated(Item item, ResourceLocation... layers) {
        ItemModelBuilder ret = getBuilder(name(item)).parent(new UncheckedModelFile("item/generated"));
        for (int i = 0; i < layers.length; i++) {
            ret = ret.texture("layer" + i, layers[i]);
        }
        return ret;
    }

    public ResourceLocation itemTexture(Item item) {
        return modLoc("items/" + name(item));
    }

    public ItemModelBuilder handheld(Item item) {
        return handheld(item, itemTexture(item));
    }

    public ItemModelBuilder handheld(Item item, ResourceLocation texture) {
        return withExistingParent(name(item), "item/handheld").texture("layer0", texture);
    }
}
