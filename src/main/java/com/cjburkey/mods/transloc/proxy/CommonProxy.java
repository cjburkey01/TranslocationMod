package com.cjburkey.mods.transloc.proxy;

import com.cjburkey.mods.transloc.block._Blocks;
import com.cjburkey.mods.transloc.item._Items;
import com.cjburkey.mods.transloc.rec._Recipes;
import com.cjburkey.mods.transloc.tile.TileEntityTranslocator;
import com.cjburkey.mods.transloc.tile._Tiles;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void preinit(FMLPreInitializationEvent e) {
		_Items.commonPreinit();
		_Blocks.commonPreinit();
		_Tiles.commonPreinit();
	}
	
	public void init(FMLInitializationEvent e) {
		_Recipes.commonInit();
	}
	
	public void postinit(FMLPostInitializationEvent e) {  }
	
}