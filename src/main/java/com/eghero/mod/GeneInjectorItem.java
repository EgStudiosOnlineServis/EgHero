package com.eghero.mod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class GeneInjectorItem extends Item {

    public GeneInjectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote) {
            boolean hasSupermanDNA = player.getPersistentData().getBoolean("HasSupermanDNA");

            if (!hasSupermanDNA) {
                // Enjektör bir kere basılır ve süreç otomatik başlar (%0'dan başlar, zamanla %100 olur)
                player.getPersistentData().putBoolean("HasSupermanDNA", true);
                player.getPersistentData().setInt("SupermanMutationLevel", 0);
                player.getPersistentData().putInt("SupermanAdaptationTimer", 0);

                player.sendMessage(new StringTextComponent("§d[Gen Enjektörü] §aDNA damarlarınıza zerk edildi! Vücudun bu yabancı gene alışması bekleniyor..."), player.getUniqueID());

                // Enjektör tüketilir
                stack.shrink(1);
            } else {
                player.sendMessage(new StringTextComponent("§cBu gen zaten damarlarında bulunuyor!"), player.getUniqueID());
            }
        }

        return ActionResult.resultSuccess(stack);
    }
}