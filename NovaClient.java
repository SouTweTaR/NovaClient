package com.nova.client;

import com.nova.client.gui.ClickGuiScreen;
import com.nova.client.module.Module;
import com.nova.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NovaClient implements ClientModInitializer {
    private static KeyBinding guiKey;

    @Override
    public void onInitializeClient() {
        // Клавиша меню по умолчанию — правый Shift (меняется в Настройки → Управление)
        guiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.novaclient.gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.novaclient"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (guiKey.wasPressed()) {
                if (mc.currentScreen == null) {
                    mc.setScreen(new ClickGuiScreen());
                }
            }
            for (Module m : ModuleManager.MODULES) {
                if (m.isEnabled()) m.onTick(mc);
            }
        });
    }
}
