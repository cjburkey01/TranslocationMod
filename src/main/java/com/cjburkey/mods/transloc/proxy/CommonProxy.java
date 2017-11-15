package com.cjburkey.mods.transloc.proxy;

import com.cjburkey.mods.transloc.tile._Tiles;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent e) {
		_Tiles.commonPreinit();
	}
	
	public void init(FMLInitializationEvent e) {
	}
	
	public void postinit(FMLPostInitializationEvent e) {
	}
	
}