package com.robertx22.mine_and_slash.db_lists.registry;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.RandomUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SlashRegistryContainer<C extends ISlashRegistryEntry> {

    public List<String> registersErrorsAlertedFor = new ArrayList<>();
    public List<String> accessorErrosAletedFor = new ArrayList<>();

    public static void logRegistryError(String text) {
        System.out.println("[Mine and Slash Registry Error]: " + text);
    }

    private SlashRegistryType type;
    private C empty;
    private HashMap<String, C> map = new HashMap<>();
    private boolean errorIfEmpty = true;

    public SlashRegistryContainer dontErrorIfEmpty() {
        this.errorIfEmpty = false;
        return this;
    }

    public int getSize() {
        return map.size();
    }

    public SlashRegistryContainer(SlashRegistryType type, C empty) {
        this.type = type;
        this.empty = empty;
    }

    private void emptyRegistryLog() {
        if (errorIfEmpty) {
            if (map.isEmpty()) {
                logRegistryError("Slash Registry of type: " + this.type.toString() + " is empty, this is really bad!");
            }
        }
    }

    public HashMap<String, C> getAll() {
        emptyRegistryLog();

        return map;
    }

    public List<C> getList() {
        return new ArrayList<C>(map.values());
    }

    public C get(String guid) {

        emptyRegistryLog();

        if (guid.isEmpty()) {
            return empty;
        }
        if (map.containsKey(guid)) {
            return map.get(guid);
        } else {
            if (accessorErrosAletedFor.contains(guid) == false) {
                logRegistryError("GUID Error: " + guid + " of type: " + type.toString() + " doesn't exist. This is either a removed/renamed old registry, or robertx22 forgot to include it in an update.");
                accessorErrosAletedFor.add(guid);
            }

            return empty;
        }
    }

    public FilterListWrap<C> getWrapped() {
        return new FilterListWrap<C>(this.map.values());
    }

    // just do predicate.and() .or() etc. if need multiple
    public List<C> getFiltered(Predicate<C> predicate) {
        return this.getList().stream().filter(predicate).collect(Collectors.toList());
    }

    public C random() {
        return RandomUtils.weightedRandom(this.getList());
    }

    public boolean isRegistered(C c) {
        return map.containsKey(c.GUID());
    }

    public boolean isRegistered(String guid) {
        return map.containsKey(guid);
    }

    public void register(C c) {

        if (isRegistered(c)) {
            if (registersErrorsAlertedFor.contains(c.GUID()) == false) {
                logRegistryError("Key: " + c.GUID() + " has already been registered to: " + c
                        .getSlashRegistryType()
                        .toString() + " registry.");
                registersErrorsAlertedFor.add(c.GUID());
            }

        } else {
            map.put(c.GUID(), c);
        }

    }
}
