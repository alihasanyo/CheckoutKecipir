package mobile.kecipir.com.checkout.Model;

public class CuponModel {

    String title, tgl, detail;

    public CuponModel(){

    }

    public CuponModel(String title, String tgl, String detail) {
        this.title = title;
        this.tgl = tgl;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getTgl() {
        return tgl;
    }

    public String getDetail() {
        return detail;
    }
}
