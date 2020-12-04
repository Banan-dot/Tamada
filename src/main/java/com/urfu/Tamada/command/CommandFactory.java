package com.urfu.Tamada.command;

import com.urfu.Tamada.command.alias.Alias;
import com.urfu.Tamada.command.ascii.Ascii;
import com.urfu.Tamada.command.ascii.AsciiLines;
import com.urfu.Tamada.command.crocodile.Crocodile;
import com.urfu.Tamada.command.permissionCommands.*;

public class CommandFactory {
    public Command getCommand(String commandName)
    {
        System.out.println(commandName);
        switch (commandName.toLowerCase())
        {
            case "help":
                return new HelpCommand();
            case "анек":
                return new JokeCommand();
            case "mute":
                return new VoiceMute();
            case "unmute":
                return new VoiceUnmute();
            case "rename":
                return new Rename();
            case "kick":
                return new KickMember();
            case "mute_t":
                return new ChatMute();
            case "ascii":
                return new Ascii();
            case "hd":
                return new AsciiLines();
            case "mm":
                return new WriteMembers();
            case "unmute_t":
                return new ChatUnmute();
            case "addsmile":
                return new AddNewEmoji();
            case "importemotes":
                return new ImportEmotes();
            case "delete":
                return new DeleteMessages();
            case "croc":
            case "crocodile":
                return new Crocodile();
            case "alias":
                return new Alias();
            case "ban":
                return new BanMember();
            case "unban":
                return new UnBanMember();
            default:
                return null;
        }
    }
}
