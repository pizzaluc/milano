package com.pizzaluc.milano.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class Gun extends Item {

    public Gun() {
        super(new Item.Properties()
                .tab(ItemGroup.TAB_COMBAT)
                .stacksTo(1));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
        ItemStack itemStack = player.getItemInHand(handIn);

        ArrowItem projectile = (ArrowItem) Items.ARROW;

        AbstractArrowEntity arrowEntity = projectile.createArrow(world, new ItemStack(Items.ARROW), player);
        arrowEntity.shootFromRotation(player, player.xRot, player.yRot, 0f, 3, 1);

        world.addFreshEntity(arrowEntity);
        return ActionResult.success(itemStack);
    }
}
