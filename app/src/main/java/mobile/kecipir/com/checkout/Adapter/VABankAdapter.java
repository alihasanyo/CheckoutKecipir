package mobile.kecipir.com.checkout.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mobile.kecipir.com.checkout.Model.ProductModel;
import mobile.kecipir.com.checkout.Model.VABankModel;
import mobile.kecipir.com.checkout.R;

public class VABankAdapter extends ArrayAdapter<VABankModel> {

    private final Activity context;
    private ArrayList<VABankModel> mData;


    public VABankAdapter(Activity context,
                         ArrayList<VABankModel> data) {
        super(context, R.layout.item_va_bank, data);
        this.context = context;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_va_bank, null, true);
        rowView.setId(position);

        ImageView imgVA = rowView.findViewById(R.id.va_image);
        TextView titleVA = rowView.findViewById(R.id.va_title);


        VABankModel m = mData.get(position);
        Glide.with(context).load(m.getImg()).into(imgVA);
        titleVA.setText(m.getTitle());

        return rowView;
    }
}
