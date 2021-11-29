package com.zendesk.zccvho.ticketviewer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class TicketDeserializer implements JsonDeserializer<Ticket> {

    @Override
    public Ticket deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        Ticket t = new Ticket();
        t.assigneId = jsonObject.get("assignee_id").getAsString();
        t.createdAt = jsonObject.get("created_at").getAsString();
        // t.solved = jsonObject.get("solved").getAsBoolean();
        t.subject = jsonObject.get("subject").getAsString();
        t.description = jsonObject.get("description").getAsString();
        System.out.println(jsonObject.get("requester_id"));
        t.requesterId = jsonObject.get("requester_id").getAsString();
        t.status = jsonObject.get("status").getAsString();
        t.id = jsonObject.get("id").getAsInt();
        // t.type = jsonObject.get("type").getAsString();
        t.updatedAt = jsonObject.get("updated_at").getAsString();
        t.requesterId = jsonObject.get("requester_id").getAsString();

        return t;
    }
}
