package com.zendesk.zccvho.ticketviewer;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class TicketDataDeserializer implements JsonDeserializer<TicketData> {

    @Override
    public TicketData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        TicketData data = new TicketData();

        Type listType = new TypeToken<List<Ticket>>(){}.getType();
        data.tickets = context.deserialize(jsonObject.get("tickets"), listType);
        data.nextPage = jsonObject.get("next_page").getAsString();
        data.total = jsonObject.get("count").getAsInt();


        return data;
    }
    
}
