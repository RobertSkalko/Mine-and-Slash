package com.robertx22.mine_and_slash.saveclasses.unit;

import info.loenwind.autosave.Registry;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import info.loenwind.autosave.exceptions.NoHandlerFoundException;
import info.loenwind.autosave.handlers.IHandler;
import info.loenwind.autosave.util.NBTAction;
import info.loenwind.autosave.util.NullHelper;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Storable(handler = StatContainer.class)
public class StatContainer implements IHandler<StatContainer> {

    @Store
    public HashMap<String, StatData> stats = new HashMap<>();

    @Override
    public boolean store(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name, StatContainer object) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
        CompoundNBT tag = new CompoundNBT();

        if (object.stats != null) {
            List<StatData> list = new ArrayList<>(object.stats.values());

            int size = list.size();
            tag.putInt("size", size);

            for (int i = 0; i < size; i++) {
                tag.putString(i + "", list.get(i)
                    .toSerializationString());
            }
        }

        nbt.put(name, tag);
        return true;
    }

    @Nullable
    @Override
    public StatContainer read(Registry registry, Set<NBTAction> phase, CompoundNBT nbt, Type type, String name, @Nullable StatContainer object) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoHandlerFoundException {
        if (nbt.contains(name)) {
            if (object == null) {
                object = new StatContainer();
            }
            CompoundNBT tag = NullHelper.notnullM(nbt.getCompound(name), "CompoundNBT.getCompound()");

            if (tag.contains("size")) {
                int size = tag.getInt("size");

                object.stats = new HashMap<>();

                for (int i = 0; i < size; i++) {

                    String ser = tag.getString(i + "");
                    StatData data = StatData.fromSerializationString(ser);
                    object.stats.put(data.getId(), data);
                }
            }
        }
        return object;
    }
}
