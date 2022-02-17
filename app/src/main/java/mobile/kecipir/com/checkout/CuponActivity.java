package mobile.kecipir.com.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mobile.kecipir.com.checkout.Adapter.CuponAdapter;
import mobile.kecipir.com.checkout.Adapter.DeliveryAdapter;
import mobile.kecipir.com.checkout.App.KecipirModule;
import mobile.kecipir.com.checkout.Model.CuponModel;
import mobile.kecipir.com.checkout.Model.VABankModel;

public class CuponActivity extends AppCompatActivity {

    private EditText et_cupon;
    private Button btn_cek_cupon;
    private ListView list_cupon;
    private Activity activity;
    private TextView tv_cek_kupon;
    private ArrayList<CuponModel> mList;
    private CuponAdapter mAdapter;
    private KecipirModule mdl = new KecipirModule();
    private String chooseCupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupon);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pakai Kupon");
        activity = this;

        et_cupon = findViewById(R.id.et_cupon);
        btn_cek_cupon = findViewById(R.id.btn_cek_cupon);
        list_cupon = findViewById(R.id.list_cupon);
        tv_cek_kupon = findViewById(R.id.tv_cek_kupon);

        tv_cek_kupon.setVisibility(View.GONE);

        mList = new ArrayList<>();
        mList.add(new CuponModel("CABAIMURAH", "valid sampai 31 Maret 2022", "Lihat Detail"));
        mList.add(new CuponModel("TAHUKECIPIR", "valid sampai 20 Maret 2022", "Lihat Detail"));
        mList.add(new CuponModel("TOGEMERICANG", "valid sampai 28 Februari 2022", "Lihat Detail"));
        mList.add(new CuponModel("BELISEPUASNYA", "valid sampai 01 Maret 2022", "Lihat Detail"));

        mAdapter = new CuponAdapter(activity, mList);
        list_cupon.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        list_cupon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CuponModel m = mList.get(position);

                switch (m.getTitle()){
                    case "CABAIMURAH" :
                        tv_cek_kupon.setVisibility(View.VISIBLE);
                        tv_cek_kupon.setTextColor(getResources().getColor(R.color.red_600));
                        tv_cek_kupon.setText("Kupon ini berlalu untuk pembelian cabai");
                        et_cupon.setText("");
                        chooseCupon = "None";
                        break;

                    case "TAHUKECIPIR" :
                        tv_cek_kupon.setVisibility(View.VISIBLE);
                        tv_cek_kupon.setTextColor(getResources().getColor(R.color.red_600));
                        tv_cek_kupon.setText("Kupon ini berlalu untuk pembelian tahu");
                        et_cupon.setText("");
                        chooseCupon = "None";
                        break;

                    case "TOGEMERICANG" :
                        tv_cek_kupon.setVisibility(View.VISIBLE);
                        tv_cek_kupon.setTextColor(getResources().getColor(R.color.red_600));
                        tv_cek_kupon.setText("Kupon ini berlalu untuk pembelian toge");
                        et_cupon.setText("");
                        chooseCupon = "None";
                        break;

                    case "BELISEPUASNYA" :
                        tv_cek_kupon.setVisibility(View.VISIBLE);
                        tv_cek_kupon.setTextColor(getResources().getColor(R.color.green_600));
                        tv_cek_kupon.setText("Selamat anda bisa pakai untuk hemat belanja");
                        et_cupon.setText(m.getTitle());
                        chooseCupon = m.getTitle();
                        break;

                    default:
                        break;
                }
            }
        });

        btn_cek_cupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mdl.setCupon(activity, chooseCupon);
                startActivity(new Intent(activity, MainActivity.class));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return false;
    }
}