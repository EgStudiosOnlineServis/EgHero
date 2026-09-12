package com.eghero.mod;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SupermanDNAItem extends ArmorItem {

    public SupermanDNAItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    // Kostüm giyildiği sürece arka planda çalışacak pasif Superman güçleri
    @WolverineOrSupermanTag // (Mantıksal imza)
    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isRemote) {
            // Çelik gibi sağlamlık (Resistance) ve Güç (Strength) pasif olarak gelir
            player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 1, true, false));
            player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 40, 1, true, false));
        }
    }

    // Kriptonit sisteminin oyuncuyu taradığında "Bu adamda Superman DNA'sı var!" demesini sağlayan kontrol fonksiyonu
    public static boolean hasSupermanDNAEquipped(PlayerEntity player) {
        ItemStack chestplate = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        return chestplate.getItem() instanceof SupermanDNAItem;
    }
}