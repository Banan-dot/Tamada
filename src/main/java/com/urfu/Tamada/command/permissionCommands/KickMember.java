package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.Sender;
import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class KickMember extends Command {

    private final String help = "Кикает указанного мембера. Если у вас есть для этого права, конечно.";

    @Override
    public void getHelp(GuildMessageReceivedEvent event) {
        Sender.send(event, help);
    }

    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var mess = event.getMessage().getContentRaw().split(" ")[1];
        if (mess.equals("@everyone") && Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
            event.getChannel().getMembers().forEach(x -> {
                try {
                    if (!x.hasPermission(Permission.ADMINISTRATOR))
                        x.kick().queue();
                    else {
                        var nick = x.getNickname();
                        if (nick == null)
                            nick = x.getEffectiveName();
                        Sender.send(event,
                                "Такого человечка (" + nick+ ") нельзя кикнуть, так как он является администратором.");
                    }
                }
                catch (net.dv8tion.jda.api.exceptions.HierarchyException e) {
                    Sender.send(event, e.getMessage());
                }
            });
        }
        else
            Sender.send(event, "Прав нет у тебя, друг.");
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getMember();
        System.out.println(event.getMessage().getContentRaw());
        if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)){
            Objects.requireNonNull(member)
                    .kick("Вы нам не понравились, обращайтесь в службу поддержи. До свидания!")
                    .queue();
            Sender.send(event,
                    "Пользователь" + member.getEffectiveName() + "кикнут.");
        }
        else
            Sender.send(event, "Не делай так, пожалуйста!");
    }
}
