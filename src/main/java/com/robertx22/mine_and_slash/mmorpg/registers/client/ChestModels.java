package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.new_content.chests.MapChestTile.ChestTypes;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChestModels {

    // TODO THIS COULD BE OBJECTIFIED

    public static final ResourceLocation chestAtlas = new ResourceLocation("textures/atlas/chest.png");

    public static final Material NORMAL_CHEST_LOCATION = getChestMaterial("normal");
    public static final Material TRAPPPED_CHEST_LOCATION = getChestMaterial("trapped");

    public static void addTextures(MessagePassingQueue.Consumer<Material> consumer) {
        consumer.accept(NORMAL_CHEST_LOCATION);
    }

    private static Material getChestMaterial(String name) {
        return new Material(Atlases.CHEST_ATLAS, new ResourceLocation(Ref.MODID, "entity/chest/" + name));
    }

    public static Material chooseChestModel(TileEntity tileEntity, ChestTypes type) {
        switch (type) {
            case NORMAL:
                return NORMAL_CHEST_LOCATION;
            case TRAPPED:
                return TRAPPPED_CHEST_LOCATION;
            default:
                return Atlases.CHEST_MATERIAL;
        }
    }

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap()
            .getTextureLocation()
            .equals(Atlases.CHEST_ATLAS)) {
            return;
        }

        event.addSprite(NORMAL_CHEST_LOCATION.getTextureLocation());
        event.addSprite(TRAPPPED_CHEST_LOCATION.getTextureLocation());

    }
}