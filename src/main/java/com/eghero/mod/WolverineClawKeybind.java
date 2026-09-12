package com.eghero.mod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.settings.IKeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class WolverineClawKeybind {

    // C tuşunu oyuna tanıtıyoruz
    public static final KeyBinding CLAW_KEY = new KeyBinding(
            "key.eghero.claw", 
            GLFW.GLFW_KEY_C, 
            "key.categories.eghero"
    );

    // Tuşa basıldığında gerçekleşecek olay
    public static void onClawKeyPressed(PlayerEntity player) {
        if (player.world.isRemote()) {
            // Oyuncuya ekranda küçük bir bilgi mesajı veya pençe efekti tetikleyebiliriz
            player.sendMessage(new StringTextComponent("§4Wolverine Pençeleri Çıktı!"), player.getUniqueID());
        }
        
        // Burada pençe vuruş efekti veya yakınındaki düşmana hasar verme mantığı çalışır
        player.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1.0F, 0.5F);
    }
}