package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.localization.CLOC;
import net.minecraft.entity.item.ItemEntity;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemNamesOnGround {

    @SubscribeEvent
    public static void onJoin(RenderNameplateEvent event) {

        if (ClientContainer.INSTANCE.RENDER_ITEM_NAMES_ON_GROUND.get()) {
            if (event.getEntity() instanceof ItemEntity) {

                ItemEntity item = (ItemEntity) event.getEntity();

                boolean render = false;

                if (item.getItem()
                    .getItem()
                    .getRegistryName()
                    .getNamespace()
                    .equals(Ref.MODID)) {

                    render = true;
                }

                GearItemData gear = Gear.Load(item.getItem());

                if (gear != null) {
                    render = true;
                    event.setContent(CLOC.translate(gear.GetDisplayName(item.getItem())));
                }

                if (render) {
                    event.setResult(Event.Result.ALLOW);
                }
            }
        }
    }
}
