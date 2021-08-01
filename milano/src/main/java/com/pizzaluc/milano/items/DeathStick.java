package com.pizzaluc.milano.items;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.function.Predicate;

public class DeathStick extends Item {
    public DeathStick() {
        super(new Item.Properties()
                .tab(ItemGroup.TAB_COMBAT)
                .stacksTo(1));
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn){
        ItemStack itemStack = player.getItemInHand(handIn);

        Vector3d viewVector = player.getViewVector(0).scale(10);
        Vector3d playerPos = player.position().add(new Vector3d(0,1.7,0));
        Vector3d ray = playerPos.add(viewVector).subtract(new Vector3d(0, 0.7,0));
        RayTraceResult blockHit = worldIn.clip(new RayTraceContext(playerPos, ray, RayTraceContext.BlockMode.VISUAL, RayTraceContext.FluidMode.NONE, player));
        if (blockHit.getType() != RayTraceResult.Type.MISS) {
            ray = blockHit.getLocation();
        }

        EntityRayTraceResult entityHit = ProjectileHelper.getEntityHitResult(worldIn, player, playerPos, ray, player.getBoundingBox().expandTowards(viewVector).inflate(1.0D), (e) -> true);
        if (entityHit != null) {
            entityHit.getEntity().hurt(DamageSource.playerAttack(player), 10);
        }

        if(worldIn.isClientSide) {
            System.out.println("got here");
            for(double i = 0.2; i <= 1; i+=0.1){
                spawnParticles(new Vector3d(
                        MathHelper.lerp(i, playerPos.x, ray.x), MathHelper.lerp(i, playerPos.y, ray.y), MathHelper.lerp(i, playerPos.z, ray.z))
                        , worldIn);
            }
        }

        return ActionResult.success(itemStack);
    }

    @OnlyIn(Dist.CLIENT)
    public void spawnParticles (Vector3d pos, World world) {
        world.addParticle(ParticleTypes.CLOUD, pos.x, pos.y, pos.z, 0.0D, 0.0D, 0.0D);
    }

}
