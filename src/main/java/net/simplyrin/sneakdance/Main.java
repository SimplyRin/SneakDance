package net.simplyrin.sneakdance;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.simplyrin.sneakdance.command.CommandSneakDance;
import net.simplyrin.sneakdance.utils.SneakDance;

/**
 * Created by SimplyRin on 2018/08/09.
 *
 * Copyright (c) 2018 SimplyRin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
@Mod(modid = "SneakDance", version = "1.0")
public class Main {

	@Getter
	private SneakDance sneakDance;

	@Getter
	@Setter
	private boolean connected = false;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		ClientCommandHandler.instance.registerCommand(new CommandSneakDance(this));

		this.sneakDance = new SneakDance(this);
		this.sneakDance.run();
	}

	@SubscribeEvent
    public void onLogin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
		this.connected = true;
	}

	@SubscribeEvent
    public void onLogout(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
		this.connected = false;
	}

	public String getPrefix() {
		return "§7[§cSneakDance§7] §r";
	}

	public void sendMessage(String message) {
		message = message.replaceAll("&", "\u00a7");
		message = message.replaceAll("§", "\u00a7");

		Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(this.getPrefix() + message));
	}

}
