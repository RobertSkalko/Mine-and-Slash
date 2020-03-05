package com.robertx22.mine_and_slash.packets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializable;
import com.robertx22.mine_and_slash.onevent.data_gen.ISerializedRegistryEntry;
import com.robertx22.mine_and_slash.registry.*;
import com.robertx22.mine_and_slash.saveclasses.ListStringData;
import com.robertx22.mine_and_slash.uncommon.datasaving.base.LoadSave;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RegistryPacket {

    static final JsonParser PARSER = new JsonParser();

    SlashRegistryType type;
    ListStringData data;

    private RegistryPacket() {

    }

    public <T extends ISerializedRegistryEntry> RegistryPacket(SlashRegistryType type, List<T> items) {

        SlashRegistryContainer reg = SlashRegistry.getRegistry(type);

        if (reg.isEmpty()) {
            SlashRegistry.restoreBackup();
        }

        // TODO CACHE THIS
        List<String> list = (List<String>) items
            .stream()
            .map(x -> ((ISerializable) x).toJson()
                .toString())
            .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new RuntimeException("Registry is empty on the server when trying to send registry packet!");
        }

        this.data = new ListStringData(list);

        this.type = type;

    }

    public static RegistryPacket decode(PacketBuffer buf) {

        try {
            RegistryPacket newpkt = new RegistryPacket();
            newpkt.type = SlashRegistryType.valueOf(buf.readString(30));

            CompoundNBT nbt = buf.readCompoundTag();

            newpkt.data = LoadSave.Load(ListStringData.class, new ListStringData(), nbt, "data");
            return newpkt;
        } catch (Exception e) {
            System.out.println("Failed reading Mine and Slash packet to bufferer.");
            e.printStackTrace();
        }
        return new RegistryPacket();

    }

    public static void encode(RegistryPacket packet, PacketBuffer tag) {
        try {
            tag.writeString(packet.type.name(), 30);
            CompoundNBT nbt = new CompoundNBT();

            LoadSave.Save(packet.data, nbt, "data");

            tag.writeCompoundTag(nbt);
        } catch (Exception e) {
            System.out.println("Failed saving " + packet.type.name() + " Mine and Slash packet to bufferer.");
            e.printStackTrace();
        }
    }

    public static void handle(final RegistryPacket pkt, Supplier<NetworkEvent.Context> ctx) {

        ctx.get()
            .enqueueWork(() -> {
                try {

                    if (pkt.data.getList()
                        .isEmpty()) {
                        throw new RuntimeException("Registry list sent from server is empty!");
                    }

                    pkt.data.getList()
                        .stream()
                        .map(x -> {
                            try {
                                JsonObject json = (JsonObject) PARSER.parse(x);
                                return pkt.type.getEmpty()
                                    .fromJson(json);
                            } catch (JsonSyntaxException e) {
                                System.out.println("Failed to parse Mine and Slash registry Json!!!");
                                e.printStackTrace();
                            }
                            return null;

                        })
                        .collect(Collectors.toList())
                        .forEach(x -> {
                            if (x instanceof ISlashRegistryEntry) {
                                SlashRegistryPackets.add((ISerializedRegistryEntry) x);
                            }
                        });

                } catch (Exception e) {

                    e.printStackTrace();
                }
            });

        ctx.get()
            .setPacketHandled(true);

    }

}