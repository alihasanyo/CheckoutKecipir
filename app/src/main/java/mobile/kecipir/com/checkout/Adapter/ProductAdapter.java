package mobile.kecipir.com.checkout.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import mobile.kecipir.com.checkout.MainActivity;
import mobile.kecipir.com.checkout.Model.ProductModel;
import mobile.kecipir.com.checkout.R;

public class ProductAdapter extends ArrayAdapter<ProductModel> {

    private final Activity context;
    private ArrayList<ProductModel> mData;
    private int qty;


    public ProductAdapter(Activity context,
                          ArrayList<ProductModel> data) {
        super(context, R.layout.item_list_product, data);
        this.context = context;
        this.mData = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_list_product, null, true);
        rowView.setId(position);

        TextView store_name = rowView.findViewById(R.id.store_name);
        ImageView iv_product = rowView.findViewById(R.id.iv_product);
        TextView tv_disc = rowView.findViewById(R.id.tv_disc);
        TextView tv_titleProduct = rowView.findViewById(R.id.tv_titleProduct);
        TextView tv_priceProduct = rowView.findViewById(R.id.tv_priceProduct);
        TextView tv_unitProduct = rowView.findViewById(R.id.tv_unitProduct);
        TextView tv_stockProduct = rowView.findViewById(R.id.tv_stockProduct);
        ImageView delete = rowView.findViewById(R.id.delete);
        ImageView iv_kurangi = rowView.findViewById(R.id.iv_kurangi);
        TextView tv_jml = rowView.findViewById(R.id.tv_jml);
        ImageView iv_tambahi = rowView.findViewById(R.id.iv_tambahi);

        ProductModel m = mData.get(position);
        store_name.setText(m.getStoreProduct());
        Glide.with(context).load(m.getImgProduct()).into(iv_product);
        tv_disc.setText(m.getDiscountProduct());
        tv_titleProduct.setText(m.getTitleProduct());
        tv_priceProduct.setText(m.getPriceProduct());
        tv_unitProduct.setText(m.getUnitProduct());
        tv_stockProduct.setText("Stok " + m.getStockProduct());

        qty = getItem(position).getQtyProduk();

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "yang dihapus adalah product " + getItem(position).getTitleProduct(), Toast.LENGTH_SHORT).show();
            }
        });

        iv_tambahi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty += 1;
                tv_jml.setText(String.valueOf(qty));
            }
        });

        iv_kurangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qty -= 1;
                if (qty == 0){
                    qty += 1;
                }
                tv_jml.setText(String.valueOf(qty));
            }
        });

        return rowView;
    }
}
