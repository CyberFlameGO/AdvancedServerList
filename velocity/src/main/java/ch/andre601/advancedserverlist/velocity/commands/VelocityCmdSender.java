/*
 * MIT License
 *
 * Copyright (c) 2022-2023 Andre_601
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
 *
 */

package ch.andre601.advancedserverlist.velocity.commands;

import ch.andre601.advancedserverlist.core.interfaces.CmdSender;
import ch.andre601.advancedserverlist.core.parsing.ComponentParser;
import com.velocitypowered.api.command.CommandSource;

public class VelocityCmdSender implements CmdSender{
    
    private final CommandSource sender;
    
    public VelocityCmdSender(CommandSource sender){
        this.sender = sender;
    }
    
    @Override
    public boolean hasPermission(String permission){
        return sender.hasPermission(permission) || sender.hasPermission("advancedserverlist.admin");
    }
    
    @Override
    public void sendMsg(String msg, Object... args){
        sender.sendMessage(ComponentParser.text(String.format(msg, args)).toComponent());
    }
}
