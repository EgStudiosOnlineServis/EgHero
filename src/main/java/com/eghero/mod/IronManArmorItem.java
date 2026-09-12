package com.eghero.mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class IronManArmorItem extends ArmorItem {

    private final int markLevel; // Zırhın Mark seviyesi (Örn: 1, 3, 50, 75)

    public IronManArmorItem(ArmorMaterial material, EquipmentSlotType slot, int markLevel, Properties properties) {
        super(material, slot, properties);
        this.markLevel = markLevel;
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (world.isRemote) return;

        // Oyuncu tam Iron Man setini giyiyorsa zırh özelliklerini ve HUD avantajlarını aktif eder
        if (isWearingFullSet(player)) {
            // Mark seviyesine göre artan güç ve gece görüşü (HUD simülasyonu)
            player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, markLevel > 50 ? 2 : 1, true, false));
            player.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 220, 0, true, false)); // HUD Tarama Ekranı
            
            // Yüksek Mark zırhlarında uçuş / süzülme yeteneği
            if (markLevel >= 7) {
                player.fallDistance = 0.0F;
            }
        }
    }

    private boolean isWearingFullSet(PlayerEntity player) {
        // Set kontrolü (Basitleştirilmiş örnek)
        return player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof IronManArmorItem;
    }
}