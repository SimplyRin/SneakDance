package net.simplyrin.sneakdance.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.simplyrin.sneakdance.Main;

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
public class CommandSneakDance extends CommandBase {

	private Main instance;

	public CommandSneakDance(Main instance) {
		this.instance = instance;
	}

	@Override
	public String getCommandName() {
		return "sneakdance";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "&cUsage: /sneakdance <boolean>";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if(args.length > 0) {
			if(args[0].equalsIgnoreCase("delay")) {
				if(args.length > 1) {
					try {
						int integer = Integer.valueOf(args[1]).intValue();
						this.instance.getSneakDance().setInterval(integer);
						this.instance.sendMessage("&aSneak interval was set to &b" + integer + "&a!");
						return;
					} catch (Exception e) {
					}
				}
				this.instance.sendMessage("&cUsage: /sneakdance delay <int>");
				return;
			}

			try {
				boolean bool = Boolean.valueOf(args[0]).booleanValue();
				this.instance.getSneakDance().setEnabled(bool);
				this.instance.sendMessage("&aSneakDance has been " + (bool ? "&aEnabled" : "&cDisabled") + "&a!");
				return;
			} catch (Exception e) {
			}
		}
		this.instance.sendMessage(this.getCommandUsage(null));
		return;
	}

}
