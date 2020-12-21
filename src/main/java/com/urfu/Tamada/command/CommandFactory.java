package com.urfu.Tamada.command;

import com.urfu.Tamada.command.alias.Alias;
import com.urfu.Tamada.command.ascii.Ascii;
import com.urfu.Tamada.command.ascii.AsciiLines;
import com.urfu.Tamada.command.crocodile.Crocodile;
import com.urfu.Tamada.command.permissionCommands.*;
import com.urfu.Tamada.command.permissionCommands.chatCommands.*;
import com.urfu.Tamada.command.permissionCommands.voiceCommands.VoiceMute;
import com.urfu.Tamada.command.permissionCommands.voiceCommands.VoiceUnmute;
import com.urfu.Tamada.command.translator.ListLanguages;
import com.urfu.Tamada.command.translator.SwitchLanguage;
import com.urfu.Tamada.command.translator.Translator;
import com.urfu.Tamada.command.translator.ViewLanguage;

public class CommandFactory {
    public Command getCommand(String commandName) {
        return switch (commandName.toLowerCase()) {
            case "bl" -> new ViewBanList();
            case "help" -> new HelpCommand();
            case "anec" -> new JokeCommand();
            case "mute" -> new VoiceMute();
            case "tr" -> new Translator();
            case "unmute" -> new VoiceUnmute();
            case "rename" -> new Rename();
            case "kick" -> new KickMember();
            case "mute_t" -> new ChatMute();
            case "ascii" -> new Ascii();
            case "sl" -> new SwitchLanguage();
            case "hd" -> new AsciiLines();
            case "p" -> new Ping();
            case "mm" -> new WriteMembers();
            case "cl" -> new ViewLanguage();
            case "ll" -> new ListLanguages();
            case "unmute_t" -> new ChatUnmute();
            case "addsmile" -> new AddNewEmoji();
            case "importemotes" -> new ImportEmotes();
            case "delete" -> new DeleteMessages();
            case "croc", "crocodile" -> new Crocodile();
            case "alias" -> new Alias();
            case "ban" -> new BanMember();
            case "unban" -> new UnBanMember();
            case "meme", "mem" -> new MemeCommand();
            case "panthers" -> new PanthersCommand();
            default -> new NoCommand();
        };
    }
}
