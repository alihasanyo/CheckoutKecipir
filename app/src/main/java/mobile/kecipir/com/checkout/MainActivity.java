package mobile.kecipir.com.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mobile.kecipir.com.checkout.Adapter.DeliveryAdapter;
import mobile.kecipir.com.checkout.Adapter.ProductAdapter;
import mobile.kecipir.com.checkout.App.KecipirModule;
import mobile.kecipir.com.checkout.Model.DeliveryModel;
import mobile.kecipir.com.checkout.Model.ProductModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_total_price, iv_VA;
    private Activity activity;
    private TextView note_product, info_cekout, infocekout_ya, delivery1, delivery2, ongkir_price, tv_VA;
    private LinearLayout ly_noted, lyCekout;
    private EditText et_noted;
    private View divider1;
    private ListView list_product;
    private ArrayList<ProductModel> mList;
    private ProductAdapter mAdapter;
    private KecipirModule mdl = new KecipirModule();
    private TextView deliv_address;
    private TextView kemasan1, kemasan2, kemasan3;
    private TextView tvcancel_kemasan;
    private LinearLayout lay_cancel_kemasan, lay_back_kemasan;
    private TextView price_total_produk, price_disc_produk, price_deliv_produk, price_kemasan_produk, price_total_pay;
    private String price_total, price_disc, price_deliv, price_kemasan, price_pay;
    private LinearLayout lay_disc;
    private TextView tv_total_price;
    private ImageView iv_tambah_belanja;
    private LinearLayout btn_bayar, lay_va, lay_va1;
    private String cekTitleVA;
    private Dialog dialog;
    private LinearLayout detil_calendar, lay_cupon;
    private TextView datedelivery;

    private ArrayList<DeliveryModel> mListDate;
    private DeliveryAdapter mAdapterDelivery;

    private TextView tv_no_cupon, typeCupon, qty_kupon;
    private LinearLayout lay_ada_kupon;
    private String cekCupontype;

    private String cekDeliveri;

    //URL
    private static final String TAG_STATUS_CODE = "code";
    private static final String TAG_STATUS = "message";
    private static final String TAG_TRN = "data";
    private static final String TAG_LIST = "summary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pesanan dan Pembayaran");
        activity = this;

        divider1 = findViewById(R.id.divider1);
        info_cekout = findViewById(R.id.tv_infoCekout);
        infocekout_ya = findViewById(R.id.infocekout_ya);
        lyCekout = findViewById(R.id.ly_info_cekout);

        note_product = findViewById(R.id.create_note);
        ly_noted = findViewById(R.id.ly_noted);
        et_noted = findViewById(R.id.et_noted);
        list_product = findViewById(R.id.list_product);
        iv_tambah_belanja = findViewById(R.id.iv_tambah_belanja);

        deliv_address = findViewById(R.id.deliv_address);
        delivery1 = findViewById(R.id.delivery1);
        delivery2 = findViewById(R.id.delivery2);
        ongkir_price = findViewById(R.id.ongkir_price);
        lay_va = findViewById(R.id.lay_va);
        lay_va1 = findViewById(R.id.lay_va1);
        detil_calendar = findViewById(R.id.detil_calendar);
        datedelivery = findViewById(R.id.datedelivery);

        kemasan1 = findViewById(R.id.kemasan1);
        kemasan2 = findViewById(R.id.kemasan2);
        kemasan3 = findViewById(R.id.kemasan3);

        tvcancel_kemasan = findViewById(R.id.tvcancel_kemasan);
        lay_cancel_kemasan = findViewById(R.id.ly_cancel_kemasan);
        lay_back_kemasan = findViewById(R.id.ly_back_kemasan);

        tv_total_price = findViewById(R.id.tv_total_price);
        iv_total_price = findViewById(R.id.iv_total_price);

        btn_bayar = findViewById(R.id.btn_bayar);

        iv_VA = findViewById(R.id.iv_VA);
        tv_VA = findViewById(R.id.tv_VA);

        lay_cupon = findViewById(R.id.lay_cupon);
        tv_no_cupon = findViewById(R.id.tv_no_cupon);
        lay_ada_kupon = findViewById(R.id.lay_ada_kupon);
        typeCupon = findViewById(R.id.typeCupon);
        qty_kupon = findViewById(R.id.qty_kupon);

        //cek va bank
        cekTitleVA = mdl.getVABank(this);
        try {
            if (cekTitleVA.equals("None"))
            {
                lay_va1.setVisibility(View.VISIBLE);
                lay_va.setVisibility(View.GONE);
                iv_VA.setImageResource(R.drawable.check_circle_icon);
                tv_VA.setText("Pilih metode pembayaran");

            } else {
                lay_va1.setVisibility(View.GONE);
                lay_va.setVisibility(View.VISIBLE);
                Glide.with(this).load(mdl.getIMGVABank(this)).into(iv_VA);
                tv_VA.setText(mdl.getVABank(this));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // End

        //cek kupon
        cekCupontype = mdl.getCupon(this);
        try {
            if (cekCupontype.equals("None"))
            {
                tv_no_cupon.setVisibility(View.VISIBLE);
                lay_ada_kupon.setVisibility(View.GONE);

            } else {
                tv_no_cupon.setVisibility(View.GONE);
                lay_ada_kupon.setVisibility(View.VISIBLE);
                typeCupon.setText(cekCupontype);
                qty_kupon.setText("1 kupon dipakai");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // End

        note_product.setOnClickListener(this);
        iv_total_price.setOnClickListener(this);
        info_cekout.setOnClickListener(this);
        iv_tambah_belanja.setOnClickListener(this);
        ly_noted.setVisibility(View.GONE);

        lay_cancel_kemasan.setVisibility(View.GONE);
        lay_back_kemasan.setOnClickListener(this);
        tvcancel_kemasan.setOnClickListener(this);
        lay_va.setOnClickListener(this);
        lay_va1.setOnClickListener(this);

        btn_bayar.setOnClickListener(this);
        detil_calendar.setOnClickListener(this);

        lay_cupon.setOnClickListener(this);

        getProduct();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_note :
                ly_noted.setVisibility(View.VISIBLE);
                visibleNoted();
                break;

            case R.id.iv_tambah_belanja :
                Toast.makeText(activity, "icon ini untuk tambah produk yang ingin dibeli", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_total_price :
                BottomSheetDialog btm_sheet_price = new BottomSheetDialog(MainActivity.this);
                btm_sheet_price.setContentView(R.layout.bottom_sheet_price_layout);
                btm_sheet_price.setCanceledOnTouchOutside(false);

                price_total_produk = btm_sheet_price.findViewById(R.id.total_produk);
                price_disc_produk = btm_sheet_price.findViewById(R.id.disc_produk);
                price_deliv_produk = btm_sheet_price.findViewById(R.id.deliv_produk);
                price_kemasan_produk = btm_sheet_price.findViewById(R.id.kemasan_produk);
                price_total_pay = btm_sheet_price.findViewById(R.id.total_pay);
                lay_disc = btm_sheet_price.findViewById(R.id.lay_disc_produk);

                if (price_disc.length() == 0){
                    lay_disc.setVisibility(View.GONE);
                }
                price_total_produk.setText(price_total);
                price_deliv_produk.setText(price_deliv);
                price_kemasan_produk.setText(price_kemasan);
                price_total_pay.setText(price_pay);

                btm_sheet_price.show();
                break;

            case R.id.tv_infoCekout:
                lyCekout.setVisibility(View.GONE);
                divider1.setVisibility(View.GONE);
                break;

            case R.id.ly_back_kemasan:
                lay_back_kemasan.setVisibility(View.GONE);
                visibleCancelKemasan();
                break;

            case R.id.tvcancel_kemasan:
                lay_back_kemasan.setVisibility(View.VISIBLE);
                lay_cancel_kemasan.setVisibility(View.GONE);
                break;

            case R.id.lay_va :
            case R.id.lay_va1 :
                startActivity(new Intent(activity, VABankActivity.class));
                break;

            case R.id.btn_bayar :
                Toast.makeText(activity, "total pembayaran sebesar " + price_pay, Toast.LENGTH_SHORT).show();
                break;

            case R.id.detil_calendar :
                dialogPilihTanggal();
                break;

            case R.id.lay_cupon :
                startActivity(new Intent(activity, CuponActivity.class));
                break;

            default:
                break;
        }
    }

    private void dialogPilihTanggal() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_date_delivery);
        dialog.setCancelable(false);
        dialog.show();

        ListView list_date = dialog.findViewById(R.id.list_date);
        LinearLayout lay_close = dialog.findViewById(R.id.lay_close);

        mListDate = new ArrayList<>();

        /*Calendar cldr_date = Calendar.getInstance();
        //Date today = cldr.getTime();
        Date tomorrow = null;
        Date tomorrow1 = null;
        int i;
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("EEEE");

        for (i = 0; i < 9; i++){
            cldr_date.add(Calendar.DAY_OF_WEEK_IN_MONTH, i);

            tomorrow = cldr_date.getTime();
            String date = df.format(tomorrow);

            tomorrow1 = cldr_date.getTime();
            String day = df1.format(tomorrow1);

            mListDate.add(new DeliveryModel(day + ", " + date));
        }*/
        mListDate.add(new DeliveryModel("Selasa, 05 Februari 2022"));
        mListDate.add(new DeliveryModel("Rabu, 06 Februari 2022"));
        mListDate.add(new DeliveryModel("Kamis, 07 Februari 2022"));
        mListDate.add(new DeliveryModel("Jumat, 08 Februari 2022"));
        mListDate.add(new DeliveryModel("Sabtu, 09 Februari 2022"));
        mListDate.add(new DeliveryModel("Senin, 11 Februari 2022"));
        mListDate.add(new DeliveryModel("Selasa, 12 Februari 2022"));
        mListDate.add(new DeliveryModel("Rabu, 13 Februari 2022"));
        mListDate.add(new DeliveryModel("Kamis, 14 Februari 2022"));

        mAdapterDelivery = new DeliveryAdapter(activity, mListDate);
        list_date.setAdapter(mAdapterDelivery);
        mAdapterDelivery.notifyDataSetChanged();

        lay_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

                //cek delivery
                cekDeliveri = mdl.getDateDelivery(MainActivity.this);
                try {
                    if (cekDeliveri.equals("None") || cekDeliveri.isEmpty())
                    {
                        datedelivery.setText("Pilih tanggal pengiriman");
                    } else {
                        datedelivery.setText(mdl.getDayDelivery(MainActivity.this) + ", " + mdl.getDateDelivery(MainActivity.this));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                // End
            }
        });

    }

    private void visibleNoted() {
        if (ly_noted.getVisibility() == View.VISIBLE){
            note_product.setVisibility(View.GONE);
            et_noted.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private void visibleCancelKemasan() {
        if (lay_back_kemasan.getVisibility() == View.GONE){
            lay_cancel_kemasan.setVisibility(View.VISIBLE);
        }
    }

    private void getProduct() {
        list_product.setVisibility(View.GONE);
        mList = new ArrayList<>();
        mAdapter = new ProductAdapter(activity, mList);
        list_product.setAdapter(mAdapter);

        String URL = mdl.getURLStr();

        JsonObjectRequest ogetProduct = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Log.e("RESPONSE", response.toString());
                    list_product.setVisibility(View.VISIBLE);

                    String status_code = response.getString(TAG_STATUS_CODE);
                    if (status_code.equals("200")) {

                        String status = response.getString(TAG_STATUS);
                        if (status.equals("success")) {

                            JSONObject data = response.getJSONObject(TAG_TRN);
                            if (!data.toString().toUpperCase().equals("NULL")){

                                //info checkout
                                String infoCekout = data.getString("info");

                                cekTitleVA = mdl.getVABank(MainActivity.this);
                                if (cekTitleVA.equals("None"))
                                {
                                    infocekout_ya.setText("Ups, masih ada yang belum sesuai. Cek lagi yuk");
                                    infocekout_ya.setTextColor(getResources().getColor(R.color.red_600));
                                    lyCekout.setBackgroundColor(getResources().getColor(R.color.orange_100));
                                } else {
                                    infocekout_ya.setText(infoCekout);
                                    lyCekout.setBackgroundColor(getResources().getColor(R.color.green_100));
                                }

                                //Produk dibeli
                                JSONArray arr = data.getJSONArray(TAG_LIST);
                                if (arr.length() > 0) {
                                    for (int i = 0; i < arr.length(); i++) {
                                        JSONObject oList = arr.getJSONObject(i);

                                        String store_prod = oList.getString("farmer");
                                        String img_prod = oList.getString("photo");
                                        String disc_prod = oList.getString("discount");
                                        String title_prod = oList.getString("title");
                                        String price_prod = oList.getString("price_sell_text");
                                        String unit_prod = oList.getString("unit");
                                        String stock_prod = oList.getString("stock_text");
                                        int qty_prod = oList.getInt("qty");

                                        if (disc_prod.equals("0%")) {
                                            disc_prod = "";
                                            price_disc = "";
                                        }

                                        mList.add(new ProductModel(title_prod, img_prod, store_prod, disc_prod, stock_prod, unit_prod, price_prod, qty_prod));

                                    }
                                }

                                mAdapter = new ProductAdapter(activity, mList);
                                mAdapter.notifyDataSetChanged();

                                //Pengiriman dan Pembayaran
                                JSONObject address = data.getJSONObject("address");
                                String detil_address = address.getString("address");
                                deliv_address.setText(detil_address);

                                JSONArray delivery = data.getJSONArray("delivery");
                                if (delivery.length() > 0){
                                    for (int j = 0; j < delivery.length(); j++){
                                        JSONObject list_delivery = delivery.getJSONObject(j);

                                        String deliv1 = list_delivery.getString("delivery_name");
                                        String deliv2 = list_delivery.getString("ket");
                                        String biaya_ongkir = list_delivery.getString("free_text");
                                        delivery1.setText(deliv1);
                                        delivery2.setText(deliv2);
                                        ongkir_price.setText(biaya_ongkir);
                                    }
                                }

                                //Kemasan
                                JSONArray kemasan = data.getJSONArray("packaging");
                                if (kemasan.length() > 0){
                                    for (int k = 0; k < kemasan.length(); k++){
                                        JSONObject list_kemasan = kemasan.getJSONObject(k);

                                        String title = list_kemasan.getString("name");
                                        String subtitle = list_kemasan.getString("ket");
                                        String btn_text = list_kemasan.getString("button_text");
                                        kemasan1.setText(title);
                                        kemasan2.setText(subtitle);
                                        kemasan3.setText(btn_text);
                                    }
                                }

                                //total pembayaran
                                JSONArray tot_pay = data.getJSONArray("bill");
                                if (tot_pay.length() > 0){
                                    for (int p = 0; p < tot_pay.length(); p++){
                                        JSONObject list_payment = tot_pay.getJSONObject(p);

                                        String title_prod = list_payment.getString("title");
                                        String total_prod = list_payment.getString("total");

                                        if (title_prod.contains("Total Harga")){
                                            price_total = total_prod;
                                        }
                                        if (title_prod.contains("Pengiriman")){
                                            price_deliv = total_prod;
                                        }
                                        if (title_prod.contains("Kemasan")){
                                            price_kemasan = total_prod;
                                        }

                                    }
                                }

                                JSONObject tot_pay1 = data.getJSONObject("netto");
                                price_pay = tot_pay1.getString("total");

                                if (cekTitleVA.equals("None") || cekTitleVA.length() == 0)
                                {
                                    tv_total_price.setText("-");
                                    iv_total_price.setVisibility(View.GONE);
                                } else {
                                    tv_total_price.setText(price_pay);
                                    iv_total_price.setVisibility(View.VISIBLE);
                                }

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
                Toast.makeText(MainActivity.this, "Cobalah beberapa saat lagi", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(MainActivity.this).add(ogetProduct);

    }

}