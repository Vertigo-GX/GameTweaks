package vertigo.gametweaks;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {

	private static final int BUTTON_WIDTH = 310;

	private static final int BUTTON_HEIGHT = 20;

	private final Screen parent;

	private boolean modified = false;

	protected ConfigScreen(Screen parent) {
		super(Component.literal("game-tweaks.options"));
		this.parent = parent;
	}

	@Override
	protected void init() {
		HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
		layout.addToHeader(new StringWidget(Component.translatable("game-tweaks.text.optionsTitle"), this.font));
		GridLayout grid = new GridLayout();
		grid.rowSpacing(5);
		GridLayout.RowHelper adder = grid.createRowHelper(1);
		adder.addChild(createToggleButton("disableLightningStartsFires", GameTweaks.CONFIG.disableLightningStartsFires, b -> setToggleButtonMessage(b, "disableLightningStartsFires", GameTweaks.CONFIG.disableLightningStartsFires ^= true)));
		adder.addChild(createToggleButton("disablePortalsSpawnPiglins", GameTweaks.CONFIG.disablePortalsSpawnPiglins, b -> setToggleButtonMessage(b, "disablePortalsSpawnPiglins", GameTweaks.CONFIG.disablePortalsSpawnPiglins ^= true)));
		adder.addChild(createToggleButton("disableEndermenPickUpBlocks", GameTweaks.CONFIG.disableEndermenPickUpBlocks, b -> setToggleButtonMessage(b, "disableEndermenPickUpBlocks", GameTweaks.CONFIG.disableEndermenPickUpBlocks ^= true)));
		layout.addToContents(grid);
		layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, b -> onClose()).build());
		layout.visitWidgets(this::addRenderableWidget);
		layout.arrangeElements();
	}

	@Override
	public void onClose() {
		if(modified) {
			GameTweaks.CONFIG.write();
		}
		this.minecraft.setScreen(this.parent);
	}

	private Button createToggleButton(String key, boolean value, Button.OnPress action) {
		return Button.builder(CommonComponents.optionStatus(Component.translatable("game-tweaks.option." + key), value), action).tooltip(Tooltip.create(Component.translatable("game-tweaks.tooltip." + key))).size(BUTTON_WIDTH, BUTTON_HEIGHT).build();
	}

	private void setToggleButtonMessage(Button button, String key, boolean value) {
		button.setMessage(CommonComponents.optionStatus(Component.translatable("game-tweaks.option." + key), value));
		modified = true;
	}

}