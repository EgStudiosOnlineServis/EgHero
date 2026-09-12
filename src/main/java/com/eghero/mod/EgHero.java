package com.eghero.mod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("eghero")
public class EgHero {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "eghero";

    public EgHero() {
        // Modun yaşam döngüsü olaylarını buraya bağlıyoruz
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Oyun içi olayları (Event Bus) dinlemek için kaydediyoruz
        MinecraftForge.EVENT_BUS.register(this);
        
        LOGGER.info("EgHero Modu başarıyla yükleniyor! Marvel & DC evrenleri birleşiyor...");
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Ön yükleme ayarları ve kayıt işlemleri burada gerçekleşir
        LOGGER.info("EgHero: Tüm güçler ve yetenekler aktif hale getirildi.");
    }
}