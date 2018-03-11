package com.cjburkey.mods.transloc.tile;

import java.util.List;
import com.cjburkey.mods.transloc.block.BlockTranslocator;
import com.cjburkey.mods.transloc.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityTranslocator extends TileEntity implements ITickable {
	
	private boolean last = false;
	
	public void update() {
		if (getWorld().isRemote) {
			return;
		}
		
		if (!last && getWorld().isBlockIndirectlyGettingPowered(pos) > 0) {
			last = true;
			moveEntity();
		} else if (last && !(getWorld().isBlockIndirectlyGettingPowered(pos) > 0)) {
			last = false;
		}
	}
	
	private void moveEntity() {
		getWorld().playSound(null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, getWorld().rand.nextFloat() * 0.25F + 0.6F);
		EnumFacing facing = getWorld().getBlockState(pos).getValue(BlockTranslocator.FACING);
		if (facing.equals(EnumFacing.DOWN)) {
			dU();
		} else if (facing.equals(EnumFacing.UP)) {
			uD();
		} else if (facing.equals(EnumFacing.EAST)) {
			eW();
		} else if (facing.equals(EnumFacing.WEST)) {
			wE();
		} else if (facing.equals(EnumFacing.NORTH)) {
			nS();
		} else if (facing.equals(EnumFacing.SOUTH)) {
			sN();
		}
	}
	
	// Down > Up
	private void dU() {
		/*List<Entity> l = inArea(getWorld(), pos.getX(), pos.getY() - 1, pos.getZ());
		if (l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));
		}*/
		moveEnts(world, 0, 1, 0);
	}
	
	// Up > Down
	private void uD() {
		/*List<Entity> l = inArea(getWorld(), pos.getX(), pos.getY() + 1, pos.getZ());
		if (l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX(), pos.getY() - (ent.height + 0.1), pos.getZ()));
		}*/
		moveEnts(world, 0, -1, 0);
	}
	
	// East > West
	private void eW() {
		moveEnts(world, -1, 0, 0);
	}
	
	// West > East
	private void wE() {
		moveEnts(world, 1, 0, 0);
	}
	
	// North > South
	private void nS() {
		moveEnts(world, 0, 0, 1);
	}
	
	// South > North
	private void sN() {
		moveEnts(world, 0, 0, -1);
	}
	
	private void moveEnts(World world, int x, int y, int z) {
		BlockPos from = new BlockPos(pos.getX() - x, pos.getY() - y, pos.getZ() - z);
		BlockPos to = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
		List<Entity> l = inArea(getWorld(), from.getX(), from.getY(), from.getZ());
		if (l.size() <= 0) {
			return;
		}
		if (CommonProxy.multipleEntities) {
			for (Entity ent : l) {
				moveEnt(ent, to);
			}
		} else {
			Entity ent = l.get(0);
			moveEnt(ent, to);
		}
	}
	
	public void moveEnt(Entity ent, BlockPos p) {
		if (p.getY() < pos.getY()) {
			p = new BlockPos(p.getX(), (int) Math.floor(pos.getY() - ent.height), pos.getZ());
		}
		ent.setPositionAndUpdate(p.getX() + 0.5, p.getY(), p.getZ() + 0.5);
	}
	
	public List<Entity> inArea(World w, int x, int y, int z) {
		return w.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(x - 0.5d, y - 0.5d, z - 0.5d, x + 0.5d, y + 0.5d, z + 0.5d));
	}
	
}