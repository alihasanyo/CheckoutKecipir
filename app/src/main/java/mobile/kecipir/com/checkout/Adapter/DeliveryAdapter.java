package mobile.kecipir.com.checkout.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mobile.kecipir.com.checkout.App.KecipirModule;
import mobile.kecipir.com.checkout.Model.DeliveryModel;
import mobile.kecipir.com.checkout.Model.VABankModel;
import mobile.kecipir.com.checkout.R;

public class DeliveryAdapter extends ArrayAdapter<DeliveryModel> {

    private final Activity context;
    private ArrayList<DeliveryModel> mData;
    private KecipirModule mdl = new KecipirModule();


    public DeliveryAdapter(Activity context,
                           ArrayList<DeliveryModel> data) {
        super(context, R.layout.item_delivery, data);
        this.context = context;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_delivery, null, true);
        rowView.setId(position);

        RadioButton rb_vabank = rowView.findViewById(R.id.rb_datedeli);

        DeliveryModel m = mData.get(position);
        rb_vabank.setText(m.getDate());

        rb_vabank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                String[] detil_date1 = rb_vabank.getText().toString().split(",");
                String day = detil_date1[0];
                String date = detil_date1[1];
                mdl.setDateDelivery(context, day, date);
            }
        });

        return rowView;
    }
}
