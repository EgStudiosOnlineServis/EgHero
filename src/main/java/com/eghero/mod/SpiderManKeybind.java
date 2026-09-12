package com.eghero.mod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class SpiderManKeybind {

    // V tuşu: Ağ Atıcı (Düşmanı veya hedefi ağla bağlayıp yavaşlatma / yakalama)
    public static final KeyBinding WEB_SHOOTER_KEY = new KeyBinding(
            "key.eghero.webshooter", 
            GLFW.GLFW_KEY_V, 
            "key.categories.eghero"
    );

    public static void onWebShooterKeyPressed(PlayerEntity player) {
        if (player.world.isRemote()) {
            player.sendMessage(new StringTextComponent("§c*Thwip!* §fAğ fırlatıldı!"), player.getUniqueID());
        }

        // Ağ atma ses efekti
        player.playSound(SoundEvents.ENTITY_ARROW_SHOOT, 1.0F, 1.5F);

        // Oyuncunun baktığı yöndeki hedefleri ağla sarıp dondurma / yavaşlatma mantığı
        Vector3d look = player.getLookVec();
        Vector3d startPos = player.getPositionVec();
        AxisAlignedBB webArea = new AxisAlignedBB(
                startPos.x + look.x * 4, startPos.y, startPos.z + look.z * 4,
                startPos.x + look.x * 6, startPos.y + 2, startPos.z + look.z * 6
        ).grow(2.0D);

        List<LivingEntity> targets = player.world.getEntitiesWithinAABB(LivingEntity.class, webArea);
        for (LivingEntity target : targets) {
            if (target != player) {
                // Ağda kalan hedefe ağır yavaşlık ve karanlık efekti veriyoruz
                target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 5, true, false));
                target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 60, 0, true, false));
            }
        }
    }
}