package com.besson.tutorial.entity.custom;

import com.besson.tutorial.entity.ModEntities;
import com.besson.tutorial.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ModChestBoat extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModChestBoat.class, EntityDataSerializers.INT);
    public ModChestBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }
    
    public ModChestBoat(Level level, double x, double y, double z) {
        this(ModEntities.MOD_CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, ModBoat.Type.ICE_ETHER.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        ModBoat.Type type = getModVariants();
        compound.putString("type", type.getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("type", 8)) {
            setVariant(ModBoat.Type.byName(compound.getString("type")));
        }
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariants()) {
            case ICE_ETHER -> ModItems.ICE_ETHER_CHEST_BOAT.get();
        };
    }

    public ModBoat.Type getModVariants() {
        return ModBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    public void setVariant(ModBoat.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }
}
