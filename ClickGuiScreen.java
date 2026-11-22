package com.nova.client.gui;

import com.nova.client.module.BoolSetting;
import com.nova.client.module.Module;
import com.nova.client.module.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

/** Квадратное меню по центру экрана со всеми функциями. */
public class ClickGuiScreen extends Screen {
    private static final int W = 220;
    private static final int ROW = 22;

    private int panelX, panelY, panelH;

    public ClickGuiScreen() {
        super(Text.literal("NovaClient"));
    }

    @Override
    protected void init() {
        int rows = 0;
        for (Module m : ModuleManager.MODULES) rows += 1 + m.getSettings().size();
        panelH = 30 + rows * ROW + 6;
        panelX = (width - W) / 2;
        panelY = (height - panelH) / 2;

        int y = panelY + 28;
        for (Module m : ModuleManager.MODULES) {
            addDrawableChild(ButtonWidget.builder(label(m.getName(), m.isEnabled()), b -> {
                m.toggle();
                b.setMessage(label(m.getName(), m.isEnabled()));
            }).dimensions(panelX + 10, y, W - 20, 20).build());
            y += ROW;

            for (BoolSetting s : m.getSettings()) {
                addDrawableChild(ButtonWidget.builder(label("  " + s.getName(), s.get()), b -> {
                    s.toggle();
                    b.setMessage(label("  " + s.getName(), s.get()));
                }).dimensions(panelX + 30, y, W - 40, 20).build());
                y += ROW;
            }
        }
    }

    private static Text label(String name, boolean on) {
        return Text.literal(name + ": ")
                .append(Text.literal(on ? "ВКЛ" : "ВЫКЛ").formatted(on ? Formatting.GREEN : Formatting.RED));
    }

    @Override
    public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float delta) {
        super.renderBackground(ctx, mouseX, mouseY, delta);
        ctx.fill(panelX, panelY, panelX + W, panelY + panelH, 0xE0101018);
        ctx.drawBorder(panelX, panelY, W, panelH, 0xFF7B5CFF);
        ctx.drawCenteredTextWithShadow(textRenderer, title, panelX + W / 2, panelY + 10, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
