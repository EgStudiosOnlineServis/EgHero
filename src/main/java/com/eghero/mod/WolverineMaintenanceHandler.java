package com.eghero.mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;

public class WolverineMaintenanceHandler {

    // Her oyun tikinde Adamantium iskeletin durumunu kontrol eden döngü
    public static void serverTick(PlayerEntity player) {
        if (player.world.isRemote) return;

        boolean hasAdamantium = player.getPersistentData().getBoolean("HasAdamantiumSkeleton");
        if (!hasAdamantium) return;

        int adamantiumDurability = player.getPersistentData().getInt("AdamantiumDurabilityTimer");

        if (adamantiumDurability <= 0) {
            // 50 dakika doldu, bakım vakti! Hafif zehirlenme ve yavaşlama başlar
            player.addPotionEffect(new EffectInstance(Effects.POISON, 100, 0, true, false));
            player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 1, true, false));
            player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 0, true, false));

            player.sendMessage(new StringTextComponent("§4[UYARI] §cAdamantium iskeletin yıprandı (50 dakika doldu)! Acilen Weapon X Kapsülü'ne girip bakım yapmalısın!"), player.getUniqueID());
        } else {
            player.getPersistentData().putInt("AdamantiumDurabilityTimer", adamantiumDurability - 1);
        }
    }

    // Kapsülde banyo yapıldığında süreyi tam 50 dakikaya (60.000 tick) sabitleyen fonksiyon
    public static void replenishAdamantium(PlayerEntity player) {
        // 50 dakika = 3000 saniye = 60.000 tick (Minecraft'ta saniyede 20 tick vardır)
        player.getPersistentData().putInt("AdamantiumDurabilityTimer", 60000);
        player.removePotionEffect(Effects.POISON);
        player.removePotionEffect(Effects.WEAKNESS);
        player.removePotionEffect(Effects.SLOWNESS);
        
        player.sendMessage(new StringTextComponent("§b[WEAPON X] §aAdamantium banyosu tamamlandı! İskelet yenilendi. Önünde kesintisiz 50 dakikalık bir güç süresi var!"), player.getUniqueID());
    }
}