package com.eghero.mod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.vector.Vector3d;
import org.lwjgl.glfw.GLFW;

public class WolverineDashKeybind {

    // V tuşunu oyuna tanıtıyoruz
    public static final KeyBinding DASH_KEY = new KeyBinding(
            "key.eghero.dash", 
            GLFW.GLFW_KEY_V, 
            "key.categories.eghero"
    );

    // V tuşuna basıldığında gerçekleşecek ileri fırlama olayı
    public static void onDashKeyPressed(PlayerEntity player) {
        // Oyuncunun baktığı yönü alıyoruz
        Vector3d lookVec = player.getLookVec();
        
        // Baktığı yöne doğru ani bir itme (dash) gücü uyguluyoruz (İleri fırlatma)
        player.setMotion(lookVec.x * 2.0D, 0.4D, lookVec.z * 2.0D);
        
        // Hız değişimini sunucuya/oyuna bildiriyoruz
        player.isAirBorne = true;
    }
}