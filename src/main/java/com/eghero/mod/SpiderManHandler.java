package com.eghero.mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class SpiderManHandler {

    public static void serverTick(PlayerEntity player) {
        if (player.world.isRemote) return;

        // Eğer oyuncu Spider-Man kostümü giyiyorsa veya gücünü aktifleştirmişse
        boolean isSpiderMan = player.getPersistentData().getBoolean("IsSpiderMan");
        if (!isSpiderMan) return;

        // 1. DUVARA TIRMANMA MEKANİĞİ
        // Oyuncu bir duvara doğru hareket edip zıplamaya çalışıyorsa yukarı tırmanır
        if (player.collidedHorizontally) {
            Vector3d motion = player.getMotion();
            player.setMotion(motion.x, 0.2D, motion.z); // Yumuşak bir tırmanış hızı
            player.fallDistance = 0.0F; // Düşme hasarını sıfırlar
        }

        // 2. ÖRÜMCEK HİSSİ (Pasif Hız ve Refleks)
        player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 40, 1, true, false)); // Yüksek zıplama
        player.addPotionEffect(new EffectInstance(Effects.SPEED, 40, 0, true, false));       // Çeviklik
    }
}