package com.eghero.mod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class KryptoniteClusterBlock extends Block {

    public KryptoniteClusterBlock() {
        super(Properties.create(Material.ROCK)
                .sound(SoundType.GLASS)
                .hardnessAndResistance(1.5F, 6.0F)
                .setLightLevel(state -> 7)); // Hafif yeşilimsi radyoaktif bir parıltı
    }

    // Blok çevresindeki alanı tarayarak Superman'i etkisiz hale getiren ana fonksiyon
    public static void applyKryptoniteEffect(World world, BlockPos pos) {
        if (world.isRemote) return;

        // 10 blok yarıçapında bir alan (Bounding Box) oluşturuyoruz
        AxisAlignedBB area = new AxisAlignedBB(pos).grow(10.0D);
        List<PlayerEntity> players = world.getEntitiesWithinAABB(PlayerEntity.class, area);

        for (PlayerEntity player : players) {
            // Eğer oyuncu Superman DNA'sına sahipse radyasyon başlar
            if (hasSupermanDNA(player)) {
                // Zehir, Zayıflık ve Yavaşlık uygulayarak tüm süper güçlerini etkisiz kılar
                player.addPotionEffect(new EffectInstance(Effects.POISON, 100, 1, true, false));
                player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 2, true, false));
                player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 1, true, false));
                
                player.sendMessage(new StringTextComponent("§2[KRİPTONİT] §cRadyasyon yayılıyor... Güçlerin sönüyor!"), player.getUniqueID());
            }
        }
    }

    // Oyuncunun Superman DNA'sına sahip olup olmadığını kontrol eden mantık
    private static boolean hasSupermanDNA(PlayerEntity player) {
        // İleride Superman kostümü kontrolü buraya bağlanacak
        return true; 
    }
}