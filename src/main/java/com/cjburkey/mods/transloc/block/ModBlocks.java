package com.cjburkey.mods.transloc.block;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.mods.transloc.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = ModInfo.MODID)
public class ModBlocks {
	
	private static final List<Block> blocks = new ArrayList<>();
	
	public static Block blockTranslocator;
	
	@SubscribeEvent
	public static void registerBlocks(Register<Block> e) {
		addBlocks(e);
	}
	
	private static void addBlocks(Register<Block> e) {
		blockTranslocator = registerBlock(new BlockTranslocator(), "block_translocator", e);
	}
	
	private static Block registerBlock(Block block, String unloc, Register<Block> e) {
		block.setUnlocalizedName(unloc);
		block.setRegistryName(new ResourceLocation(ModInfo.MODID, unloc));
		e.getRegistry().register(block);
		blocks.add(block);
		return block;
	}
	
	public static Block[] getBlocks() {
		return blocks.toArray(new Block[blocks.size()]);
	}
	
}
