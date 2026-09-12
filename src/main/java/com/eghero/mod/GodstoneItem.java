package com.eghero.mod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class GodstoneItem extends Item {
    public GodstoneItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, net.minecraft.world.World world, net.minecraft.entity.Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof PlayerEntity && isSelected) {
            // Bu cevher saf tanrısal/teknolojik enerji yayar
        }
    }
}