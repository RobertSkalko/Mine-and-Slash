package com.robertx22.mine_and_slash.mmorpg.registers.client;

import com.robertx22.mine_and_slash.database.spells.items.InGame3DWhile2DInvModel;
import com.robertx22.mine_and_slash.database.spells.spell_classes.bases.BaseSpell;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;
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

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OnModelBake {

    @SubscribeEvent
    public static void bake(ModelBakeEvent event) {

        if (true) {
            return;
        }

        List<ResourceLocation> locations = new ArrayList<>();

        List<String> spellIds = new ArrayList<>();

        for (BaseSpell baseSpell : SlashRegistry.Spells().getList()) {

            try {
                String path = baseSpell.getSpellItem().getRegistryName().getPath();
                spellIds.add(path);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        for (ResourceLocation loc : event.getModelRegistry().keySet()) {

            try {
                String key = loc.getPath();

                if (spellIds.contains(key)) {
                    BaseSpell spell = getSpellByItemId(loc);

                    if (spell != null && spell.use3dBookModel()) {
                        locations.add(loc);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

    public static BaseSpell getSpellByItemId(ResourceLocation loc) {

        for (BaseSpell spell : SlashRegistry.Spells().getList()) {
            try {
                if (spell.getSpellItem().getRegistryName() != null && loc != null) {
                    if (spell.getSpellItem().getRegistryName().equals(loc)) {
                        return spell;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;

    }

}


