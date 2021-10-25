package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Help extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        EmbedBuilder embed = new EmbedBuilder();

        String message = event.getMessage().getContentRaw();

        if (message.equalsIgnoreCase("!help") || message.equalsIgnoreCase("!h")){
            embed.setColor(Color.white);
            embed.setTitle("DiceRoller helper");
            embed.addField("Dice roller", "This is the basic function of this bot. You can roll dices, with different sides and even add modifiers to them.\nExamples: !d10, !4d4+1, !d20+8, !10d100-10, etc...", false);
            embed.addField("Shadowrun roller", "With this function you can roll 6 sided dices and the bot counts you successes and failures.\nExample: !sr 1, !sr 4, !sr 12, etc...", false);
            embed.addField("Character roller", "With this function you can create the 6 basic attributes of a character. The character generation based on DnD D20 system.\nCommand: !create", false);
        }

        event.getChannel().sendMessage(embed.build()).queue();
    }
}
