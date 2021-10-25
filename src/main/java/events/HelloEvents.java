package events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvents extends ListenerAdapter {                              // listener tudja figyelni az eventeket (üzeneteket)

    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String messageSent = event.getMessage().getContentRaw();                // üzenet tárolása Stringben
        String name = event.getMember().getUser().getName();
        if(messageSent.equalsIgnoreCase("!ping")){                  // ha az üzenet egyenlő megadott értékkel belép az if-be
            if (!event.getMember().getUser().isBot()){                          // csak akkor működik ha az üzenet küldője NEM bot
                event.getChannel().sendMessage("pong!").queue();           // kiírja az üzenetet
            }
        }
    }

}
