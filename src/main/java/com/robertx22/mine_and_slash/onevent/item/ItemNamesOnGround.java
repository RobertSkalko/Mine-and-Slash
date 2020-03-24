package com.robertx22.mine_and_slash.onevent.item;

import com.robertx22.mine_and_slash.config.forge.ClientContainer;
import com.robertx22.mine_and_slash.mmorpg.Ref;
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

                if (item.getItem()
                    .getItem()
                    .getRegistryName()
                    .getNamespace()
                    .equals(Ref.MODID)) {
                    event.setResult(Event.Result.ALLOW);

                }

            }
        }
    }
}
