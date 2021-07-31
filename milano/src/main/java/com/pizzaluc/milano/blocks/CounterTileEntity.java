package com.pizzaluc.milano.blocks;

import com.pizzaluc.milano.milano;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class CounterTileEntity extends TileEntity {

    private int count;

    public CounterTileEntity(TileEntityType<?> p_i48289_1_) {
        super(p_i48289_1_);
    }

    public CounterTileEntity() {
        this(milano.COUNTER_TE.get());
    }

    @Override
    public void load(BlockState state, CompoundNBT tags) {
        super.load(state, tags);

        count = tags.getInt("counter");

        System.out.println("load");
    }

    @Override
    public CompoundNBT save(CompoundNBT tags) {
        super.save(tags);

        tags.putInt("counter", count);

        System.out.println("save");

        return tags;
    }



}
