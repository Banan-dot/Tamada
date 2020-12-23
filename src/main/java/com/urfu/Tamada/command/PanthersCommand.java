package com.urfu.Tamada.command;

import com.urfu.Tamada.Sender;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

@CommandInformation(name = "panthers", information = "МЫ ПАНТЕРЫ РРРР")
public class PanthersCommand extends Command {
    @Override
    public void execute(GuildMessageReceivedEvent event) throws InterruptedException, IOException {
        var panthersText = """
                Я представляю отряд "Wild Panthers".\s
                Мы специализируемся на отыгрыше различных рп сценариев в контексте игры Arma 3.
                Принимаем активное участие во всех играх на данном проекте и не только.
                Наш отряд основан закаленными в боях командирами. В нашем отряде присутствуют опытные игроки, которые всегда помогут разобраться с возникшими трудностями.

                У нас есть:
                -Отряд для игры в Arma 3 во всех возможных режимах.
                -Дискорд сервер для общения и организации игр с соотрядниками.
                -Дружеская атмосфера
                -Удобный дискорд бот, с различным функционалом (Бота разработали мы)\s
                -Куча идей для отрядных ивентов
                -Возможность развития в различных специализациях

                Вы получите:
                -Статус игрока в отряде
                -Положительные эмоции от игр
                -Возможность развития как командного игрока
                -Красивый значок в TeamSpeak

                От вас:
                -Уравновешенность, адекватность и терпеливость
                -Желание играть
                Отряд активно развивается, приветствуем новых игроков!
                Подробная информация в дискорд канале""";
        Sender.send(event, panthersText);
    }
}
