package mobile.kecipir.com.checkout.Model;

public class ProductModel {

    private String titleProduct, imgProduct, storeProduct, discountProduct, stockProduct, unitProduct, priceProduct;
    private int qtyProduk;

    public ProductModel(){

    }

    public ProductModel(String titleProduct, String imgProduct, String storeProduct, String discountProduct, String stockProduct, String unitProduct, String priceProduct, int qtyProduk) {
        this.titleProduct = titleProduct;
        this.imgProduct = imgProduct;
        this.storeProduct = storeProduct;
        this.discountProduct = discountProduct;
        this.stockProduct = stockProduct;
        this.unitProduct = unitProduct;
        this.priceProduct = priceProduct;
        this.qtyProduk = qtyProduk;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public String getStoreProduct() {
        return storeProduct;
    }

    public String getDiscountProduct() {
        return discountProduct;
    }

    public String getStockProduct() {
        return stockProduct;
    }

    public String getUnitProduct() {
        return unitProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public int getQtyProduk() {
        return qtyProduk;
    }
}
