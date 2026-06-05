package vertigo.gametweaks.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vertigo.gametweaks.GameTweaks;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin {

	@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
	protected void randomTickInject(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo info) {
		if(GameTweaks.CONFIG.disablePortalsSpawnPiglins) {
			info.cancel();
		}
	}

}