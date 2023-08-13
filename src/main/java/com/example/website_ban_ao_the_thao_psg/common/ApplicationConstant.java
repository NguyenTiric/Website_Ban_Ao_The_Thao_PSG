package com.example.website_ban_ao_the_thao_psg.common;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApplicationConstant {

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiSanPham {

        ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiSanPham(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiChiTietSanPham {

        ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error"), PENDING("PENDING", "Chưa Kích Hoạt", "warning");
        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiChiTietSanPham(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiChiTietVouCherThuHang {

        ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error"), PENDING("PENDING", "Chưa Kích Hoạt", "warning");
        private final String ten;
        private final String moTa;
        private final String mauSac;

        TrangThaiChiTietVouCherThuHang(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiQuyDinh {
        ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiQuyDinh(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiVaiTro {
        ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiVaiTro(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiThuHang {
        ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiThuHang(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiViVoucher {
        ACTIVE("ACTIVE", "Hoạt động", "primary"),
        FAILED("FAILED", "Đã sử dụng", "error"),
        INACTIVE("INACTIVE", "Ngừng hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiViVoucher(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

//    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
//    public enum TenThuHang {
//        MEMBER("MEMBER", "Thành viên", "success"), SILVER("SILVER", "Bạc", "primary"), GOLD("GOLD", "Vàng", "warning"), DIAMOND("DIAMOND", "Kim cương", "error");
//
//        private final String ten;
//        private final String moTa;
//        private final String mauSac;
//
//        private TenThuHang(String ten, String moTa, String mauSac) {
//            this.ten = ten;
//            this.moTa = moTa;
//            this.mauSac = mauSac;
//        }
//
//        public String getTen() {
//            return ten;
//        }
//
//        public String getMoTa() {
//            return moTa;
//        }
//
//        public String getMauSac() {
//            return mauSac;
//        }
//    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiDiaChi {
        PENDING("DEFAULT", "Địa chỉ mặc định", "warning"), CONFIRMED("EXTRA", "Địa chỉ phụ", "success"), ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error");
        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiDiaChi(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiTaiKhoan {
        ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiTaiKhoan(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiVoucher {
        PENDING("PENDING", "Sắp Diễn Ra", "warning") ,ACTIVE("ACTIVE", "Hoạt động", "primary"), INACTIVE("INACTIVE", "Không hoạt động", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiVoucher(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum HinhThucThanhToan {
        CASH("CASH", "Tiền mặt", "success"), BANKING("BANKING", "Chuyển khoản", "primary");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private HinhThucThanhToan(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiGiaoDich {
        PENDING("PENDING", "Đang chờ", "warning"), FAILED("FAILED", "Thất bại", "error"), APPROVED("APPROVED", "Thành công", "primary");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiGiaoDich(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum LoaiGiaoDich {
        PAYMENT("PAYMENT", "Thanh toán", "primary"), REVERSE("REVERSE", "Hoàn tiền", "error");
        private final String ten;
        private final String moTa;
        private final String mauSac;

        private LoaiGiaoDich(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum HinhThucBanHang {
        ACTIVE("ACTIVE", "Tại quầy", "primary"), INACTIVE("INACTIVE", "Giao hàng", "secondary");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private HinhThucBanHang(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiHoaDon {

        PENDING("PENDING", "Đang chờ xác nhận", "warning"),
        CONFIRMED("CONFIRMED", "Đã xác nhận", "success"),
        SHIPPING("SHIPPING", "Đang vận chuyển", "secondary"),
        CANCELLED("CANCELLED", "Đã hủy", "error"),
        APPROVED("APPROVED", "Đã hoàn thành", "primary"),
        REVERSE("REVERSE", "Đã trả hàng", "info");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiHoaDon(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum LoaiLichSuHoaDon {
        CREATED("CREATED", "Tạo Đơn Hàng", "#2dc258"),
        CONFIRMED("CONFIRMED", "Đã Xác Nhận Thông Tin Thanh Toán", "#2dc258"),
        SHIPPING("SHIPPING", "Đã Giao Cho Đơn Vị Vận Chuyển", "#2dc258"),
        APPROVED("APPROVED", "Đã Nhận Được Hàng", "#2dc258"),
        CANCELLED("CANCELLED", "Đơn Hàng Đã Hủy", "#9c2919"),
        EDITED("EDITED", "Chỉnh Sửa Đơn Hàng", "#ffc107"),
        REVERSED("REVERSED", "Hoàn Trả", "#9c2919");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private LoaiLichSuHoaDon(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }

    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiHoaDonChiTiet {
        APPROVED("APPROVED", "Đã xác nhận", "primary"), REVERSE("REVERSE", "Đã hoàn trả", "error");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiHoaDonChiTiet(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }


    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum TrangThaiGioHangChiTiet {
        PENDING("PENDING", "Đang chờ", "primary"), CANCELLED("CANCELLED", "Đã hủy", "error"), APPROVED("APPROVED", "Đã hoàn thành", "success");

        private final String ten;
        private final String moTa;
        private final String mauSac;

        private TrangThaiGioHangChiTiet(String ten, String moTa, String mauSac) {
            this.ten = ten;
            this.moTa = moTa;
            this.mauSac = mauSac;
        }

        public String getTen() {
            return ten;
        }

        public String getMoTa() {
            return moTa;
        }

        public String getMauSac() {
            return mauSac;
        }
    }
}
