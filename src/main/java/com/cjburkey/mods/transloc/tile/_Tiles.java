package com.cjburkey.mods.transloc.tile;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class _Tiles {
	
	public static final void commonPreinit() {
		GameRegistry.registerTileEntity(TileEntityTranslocator.class, "tileEntityTranslocator");
	}
	
}