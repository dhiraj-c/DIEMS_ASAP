package com.dietms.diemsasap;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendNotification
{
    public static final String FCM_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String SERVER_KEY = "AAAA172XlEw:APA91bEjDyx7piHS3ZWVhdXxHZeKIo2DBYmo4cC2aky3wY4qux_HhdxPRmdh8TmHLawTiQe3JGjS7ItZF__hLyrb7MvaCg9Uube75VHWVYp5bz6dIQgNrfT5P1HduTz43j-hzHcrS905";

    Context context;
    String title;
    String body;
    String token;
    RequestQueue requestQueue;

    public SendNotification(Context context, String title, String body, String token) {
        this.context = context;
        this.title = title;
        this.body = body;
        this.token = token;
        requestQueue = Volley.newRequestQueue(context);
    }

    private JSONObject getJsonObject(String title, String body, String token) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject notificationJson = new JSONObject();
        notificationJson.put("title", title);
        notificationJson.put("body", body);
        jsonObject.put("to", token);
        jsonObject.put("notification", notificationJson);
        return jsonObject;
    }

    public void sentToServer() throws JSONException {
        final JSONObject jsonObject = getJsonObject(title, body, token);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, FCM_URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization", "key=" + SERVER_KEY);
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        requestQueue.add(request);
    }

}
