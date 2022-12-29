package com.example.backend.config;

public abstract class AppConstant {
    public final static long ADMIN_ROLE_ID = 1;
    public final static long SHOP_ROLE_ID = 2;
    public final static long WARRANTY_CENTER_ROLE_ID = 4;
    public final static long FACTORY_ROLE_ID = 3;

    public final static String PRODUCT_STATUS_SOLD = "Đã bán";  //3
    public final static String PRODUCT_STATUS_NEW = "Mới sản xuất"; //1
    public final static String PRODUCT_STATUS_MOVED_TO_SHOP = "Đưa về đại lý";  //2
    public final static String PRODUCT_STATUS_NEEDED_WARRANTY = "Lỗi, cần bảo hành"; //4
    public final static String PRODUCT_STATUS_BEING_WARRANTIED = "Đang sửa chữa bảo hành";  //5
    public final static String PRODUCT_STATUS_WARRANTY_COMPLETED = "Đã bảo hành xong";  //6
    public final static String PRODUCT_STATUS_RETURNED_TO_CUSTOMER = "Đã trả lại bảo hành cho khách hàng";  //7
    public final static String PRODUCT_STATUS_NEEDED_RETURNING_TO_FACTORY = "Lỗi, cần trả về nhà máy";  //8
    public final static String PRODUCT_STATUS_RETURNED_TO_FACTORY = "Lỗi, đã đưa về cơ sở sản xuất";  //9
    public final static String PRODUCT_STATUS_SUMMONED = "Lỗi cần triệu hồi";  //10
    public final static String PRODUCT_STATUS_WARRANTY_END = "Hết thời hạn bảo hành";  //11
    public final static String PRODUCT_STATUS_OUTDATED = "Trả lại cơ sở sản xuất";  //12
}
