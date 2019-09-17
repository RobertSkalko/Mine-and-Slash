package com.robertx22.mine_and_slash.tests;

import com.robertx22.mine_and_slash.db_lists.registry.ISlashRegistryEntry;
import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

import java.util.ArrayList;
import java.util.List;

public class CreateInstances {

    // public static classname name = SlashRegistry.getRegistry(t.getregtype).get(t.guid);

    static class Type {
        List<? extends ISlashRegistryEntry> entries;
        String baseclassname;
        String registryname;

        public Type(String baseclassname, String registryname,
                    List<? extends ISlashRegistryEntry> entries) {
            this.baseclassname = baseclassname;
            this.registryname = registryname;
            this.entries = entries;

        }
    }

    private static void create(Type type) {

        List<String> list = new ArrayList<>();

        list.add("");
        list.add("");
        list.add("");

        for (ISlashRegistryEntry entry : type.entries) {
            String s = "public static ";
            s += type.baseclassname;

            s += " " + entry.GUID().replace(" ", "_").replace("'", "").toUpperCase();

            s += " = " + type.registryname;

            s += ".get(" + '"' + entry.GUID() + '"' + ");";

            list.add(s);
        }

        list.add("");
        list.add("");
        list.add("");

        String all = String.join("\n", list);

        System.out.println(all);

    }

    public static void createall() {

        create(new Type("IUnique", "SlashRegistry.UniqueGears()", SlashRegistry.UniqueGears()
                .getList()));
        create(new Type("Stat", "SlashRegistry.Stats()", SlashRegistry.Stats()
                .getList()));

    }

}
