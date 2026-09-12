package com.eghero.mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;

public class MjolnirItem extends Item {

    public MjolnirItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            
            // Eğer oyuncunun elinde Mjölnir varsa ama Thor kostümü giymemişse
            if (player.getHeldItemMainhand() == stack || player.getHeldItemOffhand() == stack) {
                if (!ThorArmorItem.hasThorArmorEquipped(player)) {
                    player.sendMessage(new StringTextComponent("§cMjölnir çok ağır! Onu sadece Asgard'ın seçilmiş varisi taşıyabilir!"), player.getUniqueID());
                    
                    // Çekici oyuncunun envanterinden düşürüp yere fırlatıyoruz
                    player.dropItem(stack.copy(), false);
                    stack.setCount(0); // Elden siliyoruz
                }
            }
        }
    }

    // Oyuncunun elinde Mjölnir ve Thor kostümü var mı kontrolü
    public static boolean isHoldingMjolnir(PlayerEntity player) {
        if (!ThorArmorItem.hasThorArmorEquipped(player)) return false;
        ItemStack main = player.getHeldItemMainhand();
        return main.getItem() instanceof MjolnirItem;
    }
}