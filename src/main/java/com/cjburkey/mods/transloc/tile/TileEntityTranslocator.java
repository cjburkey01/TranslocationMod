package com.cjburkey.mods.transloc.tile;

import java.util.List;
import com.cjburkey.mods.transloc.block.BlockTranslocator;
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
		if(this.worldObj.isRemote) { return; }
		
		if(!last && this.worldObj.isBlockIndirectlyGettingPowered(pos) > 0) {
			last = true;
			moveEntity();
		} else if(last && !(this.worldObj.isBlockIndirectlyGettingPowered(pos) > 0)) {
			last = false;
		}
	}
	
	private void moveEntity() {
		this.worldObj.playSound(null, pos, SoundEvents.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, this.worldObj.rand.nextFloat() * 0.25F + 0.6F);
		EnumFacing facing = this.worldObj.getBlockState(pos).getValue(BlockTranslocator.FACING);
		if(facing.equals(EnumFacing.DOWN)) {
			dU();
		} else if(facing.equals(EnumFacing.UP)) {
			uD();
		} else if(facing.equals(EnumFacing.EAST)) {
			eW();
		} else if(facing.equals(EnumFacing.WEST)) {
			wE();
		} else if(facing.equals(EnumFacing.NORTH)) {
			nS();
		} else if(facing.equals(EnumFacing.SOUTH)) {
			sN();
		}
	}
	
	// Down > Up
	private void dU() {
		List<Entity> l = inArea(this.worldObj, pos.getX(), pos.getY() - 1, pos.getZ());
		if(l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));
		}
	}
	
	// Up > Down
	private void uD() {
		List<Entity> l = inArea(this.worldObj, pos.getX(), pos.getY() + 1, pos.getZ());
		if(l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX(), pos.getY() - (ent.height + 0.1), pos.getZ()));
		}
	}
	
	// East > West
	private void eW() {
		List<Entity> l = inArea(this.worldObj, pos.getX() + 1, pos.getY(), pos.getZ());
		if(l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()));
		}
	}
	
	// West > East
	private void wE() {
		List<Entity> l = inArea(this.worldObj, pos.getX() - 1, pos.getY(), pos.getZ());
		if(l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()));
		}
	}
	
	// North > South
	private void nS() {
		List<Entity> l = inArea(this.worldObj, pos.getX(), pos.getY(), pos.getZ() - 1);
		if(l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1));
		}
	}
	
	// South > North
	private void sN() {
		List<Entity> l = inArea(this.worldObj, pos.getX(), pos.getY(), pos.getZ() + 1);
		if(l.size() >= 1) {
			Entity ent = l.get(0);
			moveEnt(ent, new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1));
		}
	}
	
	public void moveEnt(Entity ent, BlockPos p) {
		ent.setPositionAndUpdate(p.getX() + 0.5, p.getY(), p.getZ() + 0.5);
	}
	
	public List<Entity> inArea(World w, int x, int y, int z) {
		return w.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(x - 0.5d, y - 0.5d, z - 0.5d, x + 0.5d, y + 0.5d, z + 0.5d));
	}
	
}