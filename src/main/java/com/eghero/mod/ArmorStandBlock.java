package com.eghero.mod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;

public class ArmorStandBlock extends Block {

    public ArmorStandBlock() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(4.0F, 10.0F)
                .setLightLevel(state -> 12)); // Stark atölyesi aydınlatması
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.isRemote) return ActionResultType.SUCCESS;

        ItemStack heldItem = player.getHeldItem(hand);
        boolean isSneaking = player.isSneaking(); // Oyuncu eğiliyor mu?

        // ÖRNEK MANTIK SİMÜLASYONU (TileEntity ile tam entegre edilecektir)
        // Gerçek dünyada bu veriler TileEntity'de tutulur, şimdilik akış mantığını kuruyoruz:

        if (isSneaking) {
            // EĞİLEREK SAĞ TIKLAMA: Stanttan zırhı alma
            player.sendMessage(new StringTextComponent("§b[Stark Atölyesi] §eStanttan zırh envanterine geri alındı!"), player.getUniqueID());
            // TODO: TileEntity içindeki zırhı oyuncunun envanterine ver / stantı boşalt
            
        } else {
            // NORMAL SAĞ TIKLAMA: Stanta zırh yerleştirme
            if (heldItem.getItem() instanceof ArmorItem) {
                player.sendMessage(new StringTextComponent("§b[Stark Atölyesi] §aZırh stanta başarıyla yerleştirildi!"), player.getUniqueID());
                // TODO: Oyuncunun elindeki zırhı TileEntity'ye kaydet ve elinden düşür
                heldItem.shrink(1);
            } else {
                player.sendMessage(new StringTextComponent("§c[Hata] Stanta yalnızca süper kahraman zırhları yerleştirebilirsin! (Eline bir zırh al)"), player.getUniqueID());
            }
        }

        return ActionResultType.CONSUME;
    }
}