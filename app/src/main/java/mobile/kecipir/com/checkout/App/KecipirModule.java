package mobile.kecipir.com.checkout.App;

import android.app.Activity;
import android.content.SharedPreferences;

import mobile.kecipir.com.checkout.R;

public class KecipirModule {

    //URL
    public final String getURLStr(){
        String url = "";

        try {
            url = "https://staging.kecipir.com/response_checkout.json";
        } catch (Exception e){
            //handle error
            e.printStackTrace();
        }finally {
            return url;
        }
    }

    //va bank
    public void setVABank(Activity activity, String va, String img){
        String pref_va = activity.getResources().getString(R.string.pref_VABank);
        SharedPreferences SP_va = activity.getSharedPreferences(pref_va, 0);
        SharedPreferences.Editor editor = SP_va.edit();
        editor.putString("va", va);
        editor.putString("img", img);
        editor.apply();
    }

    public String getVABank(Activity activity){
        String va;
        String pref_va = activity.getResources().getString(R.string.pref_VABank);
        SharedPreferences SP_va = activity.getSharedPreferences(pref_va, 0);
        va = SP_va.getString("va", "None");
        return va;
    }

    public String getIMGVABank(Activity activity){
        String img;
        String pref_img = activity.getResources().getString(R.string.pref_VABank);
        SharedPreferences SP_img = activity.getSharedPreferences(pref_img, 0);
        img = SP_img.getString("img", null);
        return img;
    }

    //date delivery
    public void setDateDelivery(Activity activity, String days, String date){
        String pref_dd = activity.getResources().getString(R.string.pref_DateDelivery);
        SharedPreferences SP_dd = activity.getSharedPreferences(pref_dd, 0);
        SharedPreferences.Editor editor = SP_dd.edit();
        editor.putString("day", days);
        editor.putString("date", date);
        editor.apply();
    }

    public String getDayDelivery(Activity activity){
        String day;
        String pref_day = activity.getResources().getString(R.string.pref_DateDelivery);
        SharedPreferences SP_day = activity.getSharedPreferences(pref_day, 0);
        day = SP_day.getString("day", "None");
        return day;
    }

    public String getDateDelivery(Activity activity){
        String date;
        String pref_date = activity.getResources().getString(R.string.pref_DateDelivery);
        SharedPreferences SP_date = activity.getSharedPreferences(pref_date, 0);
        date = SP_date.getString("date", "None");
        return date;
    }

    //cupon
    public void setCupon(Activity activity, String cupon){
        String pref_cp = activity.getResources().getString(R.string.pref_Cupon);
        SharedPreferences SP_cp = activity.getSharedPreferences(pref_cp, 0);
        SharedPreferences.Editor editor = SP_cp.edit();
        editor.putString("cupon", cupon);
        editor.apply();
    }

    public String getCupon(Activity activity){
        String cp;
        String pref_cp = activity.getResources().getString(R.string.pref_Cupon);
        SharedPreferences SP_cp = activity.getSharedPreferences(pref_cp, 0);
        cp = SP_cp.getString("cupon", "None");
        return cp;
    }

}
