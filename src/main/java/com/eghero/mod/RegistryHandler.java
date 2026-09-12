package com.eghero.mod;

import net.minecraft.block.Block;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    // Eşyalar ve Bloklar için Forge kayıt defterleri
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "eghero");
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "eghero");

    // --- 1. BLOKLAR VE BLOK EŞYALARI (Creative Sekmesine Dahil Edilir) ---
    
    // Weapon X Kapsül Bloğu
    public static final RegistryObject<Block> WEAPON_X_POD_BLOCK = BLOCKS.register("weapon_x_pod", WeaponXPodBlock::new);
    public static final RegistryObject<Item> WEAPON_X_POD_ITEM = ITEMS.register("weapon_x_pod", 
            () -> new BlockItem(WEAPON_X_POD_BLOCK.get(), new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));


    // --- 2. MADENLER VE İNGOTLAR (Creative Sekmesine Dahil Edilir) ---
    
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", 
            () -> new TitaniumIngotItem(new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));

    public static final RegistryObject<Item> ADAMANTIUM_INGOT = ITEMS.register("adamantium_ingot", 
            () -> new AdamantiumIngotItem(new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB).maxStackSize(16)));


    // --- 3. SÜPER KAHRAMAN EŞYALARI VE SİLAHLARI (Creative Sekmesine Dahil Edilir) ---
    
    // Mjölnir Çekici
    public static final RegistryObject<Item> MJOLNIR_ITEM = ITEMS.register("mjolnir", 
            () -> new MjolnirItem(new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB).maxStackSize(1)));

    // Gen Enjektörü
    public static final RegistryObject<Item> GENE_INJECTOR = ITEMS.register("gene_injector", 
            () -> new GeneInjectorItem(new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB).maxStackSize(1)));

    // Ağ Atıcı (Web Shooter)
    public static final RegistryObject<Item> WEB_SHOOTER_ITEM = ITEMS.register("web_shooter", 
            () -> new Item(new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB).maxStackSize(1)));


    // --- 4. THOR KOSTÜMÜ VE ZIRHLARI (Creative Sekmesine Dahil Edilir) ---
    
    public static final RegistryObject<Item> THOR_HELMET = ITEMS.register("thor_helmet", 
            () -> new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));
    
    public static final RegistryObject<Item> THOR_CHESTPLATE = ITEMS.register("thor_chestplate", 
            () -> new ThorArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));
    
    public static final RegistryObject<Item> THOR_LEGGINGS = ITEMS.register("thor_leggings", 
            () -> new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));
    
    public static final RegistryObject<Item> THOR_BOOTS = ITEMS.register("thor_boots", 
            () -> new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));


    // --- 5. SPİDER-MAN KOSTÜMÜ VE ZIRHLARI (Creative Sekmesine Dahil Edilir) ---
    
    public static final RegistryObject<Item> SPIDERMAN_HELMET = ITEMS.register("spiderman_helmet", 
            () -> new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));
    
    public static final RegistryObject<Item> SPIDERMAN_CHESTPLATE = ITEMS.register("spiderman_chestplate", 
            () -> new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));
    
    public static final RegistryObject<Item> SPIDERMAN_LEGGINGS = ITEMS.register("spiderman_leggings", 
            () -> new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.LEGS, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));
    
    public static final RegistryObject<Item> SPIDERMAN_BOOTS = ITEMS.register("spiderman_boots", 
            () -> new ArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));

    // --- 6. IRON MAN TEKNOLOJİSİ VE MARK ZIRHLARI (Creative Sekmesine Dahil Edilir) ---

    // Mark 1 (İlk Hurda Zırh)
    public static final RegistryObject<Item> MARK1_CHESTPLATE = ITEMS.register("mark1_chestplate", 
            () -> new IronManArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, 1, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));

    // Mark 3 (Klasik Kırmızı-Altın)
    public static final RegistryObject<Item> MARK3_CHESTPLATE = ITEMS.register("mark3_chestplate", 
            () -> new IronManArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, 3, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));

    // Mark 50 (Nanoteknoloji)
    public static final RegistryObject<Item> MARK50_CHESTPLATE = ITEMS.register("mark50_chestplate", 
            () -> new IronManArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, 50, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));

    // Mark 75 (Zirve Teknoloji)
    public static final RegistryObject<Item> MARK75_CHESTPLATE = ITEMS.register("mark75_chestplate", 
            () -> new IronManArmorItem(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, 75, new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));

      // --- 7. ATÖLYE VE ÖZEL CEVHERLER (Creative Sekmesine Dahil Edilir) ---

    // Enerji Tutan İlahi Cevher (Godstone)
    public static final RegistryObject<Item> GODSTONE_ITEM = ITEMS.register("godstone", 
            () -> new GodstoneItem(new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));

    // Zırh Sergileme Standı Bloğu
    public static final RegistryObject<Block> ARMOR_STAND_BLOCK = BLOCKS.register("armor_stand_block", ArmorStandBlock::new);
    public static final RegistryObject<Item> ARMOR_STAND_ITEM = ITEMS.register("armor_stand_block", 
            () -> new BlockItem(ARMOR_STAND_BLOCK.get(), new Item.Properties().group(EgHeroItemGroup.EGHERO_TAB)));
  
}
