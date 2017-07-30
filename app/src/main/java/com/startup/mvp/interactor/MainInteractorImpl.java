package com.startup.mvp.interactor;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.startup.mvp.AppController;
import com.startup.mvp.interfaces.main.MainInteractor;
import com.startup.mvp.interfaces.main.MainPresenter;
import com.startup.mvp.model.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Harish Penta on 24-7-2017.
 */
public class MainInteractorImpl implements MainInteractor {
    static final String URL = "https://api.seemora.com/General/GetGeneralData";
    //static final String URL = "https://api.seemora.com/General/GetGeneralData";
    int socketTimeout = 300000; // 30 seconds. You can change it
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    private List<Photo> result = new ArrayList<>();
    private MainPresenter presenter;


    public MainInteractorImpl(MainPresenter presenter) {

        this.presenter = presenter;
    }

    @Override
    public void getAllData() {


        StringRequest strSendFees = new StringRequest(StringRequest.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                VolleyLog.d("Ver", "Version : " + response);
                if (response != "") {

                    try {

                        JSONObject objmain = new JSONObject(response);

                        JSONArray array = objmain.getJSONArray("banners");
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object = array.getJSONObject(i);

                            String img = "https://mm.seemora.com/uploads/cat_banner/" + object.getString("image_name");

                            Photo p = new Photo();
                            p.setThumbnailUrl(img);

                            result.add(p);
                        }
                        presenter.onSucessDataLoad(result);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("dff", "FeesDetailsError : " + error.getMessage());
                String json = null;
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {
                    switch (response.statusCode) {
                        case 500:
                            json = new String(response.data);
                            json = trimMessage(json, "message");
                            if (json != null) displayMessage(json);
                            break;
                        case 405:
                            json = new String(response.data);
                            json = trimMessage(json, "message");
                            if (json != null) displayMessage(json);
                            break;
                    }
                    //Additional cases
                }
                //  Snackbar.make(coordinatorLayout,"Fees Details not sync",Snackbar.LENGTH_SHORT).show();
            }

            public String trimMessage(String json, String key) {
                String trimmedString = null;

                try {
                    JSONObject obj = new JSONObject(json);
                    trimmedString = obj.getString(key);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }

                return trimmedString;
            }

            //Somewhere that has access to a context
            public void displayMessage(String toastString) {
                Log.d("RESPONSE-ERROR", toastString);
            }
        });
        strSendFees.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(strSendFees);

        /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        //Call<PhotoList> call = apiService.getAllPhotos();

        Call<List<Photo>> call = apiService.getAllPhotos();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                Log.d("onResponse: ", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });*/


        //https://api.seemora.com/Push_Notification/Version?version=1.1

    }
}
