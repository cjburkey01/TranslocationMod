package com.cjburkey.mods.transloc.rec;

import com.cjburkey.mods.transloc.block._Blocks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class _Recipes {
	
	public static final void commonInit() {
		addShaped(new ItemStack(_Blocks.blockTranslocator, 1), new Object[] {
			"yxy",
			"zaz",
			"yxy",
			Character.valueOf('x'), Blocks.PISTON,
			Character.valueOf('y'), "ingotIron",
			Character.valueOf('z'), "ingotGold",
			Character.valueOf('a'), "dustRedstone",
		});
	}
	
	private static final void addShaped(ItemStack out, Object... in) {
		GameRegistry.addRecipe(new ShapedOreRecipe(out, in));
	}
	
	private static final void addShapeless(ItemStack out, Object... in) {
		GameRegistry.addRecipe(new ShapelessOreRecipe(out, in));
	}
	
}