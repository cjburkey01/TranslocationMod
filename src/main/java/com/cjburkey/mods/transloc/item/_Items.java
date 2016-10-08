package com.cjburkey.mods.transloc.item;

import java.util.ArrayList;
import java.util.List;
import com.cjburkey.mods.transloc.Info;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class _Items {
	
	private static final List<Item> items = new ArrayList<Item>();
	
	public static Item testItem;
	
	//-- Common --//
	
	public static final void commonPreinit() {
		//testItem = registerItem("testItem", new Item());
	}
	
	public static final Item registerItem(String n, Item i) {
		ResourceLocation r = new ResourceLocation(Info.id, n);
		i.setRegistryName(r);
		i.setUnlocalizedName(n);
		i.setCreativeTab(CreativeTabs.MISC);
		GameRegistry.register(i);
		items.add(i);
		return i;
	}
	
	//-- Client --//
	
	public static final void clientInit() {
		for(Item i : items) {
			registerRender(i);
		}
	}
	
	public static final void registerRender(Item i) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(i.getRegistryName(), "inventory"));
	}
	
}