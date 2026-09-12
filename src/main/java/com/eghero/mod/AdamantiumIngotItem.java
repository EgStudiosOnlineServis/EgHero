package com.eghero.mod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class AdamantiumIngotItem extends Item {
    public AdamantiumIngotItem(Properties properties) {
        super(properties);
    }

    // Adamantium çok zehirli ve ağır bir metal olduğu için çıplak elle tutulduğunda hafif radyoaktif etki verebilir
    @Override
    public void inventoryTick(ItemStack stack, net.minecraft.world.World world, net.minecraft.entity.Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof PlayerEntity && isSelected) {
            PlayerEntity player = (PlayerEntity) entity;
            // Adamantium'un o saf, işlenmemiş ham halinin tehlikesini hissettiren detay
            // (İleride Weapon X laboratuvar tezgahında işlenecek)
        }
    }
}