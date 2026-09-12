package com.eghero.mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;

public class SupermanFlightHandler {

    // Uçuşun aktif olup olmadığını takip eden bayrak
    private static boolean isFlyingActive = false;
    private static int jumpPressCooldown = 0;

    // Oyuncu zıplama tuşuna her bastığında bu fonksiyon tetiklenir
    public static void onPlayerJump(PlayerEntity player) {
        // Önce oyuncunun üzerinde Superman DNA'sı var mı diye kontrol ediyoruz
        if (!SupermanDNAItem.hasSupermanDNAEquipped(player)) {
            return; // Eğer DNA yoksa uçamaz!
        }

        // Yerde değilken (havadayken) çift zıplama algılaması
        if (!player.isOnGround()) {
            if (jumpPressCooldown > 0 && jumpPressCooldown < 15) {
                // Çift zıplama yakalandı! Uçuşu aç/kapat yapıyoruz
                toggleFlight(player);
            }
        }
        jumpPressCooldown = 15; // Tuş basma aralığı süresi
    }

    // Uçuş modunu değiştiren ana mantık
    public static void toggleFlight(PlayerEntity player) {
        isFlyingActive = !isFlyingActive;
        
        if (isFlyingActive) {
            player.abilities.allowFlying = true;
            player.abilities.isFlying = true;
            player.sendPlayerAbilities();
            player.sendMessage(new StringTextComponent("§b[Superman] §aUçuş Modu Aktif! Gökyüzündesin."), player.getUniqueID());
        } else {
            player.abilities.isFlying = false;
            player.abilities.allowFlying = false;
            player.sendPlayerAbilities();
            player.sendMessage(new StringTextComponent("§b[Superman] §cUçuş Modu Kapatıldı."), player.getUniqueID());
        }
    }

    // Her oyun tikinde (tick) uçuş durumunu ve fiziklerini güncelleyen döngü
    public static void serverTick(PlayerEntity player) {
        if (jumpPressCooldown > 0) {
            jumpPressCooldown--;
        }

        // Eğer oyuncu uçuyorsa ama üzerinden Superman DNA'sını çıkardıysa uçuşu zorla kapat
        if (isFlyingActive && !SupermanDNAItem.hasSupermanDNAEquipped(player)) {
            isFlyingActive = false;
            player.abilities.isFlying = false;
            player.abilities.allowFlying = false;
            player.sendPlayerAbilities();
            player.sendMessage(new StringTextComponent("§c[Superman] Kostüm çıkarıldı! Uçuş iptal edildi."), player.getUniqueID());
            return;
        }

        // Uçuş aktifse düşme hasarını sıfırla ve akıcı süzülme sağla
        if (isFlyingActive && player.abilities.isFlying) {
            player.fallDistance = 0.0F;
            
            // Bakılan yöne doğru hafif bir hız kazandırarak akıcı uçuş sağla
            Vector3d look = player.getLookVec();
            if (player.isSprinting()) {
                player.setMotion(look.x * 1.2D, look.y * 1.2D, look.z * 1.2D);
            }
        }
    }
}