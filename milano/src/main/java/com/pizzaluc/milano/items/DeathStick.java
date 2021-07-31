package com.pizzaluc.milano.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

public class DeathStick extends Item {
    public DeathStick() {
        super(new Item.Properties()
                .tab(ItemGroup.TAB_COMBAT)
                .stacksTo(1));
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn){
        ItemStack itemstack = player.getItemInHand(handIn);

        Vector3d playerPos = player.getPosition(0.5f);

        System.out.println(player.getViewVector(0) + ", " + player.getViewVector(0.5f) + ", " + player.getViewVector(1));

        EntityRayTraceResult hit = ProjectileHelper.getEntityHitResult(worldIn, player, playerPos, playerPos.add(player.getViewVector(0f).scale(5)),
                new AxisAlignedBB(playerPos.x - 5, playerPos.y -5, playerPos.z -5,
                        playerPos.x +5, playerPos.y +5, playerPos.z +5), (e) -> true);
        if(hit != null) {
            System.out.println("hit type " + hit.getType());
            if(hit.getType() == RayTraceResult.Type.ENTITY){
                hit.getEntity().hurt(DamageSource.playerAttack(player), 10);
            }
        }


        return ActionResult.success(itemstack);
    }
}
