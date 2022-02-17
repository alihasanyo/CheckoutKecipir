package mobile.kecipir.com.checkout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mobile.kecipir.com.checkout.Adapter.ProductAdapter;
import mobile.kecipir.com.checkout.Adapter.VABankAdapter;
import mobile.kecipir.com.checkout.App.KecipirModule;
import mobile.kecipir.com.checkout.Model.ProductModel;
import mobile.kecipir.com.checkout.Model.VABankModel;

public class VABankActivity extends AppCompatActivity {

    private ListView listVA;
    private Activity activity;
    private ArrayList<VABankModel> mList;
    private VABankAdapter mAdapter;
    private KecipirModule mdl = new KecipirModule();

    //URL
    private static final String TAG_STATUS_CODE = "code";
    private static final String TAG_STATUS = "message";
    private static final String TAG_TRN = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vabank);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Metode Pembayaran");
        activity = this;

        listVA = findViewById(R.id.listVABank);

        listVA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final VABankModel m = mList.get(position);

                mdl.setVABank(activity, m.getTitle(), m.getImg());

                Intent va = new Intent(activity, MainActivity.class);
                startActivity(va);

            }
        });

        getVABank();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return false;
    }

    private void getVABank() {
        listVA.setVisibility(View.GONE);
        mList = new ArrayList<>();
        mAdapter = new VABankAdapter(activity, mList);
        listVA.setAdapter(mAdapter);

        String URL = mdl.getURLStr();

        JsonObjectRequest oGetBank = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.e("RESPONSE", response.toString());
                    listVA.setVisibility(View.VISIBLE);

                    String status_code = response.getString(TAG_STATUS_CODE);
                    if (status_code.equals("200")) {

                        String status = response.getString(TAG_STATUS);
                        if (status.equals("success")) {

                            JSONObject data = response.getJSONObject(TAG_TRN);
                            if (!data.toString().toUpperCase().equals("NULL")){

                                //list VA Bank
                                JSONArray arr = data.getJSONArray("payment_option");
                                if (arr.length() > 0) {
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject oList = arr.getJSONObject(i);

                                        String imgVA = oList.getString("img");
                                        String titleVA = oList.getString("name_va");

                                        mList.add(new VABankModel(imgVA, titleVA));

                                    }
                                }

                                mAdapter = new VABankAdapter(activity, mList);
                                mAdapter.notifyDataSetChanged();

                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VABankActivity.this, "Cobalah beberapa saat lagi", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(VABankActivity.this).add(oGetBank);
    }
}