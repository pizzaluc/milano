package com.pizzaluc.milano.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class UraniumItem extends Item {
    public UraniumItem() {
        super(new Item.Properties()
                .tab(ItemGroup.TAB_MATERIALS)
                .stacksTo(1));
    }

    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit){
        System.out.println("lol");
        return ActionResultType.PASS;
    }


}
