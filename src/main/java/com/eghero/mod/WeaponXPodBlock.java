package com.eghero.mod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;

public class WeaponXPodBlock extends Block {

    public WeaponXPodBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(5.0F, 10.0F)
                .setLightLevel(state -> 10)); // Hafif teknolojik bir mavi/beyaz ışık
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.isRemote) return ActionResultType.SUCCESS;

        ItemStack heldItem = player.getHeldItem(hand);

        // Kapsülün içine Adamantium veya Su Şişesi ekleme mantığı
        // Oyuncu elinde Adamantium ile sağ tıklarsa süreci başlatır
        if (heldItem.getItem() instanceof AdamantiumIngotItem) {
            
            // Envanterinde 4 adet su şişesi var mı kontrol ediyoruz
            boolean hasWaterBottles = hasEnoughWaterBottles(player);

            if (hasWaterBottles) {
                // Malzemeleri tüketiyoruz
                heldItem.shrink(1);
                consumeWaterBottles(player);

                // Weapon X Deneyi Başlıyor! İskelet Adamantium ile kaplanıyor
                player.getPersistentData().putBoolean("HasAdamantiumSkeleton", true);
                
                player.sendMessage(new StringTextComponent("§b[WEAPON X] §aKapsül mühürlendi! Sıvı enjekte ediliyor... İskeletin Adamantium ile kaplandı!"), player.getUniqueID());
                player.playSound(net.minecraft.util.SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.0F, 0.5F);

            } else {
                player.sendMessage(new StringTextComponent("§c[Hata] Kapsülü çalıştırmak için içeride 4 adet Su Şişesi bulunmalı!"), player.getUniqueID());
            }
            return ActionResultType.CONSUME;
        } else {
            player.sendMessage(new StringTextComponent("§e[Weapon X Kapsülü] §7Çalıştırmak için eline Adamantium Ingot alıp kapsüle sağ tıklamalısın (4x Su Şişesi gereklidir)."), player.getUniqueID());
        }

        return ActionResultType.SUCCESS;
    }

    // Oyuncunun envanterinde en az 4 su şişesi var mı kontrolü
    private boolean hasEnoughWaterBottles(PlayerEntity player) {
        int count = 0;
        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack.getItem() == Items.POTION && stack.hasTag() && stack.getTag().getString("Potion").contains("water")) {
                count += stack.getCount();
            }
        }
        return count >= 4;
    }

    // Envanterden 4 su şişesini silme/tüketme fonksiyonu
    private void consumeWaterBottles(PlayerEntity player) {
        int remainingToConsume = 4;
        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack.getItem() == Items.POTION && stack.hasTag() && stack.getTag().getString("Potion").contains("water")) {
                int count = stack.getCount();
                if (count >= remainingToConsume) {
                    stack.shrink(remainingToConsume);
                    break;
                } else {
                    remainingToConsume -= count;
                    stack.setCount(0);
                }
            }
        }
    }
}
// Kapsül kodunun içine eklenecek mantık:
boolean hasAdamantium = player.getPersistentData().getBoolean("HasAdamantiumSkeleton");

if (heldItem.getItem() instanceof AdamantiumIngotItem) {
    boolean hasWaterBottles = hasEnoughWaterBottles(player);

    if (hasWaterBottles) {
        heldItem.shrink(1);
        consumeWaterBottles(player);

        if (!hasAdamantium) {
            // İlk kez Adamantium takılıyor
            player.getPersistentData().putBoolean("HasAdamantiumSkeleton", true);
            player.sendMessage(new StringTextComponent("§b[WEAPON X] §aKapsül mühürlendi! İskeletin Adamantium ile kaplandı!"), player.getUniqueID());
        } else {
            // Bakımı yenileniyor (Banyo yapılıyor)
            WolverineMaintenanceHandler.replenishAdamantium(player);
        }

        player.playSound(net.minecraft.util.SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.0F, 0.5F);
    } else {
        player.sendMessage(new StringTextComponent("§c[Hata] Kapsülü çalıştırmak için 4 adet Su Şişesi gerekli!"), player.getUniqueID());
    }
}