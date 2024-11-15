package com.web.sgucharitywebsite.helper;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
public class CurrencyFormatter {
    // Phương thức để chuyển đổi kiểu double sang chuỗi tiền tệ VND
    public static String formatToVND(double amount) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        return currencyVN.format(amount);
    }
    // Phương thức để chuyển đổi chuỗi tiền tệ VND sang kiểu double
    public static double parseCurrencyStringToDouble(String amountStr) {
        if (amountStr == null || amountStr.isEmpty()) {
            return 0.0;
        }

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

        try {
            // Xóa ký hiệu tiền tệ, khoảng trắng và dấu phân cách nếu có
            amountStr = amountStr.replaceAll("[₫\\s]", "");
            // Phân tích chuỗi thành số
            Number number = currencyVN.parse(amountStr);
            return number.doubleValue();
        } catch (ParseException e) {
            System.err.println("Lỗi khi chuyển đổi chuỗi tiền tệ: " + e.getMessage());
            return 0.0;
        }
    }
}
