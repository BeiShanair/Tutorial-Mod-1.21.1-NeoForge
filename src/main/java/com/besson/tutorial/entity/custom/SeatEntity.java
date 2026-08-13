package com.besson.tutorial.entity.custom;

import com.besson.tutorial.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SeatEntity extends Entity {
    public SeatEntity(Level level) {
        super(ModEntities.SEAT.get(), level);
        this.noPhysics = true;
    }
    
    private SeatEntity(Level level, BlockPos pos, double yOffset, Direction direction) {
        this(level);
        this.setPos(pos.getX() + 0.5, pos.getY() + yOffset, pos.getZ() + 0.5);
        this.setRot(direction.toYRot(), 0.0f);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {

    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            if (this.getPassengers().isEmpty() || this.level().isEmptyBlock(this.blockPosition())) {
                this.remove(RemovalReason.DISCARDED);
                this.level().updateNeighbourForOutputSignal(blockPosition(), this.level().getBlockState(blockPosition()).getBlock());
            }
        }
    }

    @Override
    public Vec3 getVehicleAttachmentPoint(Entity entity) {
        return Vec3.ZERO;
    }

    @Override
    protected boolean canRide(Entity vehicle) {
        return true;
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        Direction original = this.getDirection();
        Direction[] offsets = new Direction[]{original, original.getCounterClockWise(), original.getClockWise(), original.getOpposite()};
        for (Direction offset : offsets) {
            Vec3 safeVec = DismountHelper.findSafeDismountLocation(passenger.getType(), this.level(), this.blockPosition().relative(offset), false);
            if (safeVec != null) {
                return safeVec.add(0.0, 0.25, 0.0);
            }
        }
        return super.getDismountLocationForPassenger(passenger);
    }
    
    public static InteractionResult create(Level level, BlockPos pos, double yOffset, Player player, Direction direction) {
        if (!level.isClientSide()) {
            List<SeatEntity> seats = level.getEntitiesOfClass(SeatEntity.class, new AABB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0, pos.getY() + 1.0, pos.getZ() + 1.0));
            if (seats.isEmpty()) {
                SeatEntity seat = new SeatEntity(level, pos, yOffset, direction);
                level.addFreshEntity(seat);
                player.startRiding(seat, false);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        passenger.setYRot(this.getYRot());
    }

    @Override
    protected void positionRider(Entity passenger, MoveFunction callback) {
        super.positionRider(passenger, callback);
        this.clampYaw(passenger);
    }

    private void clampYaw(Entity passenger) {
        passenger.setYBodyRot(this.getYRot());
        float wrappedYaw = Mth.wrapDegrees(passenger.getYRot() - this.getYRot());
        float clampedYaw = Mth.clamp(wrappedYaw, -120.0f, 120.0f);
        passenger.yRotO += clampedYaw - wrappedYaw;
        passenger.setYRot(passenger.getYRot() + clampedYaw - wrappedYaw);
        passenger.setYHeadRot(passenger.getYRot());
    }

}
