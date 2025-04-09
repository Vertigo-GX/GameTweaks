package vertigo.gametweaks.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vertigo.gametweaks.GameTweaks;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin {

	@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
	protected void randomTickInject(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {
		if (GameTweaks.CONFIG.disablePortalsSpawnPiglins) {
			info.cancel();
		}
	}

}