package com.besson.tutorial.entity.custom;

import com.besson.tutorial.block.ModBlocks;
import com.besson.tutorial.entity.ModEntities;
import com.besson.tutorial.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.function.IntFunction;

public class ModBoat extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoat.class, EntityDataSerializers.INT);
    public ModBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }
    
    public ModBoat(Level level, double x, double y, double z) {
        this(ModEntities.MOD_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, Type.ICE_ETHER.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        ModBoat.Type type = getModVariants();
        compound.putString("type", type.getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("type", 8)) {
            setVariant(ModBoat.Type.byName(compound.getString("type")));
        }
    }

    @Override
    public Item getDropItem() {
        return switch (getModVariants()) {
            case ICE_ETHER -> ModItems.ICE_ETHER_BOAT.get();
        };
    }

    public ModBoat.Type getModVariants() {
        return ModBoat.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
    
    public void setVariant(ModBoat.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public enum Type implements StringRepresentable {
        ICE_ETHER("ice_ether", ModBlocks.ICE_ETHER_PLANKS.get());

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<ModBoat.Type> CODEC = StringRepresentable.fromEnum(ModBoat.Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        Type(String name, Block planks) {
            this.name = name;
            this.planks = planks;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public String getName() {
            return name;
        }

        public Block getPlanks() {
            return planks;
        }

        @Override
        public String toString() {
            return name;
        }
        
        public static ModBoat.Type byId(int id) {
            return BY_ID.apply(id);
        }
        
        public static ModBoat.Type byName(String name) {
            return CODEC.byName(name, ICE_ETHER);
        }
    }
}
