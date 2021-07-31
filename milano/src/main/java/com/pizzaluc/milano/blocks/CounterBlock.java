package com.pizzaluc.milano.blocks;

import com.pizzaluc.milano.milano;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CounterBlock extends Block {

    public CounterBlock() {
        super(Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .strength(2.0f)
        );
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return milano.COUNTER_TE.get().create();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isClientSide) {
            TileEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof CounterTileEntity) {
                CompoundNBT val = world.getBlockEntity(pos).getTileData();
                if (world.getBlockEntity(pos).getTileData().isEmpty()) {
                    val.putInt("counter", 1);
                } else {
                    val.putInt("counter", val.getInt("counter") + 1);
                }

                System.out.println(world.getBlockEntity(pos).getTileData().getInt("counter"));
            } else {

            }
        }
        return ActionResultType.SUCCESS;
    }


}
