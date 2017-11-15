package com.cjburkey.mods.transloc;

import com.cjburkey.mods.transloc.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = ModInfo.NAME, modid = ModInfo.MODID, version = ModInfo.VERSION)
public class TranslocMod {
	
	@Instance(ModInfo.MODID)
	public static TranslocMod instance;
	
	@SidedProxy(modId = ModInfo.MODID, clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) {
		proxy.preinit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent e) {
		proxy.postinit(e);
	}
	
}