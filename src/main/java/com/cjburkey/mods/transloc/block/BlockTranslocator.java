package com.cjburkey.mods.transloc.block;

import java.util.Random;
import javax.annotation.Nullable;
import com.cjburkey.mods.transloc.tile.TileEntityTranslocator;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class BlockTranslocator extends BlockDirectional implements ITileEntityProvider {
	
	public BlockTranslocator() {
		super(Material.WOOD);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setHardness(1.5f);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 0);
	}
	
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTranslocator();
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		EnumFacing f = getFacingFromEntity(pos, placer);
		return this.getDefaultState().withProperty(FACING, f);
	}
	
	@Nullable
	public static EnumFacing getFacing(int meta) {
		int i = meta & 7;
		return i > 5 ? null : EnumFacing.getFront(i);
	}
	
	public static EnumFacing getFacingFromEntity(BlockPos pos, EntityLivingBase ent) {
		if (MathHelper.abs((float) ent.posX - (float) pos.getX()) < 2.0F && MathHelper.abs((float) ent.posZ - (float) pos.getZ()) < 2.0F) {
			double d0 = ent.posY + (double) ent.getEyeHeight();
			if (d0 - (double) pos.getY() > 2.0D) {
				return EnumFacing.UP;
			}
			if ((double) pos.getY() - d0 > 0.0D) {
				return EnumFacing.DOWN;
			}
		}
		return ent.getHorizontalFacing().getOpposite();
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, getFacing(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | ((EnumFacing) state.getValue(FACING)).getIndex();
		return i;
	}
	
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}
	
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	public int quantityDropped(Random r) {
		return 1;
	}
	
}