package vertigo.gametweaks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vertigo.gametweaks.GameTweaks;

@Mixin(targets = "net.minecraft.world.entity.monster.EnderMan$EndermanTakeBlockGoal")
public abstract class PickUpBlockGoalMixin {

	@Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
	public void canStartInject(CallbackInfoReturnable<Boolean> info) {
		if(GameTweaks.CONFIG.disableEndermenPickUpBlocks) {
			info.setReturnValue(false);
		}
	}

}