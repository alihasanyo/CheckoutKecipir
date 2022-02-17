package mobile.kecipir.com.checkout.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mobile.kecipir.com.checkout.Model.CuponModel;
import mobile.kecipir.com.checkout.Model.VABankModel;
import mobile.kecipir.com.checkout.R;

public class CuponAdapter extends ArrayAdapter<CuponModel> {

    private final Activity context;
    private ArrayList<CuponModel> mData;


    public CuponAdapter(Activity context,
                        ArrayList<CuponModel> data) {
        super(context, R.layout.item_list_cupon, data);
        this.context = context;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_list_cupon, null, true);
        rowView.setId(position);

        TextView titleCupon = rowView.findViewById(R.id.tv_title_kupon);
        TextView validCupon = rowView.findViewById(R.id.tv_valid_kupon);
        TextView detilCupon = rowView.findViewById(R.id.tv_cek_kupon);

        CuponModel m = mData.get(position);
        titleCupon.setText(m.getTitle());
        validCupon.setText(m.getTgl());
        detilCupon.setText(m.getDetail());

        return rowView;
    }
}
