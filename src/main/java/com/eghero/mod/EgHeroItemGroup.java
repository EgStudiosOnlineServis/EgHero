package com.eghero.mod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EgHeroItemGroup extends ItemGroup {
    
    public static final EgHeroItemGroup EGHERO_TAB = new EgHeroItemGroup("eghero_tab");

    public EgHeroItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        // Sekmenin simgesi olarak Mjölnir çekicini veya Superman DNA'sını koyuyoruz
        // (RegistryHandler üzerinden ana item'lardan biri buraya bağlanabilir)
        return new ItemStack(RegistryHandler.MJOLNIR_ITEM.get()); 
    }
}