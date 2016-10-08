package com.cjburkey.mods.transloc.block;

import java.util.HashMap;
import com.cjburkey.mods.transloc.Info;
import com.cjburkey.mods.transloc.item._Items;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class _Blocks {
	
	private static final HashMap<Block, Item> blocks = new HashMap<Block, Item>();
	
	public static Block blockTranslocator;
	
	//-- Common --//
	
	public static final void commonPreinit() {
		blockTranslocator = registerBlock("blockTranslocator", new BlockTranslocator());
	}
	
	public static final Block registerBlock(String n, Block b) {
		ResourceLocation r = new ResourceLocation(Info.id, n);
		b.setRegistryName(r);
		b.setUnlocalizedName(n);
		GameRegistry.register(b);
		blocks.put(b, _Items.registerItem(n, new ItemBlock(b)));
		return b;
	}
	
	//-- Client --//
	
	public static final void clientInit() {  }
	
}