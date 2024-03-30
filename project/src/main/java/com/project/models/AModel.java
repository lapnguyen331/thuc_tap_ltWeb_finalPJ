package com.project.models;

import org.json.JSONObject;

public abstract class AModel implements IModel {
    public void insertLog(JSONObject json) {
        String ip = json.getString("ip");
    }
}
