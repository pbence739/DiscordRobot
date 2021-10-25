package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Random;

public class RollDice extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        Random random = new Random();
        String message = event.getMessage().getContentRaw();
        EmbedBuilder embed = new EmbedBuilder();

        if(message.charAt(0) == '!'){
            message = message.replaceAll("!", "");
            embed.setTitle(event.getMember().getUser().getName() + "'s roll");

                int dices, sides;
                int bonuses = 0;

                if (message.contains("+")){
                    if (message.charAt(0) == 'd') {
                        message = message.replaceAll("d", "");
                        String[] diceparams = message.split("[+]");
                        dices = 1;
                        sides = Integer.parseInt(diceparams[0]);
                        bonuses = Integer.parseInt(diceparams[1]);
                    } else {
                        String[] diceParam = message.split("[d+]");
                        dices = Integer.parseInt(diceParam[0]);
                        sides = Integer.parseInt(diceParam[1]);
                        bonuses = Integer.parseInt(diceParam[2]);
                    }
                } else if (message.contains("-")){
                    if (message.charAt(0) == 'd') {
                        message = message.replaceAll("d", "");
                        String[] diceparams = message.split("[-]");
                        dices = 1;
                        sides = Integer.parseInt(diceparams[0]);
                        bonuses = Integer.parseInt(diceparams[1]) * -1;
                    } else {
                        String[] diceParam = message.split("[d-]");
                        dices = Integer.parseInt(diceParam[0]);
                        sides = Integer.parseInt(diceParam[1]);
                        bonuses = Integer.parseInt(diceParam[2]) * -1;
                    }
                } else {
                    if (message.charAt(0) == 'd') {
                        message = message.replaceAll("d", "");
                        dices = 1;
                        sides = Integer.parseInt(message);
                    } else {
                        String[] diceParam = message.split("d");
                        dices = Integer.parseInt(diceParam[0]);
                        sides = Integer.parseInt(diceParam[1]);
                    }
                }

                int sum = 0;
                String rolls = "";

                for (int i = 0; i < dices; i++) {
                    int singleRoll = random.nextInt(sides) + 1;
                    sum += singleRoll;
                    if (i == dices -1 ){
                        rolls += "" + singleRoll;
                    } else {
                        rolls += "" + singleRoll + ", ";
                    }
                }

                if (dices  > 1){
                    embed.addField("Rolls: ", rolls, true);
                }
                if (bonuses == 0){
                    embed.addField("Result: ", "" + sum, false);
                } else if (bonuses > 0){
                    embed.addField("Result: ", "" + sum + " + " + bonuses + " = " + (bonuses+sum), false);
                } else {
                    embed.addField("Result: ", "" + sum + " - " + (bonuses*-1) + " = " + (bonuses+sum), false);
                }
                embed.setColor(Color.CYAN);

                event.getChannel().sendMessage(embed.build()).queue();
        }
    }
}