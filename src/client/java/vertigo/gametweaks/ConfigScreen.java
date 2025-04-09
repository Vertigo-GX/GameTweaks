package vertigo.gametweaks;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ConfigScreen extends Screen {

	private static final int BUTTON_WIDTH = 310;
	private static final int BUTTON_HEIGHT = 20;

	private final Screen parent;

	private boolean modified = false;

	protected ConfigScreen(Screen parent) {
		super(Text.literal("game-tweaks.options"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		ThreePartsLayoutWidget layout = new ThreePartsLayoutWidget(this);
		layout.addHeader(new TextWidget(Text.translatable("game-tweaks.text.optionsTitle"), this.textRenderer));
		GridWidget grid = new GridWidget();
		grid.setRowSpacing(5);
		GridWidget.Adder adder = grid.createAdder(1);
		adder.add(createToggleButton("disableLightningStartsFires", GameTweaks.CONFIG.disableLightningStartsFires, b -> {
			setToggleButtonMessage(b, "disableLightningStartsFires", GameTweaks.CONFIG.disableLightningStartsFires ^= true);
		}));
		adder.add(createToggleButton("disablePortalsSpawnPiglins", GameTweaks.CONFIG.disablePortalsSpawnPiglins, b -> {
			setToggleButtonMessage(b, "disablePortalsSpawnPiglins", GameTweaks.CONFIG.disablePortalsSpawnPiglins ^= true);
		}));
		adder.add(createToggleButton("disableEndermenPickUpBlocks", GameTweaks.CONFIG.disableEndermenPickUpBlocks, b -> {
			setToggleButtonMessage(b, "disableEndermenPickUpBlocks", GameTweaks.CONFIG.disableEndermenPickUpBlocks ^= true);
		}));
		layout.addBody(grid);
		layout.addFooter(ButtonWidget.builder(ScreenTexts.DONE, b -> {
			close();
		}).build());
		layout.forEachChild(this::addDrawableChild);
		layout.refreshPositions();
	}

	@Override
	public void close() {
		if (modified) {
			GameTweaks.CONFIG.write();
		}
		this.client.setScreen(this.parent);
	}

	private ButtonWidget createToggleButton(String key, boolean value, ButtonWidget.PressAction action) {
		return ButtonWidget.builder(ScreenTexts.composeToggleText(Text.translatable("game-tweaks.option." + key), value), action).tooltip(Tooltip.of(Text.translatable("game-tweaks.tooltip." + key))).size(BUTTON_WIDTH, BUTTON_HEIGHT).build();
	}

	private void setToggleButtonMessage(ButtonWidget button, String key, boolean value) {
		button.setMessage(ScreenTexts.composeToggleText(Text.translatable("game-tweaks.option." + key), value));
		modified = true;
	}

}