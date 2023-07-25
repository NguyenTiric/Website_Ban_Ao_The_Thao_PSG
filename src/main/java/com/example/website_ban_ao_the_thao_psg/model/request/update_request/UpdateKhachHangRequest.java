package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.entity.VaiTro;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UpdateKhachHangRequest {
    private Integer id;

    //    @NotNull(message = "Thứ hạng không để trống")
    private ThuHang thuHang;

//    @NotNull(message = "Vai trò không để trống")
    private VaiTro vaiTro;

    @NotBlank(message = "Tên không để trống")
    @Size(min = 0, max = 45, message = "Tên không vượt quá 45 ký tự")
    private String ten;

    @NotNull(message = "Giới tính không để trống")
    private Boolean gioiTinh;

    //    @NotNull(message = "Giới tính không để trống")
    private LocalDate ngaySinh;

    @Lob
    private Blob anh;

    @NotBlank(message = "Địa chỉ không để trống")
    @Size(min = 0, max = 225, message = "Địa chỉ không vượt quá 225 ký tự")
    private String diaChi;

    @NotNull(message = "Email không để trống")
    @Size(min = 0, max = 10, message = "Số điện thoại không vượt quá 10 ký tự")
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
    private String sdt;

    @NotBlank(message = "Email không để trống")
    @Size(min = 0, max = 225, message = "Email không vượt quá 225 ký tự")
    private String email;

    //    @NotNull(message = "Số lượng đơn hàng thành công không để trống")
    private Integer soLuongDonHangThanhCong;

    //    @NotBlank(message = "Mật khẩu không để trống")
    private String matKhau;

    //    @NotNull(message = "Số tiền đã chi tiêu không để trống")
    private BigDecimal soTienDaChiTieu;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;

    @Enumerated(EnumType.STRING)
    private ApplicationConstant.TrangThaiTaiKhoan trangThai;
}
