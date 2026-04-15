package me.alpha432.oyvey.mixin.world;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ClientLevel.class)
public interface IClientLevel {
    // This allows us to access the private list of tile entities
    @Accessor("blockEntitiesToTick")
    List<BlockEntity> getBlockEntities();
}
