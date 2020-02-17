package com.robertx22.mine_and_slash.saveclasses;

import com.robertx22.mine_and_slash.database.map_events.base.MapEvent;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import info.loenwind.autosave.annotations.Factory;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Storable
public class MapEventsData {

    @Factory
    public MapEventsData() {

    }

    @Store
    private List<Data> events = new ArrayList<>();

    public boolean isActive(MapEvent event) {
        return events.stream().anyMatch(x -> x.event.equals(event.GUID()));
    }

    public void add(MapEvent event, World world) {

        event.onActivate(world);

        Data data = new Data();
        data.event = event.GUID();
        data.minRem = event.minutesEventLasts();
        events.add(data);
    }

    public List<MapEvent> getEvents() {
        return events.stream().map(x -> SlashRegistry.MapEvents().get(x.event)).collect(Collectors.toList());
    }

    public void onMinute(World world) {
        events.forEach(x -> {
            SlashRegistry.MapEvents().get(x.event).onMinutePassed(world, x);
            x.minRem--;
        });

        events.removeIf(x -> x.minRem < 1);
    }

    @Storable
    public class Data {

        public String event = "";
        public int minRem = 10;

    }

}
