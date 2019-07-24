package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.items.spells.InGame3DWhile2DInvModel;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.SimpleBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OnModelBake {

    @SubscribeEvent
    public static void bake(ModelBakeEvent event) {

        List<ResourceLocation> locations = event.getModelRegistry()
                .keySet()
                .stream()
                .filter(x -> x.getNamespace().equals(Ref.MODID) && x.getPath()
                        .contains("spell"))
                .collect(Collectors.toList());

        List<IBakedModel> models = new ArrayList<>();
        locations.stream().forEach(x -> models.add(event.getModelRegistry().get(x)));

        for (ResourceLocation loc : locations) {

            if (event.getModelRegistry().get(loc) instanceof SimpleBakedModel) {
                SimpleBakedModel model = (SimpleBakedModel) event.getModelRegistry()
                        .get(loc);
                InGame3DWhile2DInvModel mymodel = new InGame3DWhile2DInvModel(model);
                event.getModelRegistry().put(loc, mymodel);
            }
        }

    }

}


