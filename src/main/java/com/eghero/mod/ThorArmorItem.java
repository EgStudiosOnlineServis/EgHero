package com.eghero.mod;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ThorArmorItem extends ArmorItem {

    public ThorArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    @Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
        if (!world.isRemote) {
            // Asgardian gücü: Pasif olarak Güç ve Direnç verir
            player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 40, 1, true, false));
            player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 0, true, false));
        }
    }

    // Oyuncunun üzerinde Thor kostümü olup olmadığını kontrol eden fonksiyon
    public static boolean hasThorArmorEquipped(PlayerEntity player) {
        ItemStack chestplate = player.getItemStackFromSlot(EquipmentSlotType.CHEST);
        return chestplate.getItem() instanceof ThorArmorItem;
    }
}