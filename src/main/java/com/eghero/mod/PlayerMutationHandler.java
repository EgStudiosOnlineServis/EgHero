package com.eghero.mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;

public class PlayerMutationHandler {

    // Her oyun tikinde vücudun DNA'ya uyum sağlamasını ve zıt gen çatışmalarını denetleyen ana döngü
    public static void serverTick(PlayerEntity player) {
        if (player.world.isRemote) return;

        // Oyuncunun veri etiketlerinden gen durumlarını alıyoruz
        int currentLevel = player.getPersistentData().getInt("SupermanMutationLevel");
        boolean hasSupermanDNA = player.getPersistentData().getBoolean("HasSupermanDNA");
        boolean hasOppositeDNA = player.getPersistentData().getBoolean("HasOppositeDNA"); // Örn: Zıt bir gen (örn. Kryptonit veya zıt mutant geni)

        // 1. ZIT GEN ÇATIŞMASI (VÜCUT REDDİ) KONTROLÜ
        if (hasSupermanDNA && hasOppositeDNA) {
            // İki zıt gen birbiriyle çatışıyor! Hücreler parçalanıyor.
            player.addPotionEffect(new EffectInstance(Effects.POISON, 100, 1, true, false));
            player.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 0, true, false)); // Çürüme efekti
            player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 140, 0, true, false));
            player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 2, true, false));

            player.sendMessage(new StringTextComponent("§4[ÖLÜMCÜL ÇATIŞMA] §cVücudundaki iki zıt DNA birbirini yok ediyor! Hücreler çürüyor!"), player.getUniqueID());
            return; // Çatışma varken normal uyum süreci durur
        }

        // 2. TEKİL DNA İÇİN ZAMANLA KADEMELİ UYUM SÜRECİ (%25 -> %50 -> %75 -> %100)
        if (hasSupermanDNA && currentLevel < 100) {
            // Rastgele veya süreye bağlı bir sayaçla uyumu artırabiliriz (Burada örnek bir tick sayacı simüle ediyoruz)
            int adaptationTimer = player.getPersistentData().getInt("SupermanAdaptationTimer");
            adaptationTimer++;

            // Her belli bir süre geçtikçe uyum %25 artar
            if (adaptationTimer >= 600) { // Örnek: Belirli bir süre (örn. 30 saniye) geçince
                currentLevel += 25;
                player.getPersistentData().setInt("SupermanMutationLevel", currentLevel);
                player.getPersistentData().putInt("SupermanAdaptationTimer", 0); // Sayacı sıfırla

                if (currentLevel == 25) {
                    player.sendMessage(new StringTextComponent("§e[Mutasyon] §aVücudunuz Superman DNA'sına alışmaya başladı. Uyum: %25"), player.getUniqueID());
                } else if (currentLevel == 50) {
                    player.sendMessage(new StringTextComponent("§e[Mutasyon] §aHücre adaptasyonu yarılandı. Hafif güç artışı aktif! Uyum: %50"), player.getUniqueID());
                } else if (currentLevel == 75) {
                    player.sendMessage(new StringTextComponent("§e[Mutasyon] §aNeredeyse tam uyum! Uçuş kabiliyeti sinyalleri geliyor... Uyum: %75"), player.getUniqueID());
                } else if (currentLevel == 100) {
                    player.sendMessage(new StringTextComponent("§b[Mutasyon] §lMÜKEMMEL UYUM! §aKryptonlu genler tamamen açıldı! Tüm güçler serbest! Uyum: %100"), player.getUniqueID());
                }
            } else {
                player.getPersistentData().putInt("SupermanAdaptationTimer", adaptationTimer);
            }
        }
    }

    // Belirli bir gücün kilidinin açılıp açılmadığını kontrol eden fonksiyon
    public static boolean hasUnlockedPower(PlayerEntity player, int requiredLevel) {
        int mutationLevel = player.getPersistentData().getInt("SupermanMutationLevel");
        return mutationLevel >= requiredLevel;
    }
}