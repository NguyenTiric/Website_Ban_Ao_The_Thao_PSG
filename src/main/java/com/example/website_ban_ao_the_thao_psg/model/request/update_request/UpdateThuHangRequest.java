package com.example.website_ban_ao_the_thao_psg.model.request.update_request;

import com.example.website_ban_ao_the_thao_psg.common.ApplicationConstant.TrangThaiThuHang;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UpdateThuHangRequest {

    private Integer id;

    private String ma;

    private String ten;

    @NotNull(message = "Số tiền chi tiêu tối thiểu không để trống")
    @Min(value = 1, message = "Số tiền chi tiêu tối thiểu là số nguyên")
    private BigDecimal soTienKhachChiToiThieu;

    @NotNull(message = "Số lượng đơn hàng thành công không để trống")
    @Min(value = 1, message = "Số lượng đơn hàng tối thiểu là số nguyên")
    private Integer soLuongDonHangToiThieu;

    @Enumerated(EnumType.STRING)
    private TrangThaiThuHang trangThai;

    private LocalDate ngayTao;

    private LocalDate ngayCapNhat;
}
