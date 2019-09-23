package com.robertx22.mine_and_slash.network;

import com.robertx22.mine_and_slash.database.gearitemslots.weapons.Wand;
import com.robertx22.mine_and_slash.database.spells.entities.bases.EntityWandProjectile;
import com.robertx22.mine_and_slash.saveclasses.item_classes.GearItemData;
import com.robertx22.mine_and_slash.uncommon.capability.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.WeaponSpeedCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Gear;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class WandAttackPacket {

    public BlockPos pos;

    public WandAttackPacket() {

    }

    public WandAttackPacket(PlayerEntity player) {
        this.pos = player.getPosition();

    }

    public static WandAttackPacket decode(PacketBuffer buf) {

        WandAttackPacket newpkt = new WandAttackPacket();

        newpkt.pos = buf.readBlockPos();

        return newpkt;

    }

    public static void encode(WandAttackPacket packet, PacketBuffer tag) {
        tag.writeBlockPos(packet.pos);
    }

    // static int RANGE = 7;

    public static void handle(final WandAttackPacket pkt,
                              Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {
            try {
                ServerPlayerEntity e = ctx.get().getSender();
                if (e != null) {

                    WeaponSpeedCap.IWeaponSpeedCap speed = Load.weaponSpeed(e);

                    if (speed.canAttack()) {

                        speed.onAttack(e);

                        GearItemData gear = Gear.Load(e.getHeldItemMainhand());

                        if (gear == null || !(gear.GetBaseGearType() instanceof Wand)) {
                            return;

                        }

                        EntityCap.UnitData data = Load.Unit(e);

                        if (data.tryUseWeapon(gear, e)) {

                            float velocity = 1.5F;

                            EntityWandProjectile projectile = new EntityWandProjectile(e.world);
                            projectile.SetReady(e.getHeldItemMainhand());
                            projectile.SpawnAndShoot(e, velocity);

                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.get().setPacketHandled(true);

    }

}