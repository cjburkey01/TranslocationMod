package com.cjburkey.mods.transloc.item;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.mods.transloc.ModInfo;
import com.cjburkey.mods.transloc.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ModInfo.MODID)
public final class ModItems {
	
	private static final List<Item> items = new ArrayList<Item>();
	private static final List<ItemBlock> blocks = new ArrayList<ItemBlock>();
	
	@SubscribeEvent
	public static void registerItems(Register<Item> e) {
		addItems(e);
		addBlocks(e);
	}
	
	@SubscribeEvent
	public static void registerItems(ModelRegistryEvent e) {
		registerRenders(e);
	}
	
	private static void addItems(Register<Item> e) {
	}
	
	private static void addBlocks(Register<Item> e) {
		for (Block block : ModBlocks.getBlocks()) {
			registerItemBlock(block, e);
		}
	}
	
	private static ItemBlock registerItemBlock(Block block, Register<Item> e) {
		String unloc = block.getUnlocalizedName().substring(5);
		return (ItemBlock) registerItem(new ItemBlock(block), unloc, e);
	}
	
	private static Item registerItem(Item item, String unloc, Register<Item> e) {
		item.setUnlocalizedName(unloc);
		item.setRegistryName(new ResourceLocation(ModInfo.MODID, unloc));
		items.add(item);
		e.getRegistry().register(item);
		return item;
	}
	
	private static void registerRenders(ModelRegistryEvent e) {
		for (Item item : items) {
			registerRender(item, e);
		}
		for (ItemBlock block : blocks) {
			registerRender(block, e);
		}
	}
	
	private static void registerRender(Item item, ModelRegistryEvent e) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}