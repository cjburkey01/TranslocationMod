package com.cjburkey.mods.transloc;

import com.cjburkey.mods.transloc.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(name = Info.name, modid = Info.id, version = Info.version)
public class TranslocMod {
	
	@Instance
	public static TranslocMod instance;
	
	@SidedProxy(clientSide = Info.clientProxy, serverSide = Info.serverProxy)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent e) { proxy.preinit(e); }

	@EventHandler
	public void init(FMLInitializationEvent e) { proxy.init(e); }

	@EventHandler
	public void postinit(FMLPostInitializationEvent e) { proxy.postinit(e); }
	
}