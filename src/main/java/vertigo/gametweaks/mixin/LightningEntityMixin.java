package vertigo.gametweaks.mixin;

import net.minecraft.world.entity.LightningBolt;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vertigo.gametweaks.GameTweaks;

@Mixin(LightningBolt.class)
public abstract class LightningEntityMixin {

	@Inject(method = "spawnFire", at = @At("HEAD"), cancellable = true)
	private void spawnFireInject(int spreadAttempts, CallbackInfo info) {
		if(GameTweaks.CONFIG.disableLightningStartsFires) {
			info.cancel();
		}
	}

}