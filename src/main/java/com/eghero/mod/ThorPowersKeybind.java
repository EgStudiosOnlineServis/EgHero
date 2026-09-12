package com.eghero.mod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class ThorPowersKeybind {

    // N tuşu: Elektrik Atma
    public static final KeyBinding LIGHTNING_BOLT_KEY = new KeyBinding(
            "key.eghero.thorbolt", GLFW.GLFW_KEY_N, "key.categories.eghero"
    );

    // C tuşu: 30 Blokluk Alana Yıldırım Patlaması
    public static final KeyBinding LIGHTNING_BLAST_KEY = new KeyBinding(
            "key.eghero.thorblast", GLFW.GLFW_KEY_C, "key.categories.eghero"
    );

    // N Tuşu: Tekli elektrik/yıldırım atışı
    public static void onBoltKeyPressed(PlayerEntity player) {
        if (!MjolnirItem.isHoldingMjolnir(player)) {
            player.sendMessage(new StringTextComponent("§cMjölnir elinde olmadan bu gücü kullanamazsın!"), player.getUniqueID());
            return;
        }

        if (!player.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) player.world;
            Vector3d look = player.getLookVec();
            BlockPos targetPos = new BlockPos(player.getPosX() + look.x * 10, player.getPosY(), player.getPosZ() + look.z * 10);
            
            LightningBoltEntity bolt = net.minecraft.entity.EntityType.LIGHTNING_BOLT.create(serverWorld);
            bolt.setPosition(targetPos.getX(), targetPos.getY(), targetPos.getZ());
            serverWorld.addEntity(bolt);
            
            player.sendMessage(new StringTextComponent("§e[Mjölnir] §bYıldırım gönderildi!"), player.getUniqueID());
        }
    }

    // C Tuşu: 30 blok yakınındaki HERKESIN kafasına yıldırım yağdırma
    public static void onBlastKeyPressed(PlayerEntity player) {
        if (!MjolnirItem.isHoldingMjolnir(player)) {
            player.sendMessage(new StringTextComponent("§cMjölnir elinde olmadan bu gücü kullanamazsın!"), player.getUniqueID());
            return;
        }

        if (!player.world.isRemote) {
            ServerWorld serverWorld = (ServerWorld) player.world;
            AxisAlignedBB area = player.getBoundingBox().grow(30.0D);
            List<LivingEntity> targets = serverWorld.getEntitiesWithinAABB(LivingEntity.class, area);

            int struckCount = 0;
            for (LivingEntity target : targets) {
                if (target != player) {
                    LightningBoltEntity bolt = net.minecraft.entity.EntityType.LIGHTNING_BOLT.create(serverWorld);
                    bolt.setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
                    serverWorld.addEntity(bolt);
                    struckCount++;
                }
            }
            player.sendMessage(new StringTextComponent("§6[Mjölnir Gök Gürültüsü] §e30 blok içindeki " + struckCount + " hedef yıldırıma tutuldu!"), player.getUniqueID());
        }
    }
}