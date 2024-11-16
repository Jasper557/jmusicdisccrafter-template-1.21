package de.jaspy.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import de.jaspy.JMusicDiscCrafter;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;

import javax.swing.text.AttributeSet;
import javax.swing.text.Style;

public class MusicDiscCrafterScreen extends HandledScreen<MusicDiscCrafterScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(JMusicDiscCrafter.MOD_ID, "textures/gui/music_disc_crafter_gui.png");

    private TextFieldWidget titleField;
    private TextFieldWidget linkField;
    private ButtonWidget createButton;

    public MusicDiscCrafterScreen(MusicDiscCrafterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        // Initialize title field
        this.titleField = new TextFieldWidget(textRenderer, x + 31, y + 16, 100, 14, Text.literal(""));
        this.titleField.setMaxLength(15);
        this.titleField.setFocusUnlocked(true);
        addDrawableChild(this.titleField);

        // Initialize link field
        linkField = new TextFieldWidget(textRenderer, x + 31, y + 34, 100, 14, Text.literal(""));
        linkField.setMaxLength(200);
        linkField.setFocusUnlocked(true);
        addDrawableChild(linkField);
        
        // Add create button
        this.createButton = ButtonWidget.builder(Text.literal("Create"), button -> {
            // Send packet to server to create disc
            this.handler.createDisc(titleField.getText(), linkField.getText());
        })
        .dimensions(x + 31, y + 52, 45, 17)
        .build();
        addDrawableChild(this.createButton);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.client.player.closeHandledScreen();
        }

        if (this.titleField.isActive()) {
            return this.titleField.keyPressed(keyCode, scanCode, modifiers);
        } else if (this.linkField.isActive()) {
            return this.linkField.keyPressed(keyCode, scanCode, modifiers);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.titleField.setFocused(false);
        this.linkField.setFocused(false);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        // Draw labels
        context.drawText(textRenderer, Text.literal("Title:"),
            x + 7, y + 18, 0x404040, false);
        context.drawText(textRenderer, Text.literal("Link:"),
            x + 8, y + 36, 0x404040, false);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
