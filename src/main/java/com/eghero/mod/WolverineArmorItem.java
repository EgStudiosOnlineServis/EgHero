package com.eghero.mod;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class WolverineArmorItem extends ArmorItem {

    public WolverineArmorItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
        super(materialIn, slot, builderIn);
    }

    // Kostüm giyildiğinde arka planda sürekli çalışacak pasif güç (Yenilenme / Regeneration)
    @Override
    public void onArmorTick(net.minecraft.item.ItemStack stack, World world, PlayerEntity player) {
        if (!world.isRemote) {
            // Wolverine'in efsanevi iyileşme gücü
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 0, true, false));
        }
    }
}