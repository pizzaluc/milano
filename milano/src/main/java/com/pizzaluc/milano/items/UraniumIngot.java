package com.pizzaluc.milano.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class UraniumIngot extends Item {
    public UraniumIngot() {
        super(new Item.Properties()
                .tab(ItemGroup.TAB_MATERIALS)
                .stacksTo(64));
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn){
        ItemStack itemstack = player.getItemInHand(handIn);
        System.out.println("lol");
        return ActionResult.success(itemstack);
    }


}
