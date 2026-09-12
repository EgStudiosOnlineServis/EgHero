package com.eghero.mod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.glfw.GLFW;

public class WolverineClimbKeybind {

    // G tuşunu oyuna tanıtıyoruz
    public static final KeyBinding CLIMB_KEY = new KeyBinding(
            "key.eghero.climb", 
            GLFW.GLFW_KEY_G, 
            "key.categories.eghero"
    );

    // Duvara tırmanma modunun açık/kapalı durumu
    private static boolean isClimbingActive = false;

    // G tuşuna basıldığında çalışacak aç/kapa (toggle) olayı
    public static void onClimbKeyPressed(PlayerEntity player) {
        isClimbingActive = !isClimbingActive;

        if (isClimbingActive) {
            player.sendMessage(new StringTextComponent("§2Duvara Tırmanma: AÇIK"), player.getUniqueID());
        } else {
            player.sendMessage(new StringTextComponent("§cDuvara Tırmanma: KAPALI"), player.getUniqueID());
        }
    }

    // Oyuncu duvara yakınken ve mod açıkken düşmesini engelleyen tırmanma mantığı
    public static void handleClimbing(PlayerEntity player) {
        if (isClimbingActive && player.collidedHorizontally) {
            // Duvara tutunurken aşağı kaymasını engellemek için dikey hızı sabitliyoruz
            player.setMotion(player.getMotion().x, 0.2D, player.getMotion().z);
            player.fallDistance = 0.0F; // Düşme hasarını sıfırlar
        }
    }
}