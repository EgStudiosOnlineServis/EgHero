package com.eghero.mod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public class ArcReactorItem extends Item implements ICurioItem {

    public ArcReactorItem(Properties properties) {
        super(properties);
    }

    // Curios API standartlarına göre sırt slotunda çalışırken pasif enerji üretimi
    @Override
    public void curioTick(String identifier, int index, net.minecraft.entity.LivingEntity livingEntity) {
        if (livingEntity.world.isRemote) return;
        
        // Sırtında Ark Reaktörü olan oyuncuya sürekli enerji/güç sinyali verilir
        livingEntity.getPersistentData().putBoolean("HasArcReactorEquipped", true);
    }
}