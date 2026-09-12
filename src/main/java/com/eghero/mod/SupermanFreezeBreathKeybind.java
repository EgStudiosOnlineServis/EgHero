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

public class SupermanFreezeBreathKeybind {

    // C tuşunu Soğuk Nefes için tanıtıyoruz (Wolverine pençesiyle aynı tuş, duruma göre ayarlanabilir)
    public static final KeyBinding FREEZE_BREATH_KEY = new KeyBinding(
            "key.eghero.freezebreath", 
            GLFW.GLFW_KEY_C, 
            "key.categories.eghero"
    );

    // C tuşuna basıldığında tetiklenecek ana fonksiyon
    public static void onFreezeBreathKeyPressed(PlayerEntity player) {
        // Önce oyuncunun üzerinde Superman DNA'sı var mı diye kontrol ediyoruz
        if (!SupermanDNAItem.hasSupermanDNAEquipped(player)) {
            player.sendMessage(new StringTextComponent("§cBu gücü kullanmak için Superman DNA'sına sahip olmalısın!"), player.getUniqueID());
            return;
        }

        if (player.world.isRemote()) {
            player.sendMessage(new StringTextComponent("§b[Soğuk Nefes] §fFiyuvvv! Ortalık buz kesti!"), player.getUniqueID());
        }

        // Buz üfleme ses efekti
        player.playSound(SoundEvents.ENTITY_PLAYER_BREATH, 1.0F, 0.5F);

        // Oyuncunun baktığı yönde 5 blokluk bir alan tarıyoruz
        Vector3d look = player.getLookVec();
        Vector3d startPos = player.getPositionVec();
        AxisAlignedBB breathArea = new AxisAlignedBB(
                startPos.x + look.x * 5, startPos.y, startPos.z + look.z * 5,
                startPos.x + look.x * 6, startPos.y + 2, startPos.z + look.z * 6
        ).grow(3.0D);

        // Alandaki tüm canlıları bulup donduruyoruz (Yavaşlık ve Körlük vererek)
        List<LivingEntity> targets = player.world.getEntitiesWithinAABB(LivingEntity.class, breathArea);
        for (LivingEntity target : targets) {
            if (target != player) {
                target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 140, 4, true, false)); // Ağır yavaşlatma (donma etkisi)
                target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 140, 1, true, false));
            }
        }
    }
}