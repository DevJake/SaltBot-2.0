package me.Salt;


import me.Salt.Event.JGenericMessageEvent;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


/**
 * Created by Salt001 on 05/04/2017.
 */
public class EventDistributor extends ListenerAdapter {

    @Override
    public void onGenericMessage(GenericMessageEvent event) {
        JGenericMessageEvent.process(event);
    }

}