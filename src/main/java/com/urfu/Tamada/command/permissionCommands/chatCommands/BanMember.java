package com.urfu.Tamada.command.permissionCommands.chatCommands;

import com.urfu.Tamada.BanList;
import com.urfu.Tamada.Config;
import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class BanMember extends Command {
    private final String help = "Банит укзананого участника. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var pair = CommandController.getMemberFromEvent(event);
        var id = pair.getFirst().getIdLong();
        var guildId = event.getGuild().getIdLong();
        if (id == Objects.requireNonNull(event.getMember()).getIdLong()) {
            Sender.send(event, "Самобан? АХАХХАХАХА");
            return;
        }

        if (BanList.isInBanList(guildId, id))
            Sender.send(event, "Данный человек уже в бане");
        else if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR))
            if (!pair.getFirst().hasPermission(Permission.ADMINISTRATOR))
                BanList.addToBanList(event.getGuild().getIdLong(), id);
            else
                Sender.send(event, "Админа нельзя забанить");
        else
            Sender.send(event, "Не делай так, пожалуйста!");
    }
}
