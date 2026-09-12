package com.eghero.mod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.glfw.GLFW;

public class SupermanLaserKeybind {

    // B tuşunu oyuna lazer için tanıtıyoruz
    public static final KeyBinding LASER_KEY = new KeyBinding(
            "key.eghero.laser", 
            GLFW.GLFW_KEY_B, 
            "key.categories.eghero"
    );

    // B tuşuna basıldığında tetiklenecek ana fonksiyon
    public static void onLaserKeyPressed(PlayerEntity player) {
        // Önce oyuncunun üzerinde Superman DNA'sı var mı diye kontrol ediyoruz
        if (!SupermanDNAItem.hasSupermanDNAEquipped(player)) {
            player.sendMessage(new StringTextComponent("§cBu gücü kullanmak için Superman DNA'sına sahip olmalısın!"), player.getUniqueID());
            return;
        }

        if (player.world.isRemote()) {
            player.sendMessage(new StringTextComponent("§4[Göz Lazerleri] §cZzzzt! Kızıl ışınlar ateşlendi!"), player.getUniqueID());
        }

        // Lazerin ateşlenme sesi (yoğun enerji efekti için)
        player.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 0.5F, 2.0F);

        // Oyuncunun baktığı yönü hesaplıyoruz
        Vector3d lookVec = player.getLookVec();
        
        // Lazerin isabet edeceği alandaki düşmanları yakma mantığı burada çalışır
        // Oyuncunun baktığı yöne doğru küçük bir patlama/yakma etkisi yaratılabilir
        player.setFire(3); // Örnek olarak oyuncunun etrafındaki enerjiyi yansıtır
    }
}