package com.cjburkey.mods.transloc.proxy;

import com.cjburkey.mods.transloc.tile._Tiles;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public static Configuration config;
	public static boolean multipleEntities;
	
	public void preinit(FMLPreInitializationEvent e) {
		_Tiles.commonPreinit();
		
		config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		multipleEntities = config.getBoolean("multipleEntities", "main", true, "Whether or not the Translocation block should teleport ALL entities directly in front of it, or only one at a time.");
		config.save();
	}
	
	public void init(FMLInitializationEvent e) {
	}
	
	public void postinit(FMLPostInitializationEvent e) {
	}
	
}