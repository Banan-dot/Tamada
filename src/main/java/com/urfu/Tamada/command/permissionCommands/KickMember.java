package com.urfu.Tamada.command.permissionCommands;

import com.urfu.Tamada.command.Command;
import com.urfu.Tamada.events.CommandController;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Objects;

public class KickMember extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) {
        var mess = event.getMessage().getContentRaw().split(" ")[1];
        if (mess.equals("@everyone") && Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) {
            event.getChannel().getMembers().forEach(x ->
            {
                try { x.kick().queue();}
                catch (net.dv8tion.jda.api.exceptions.HierarchyException e) {
                    event.getChannel().sendMessage(e.getMessage()).queue();
                }
            });
        }
        else
            event.getChannel().sendMessage("Куда ты пишешь, дифиченто").queue();
        var pair = CommandController.getMemberFromEvent(event);
        var member = pair.getMember();
        var guild = pair.getGuild();
        System.out.println(event.getMessage().getContentRaw());
        if (Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)){
            Objects.requireNonNull(member)
                    .kick("Вы нам не понравились, обращайтесь в службу поддержи. До свидания!")
                    .queue();
        }
        else
            event.getChannel().sendMessage("Не делай так, пожалуйста!").queue();
    }
}
