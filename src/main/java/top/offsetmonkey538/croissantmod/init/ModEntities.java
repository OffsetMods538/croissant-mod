package top.offsetmonkey538.croissantmod.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import top.offsetmonkey538.croissantmod.entity.projectile.thrown.ThrownCroissantEntity;

import static top.offsetmonkey538.croissantmod.CroissantMod.id;

public final class ModEntities {
    private ModEntities() {

    }

    public static final EntityType<ThrownCroissantEntity> CROISSANT = register(
            FabricEntityTypeBuilder.<ThrownCroissantEntity>create(SpawnGroup.MISC, ThrownCroissantEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.15f))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build(),
            "croissant"
    );

    private static <T extends Entity> EntityType<T> register(EntityType<T> entityType, String name) {
        return Registry.register(Registries.ENTITY_TYPE, id(name), entityType);
    }

    public static void register() {
        // Registers entities by loading the class
    }
}
