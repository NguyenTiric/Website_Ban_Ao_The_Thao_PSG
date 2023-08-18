package com.example.website_ban_ao_the_thao_psg.scheduled;

import com.example.website_ban_ao_the_thao_psg.entity.KhachHang;
import com.example.website_ban_ao_the_thao_psg.model.response.KhachHangResponse;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.repository.KhachHangRepository;
import com.example.website_ban_ao_the_thao_psg.repository.NhanVienRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@EnableScheduling
@Component
public class ScheduledThuHang {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Autowired
    private ThuHangRepository thuHangRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Scheduled(fixedRate = 6000)
    public void updateThuHang() {
        SimpleMailMessage message = new SimpleMailMessage();

        List<KhachHang> khachHangs = this.khachHangRepository.findAll();
        List<ThuHang> activeThuHangList = this.thuHangRepository.findAllByActive();
        for (KhachHang khachHang : khachHangs) {
            BigDecimal soTienDaChiTieu = khachHang.getSoTienDaChiTieu();
            Integer soLuongDonHangThanhCong = khachHang.getSoLuongDonHangThanhCong();
            ThuHang selectedThuHang = null;

            for (ThuHang thuHang : activeThuHangList) {
                BigDecimal soTienToiThieu = thuHang.getSoTienKhachChiToiThieu();
                Integer soDonHangToiThieu = thuHang.getSoLuongDonHangToiThieu();

                if (soDonHangToiThieu <= soLuongDonHangThanhCong && soTienDaChiTieu.compareTo(soTienToiThieu) >= 0) {
                    if (selectedThuHang == null || (soDonHangToiThieu >= selectedThuHang.getSoLuongDonHangToiThieu()
                            && soTienToiThieu.compareTo(selectedThuHang.getSoTienKhachChiToiThieu()) >= 0)) {
                        selectedThuHang = thuHang;
                    }
                }
            }

            if (selectedThuHang != null) {
                khachHang.setThuHang(selectedThuHang);
                khachHangRepository.save(khachHang);
                message.setTo(khachHang.getEmail());
                message.setSubject("Chúc mừng! Bạn đã thăng hạng trong Hội viên Ưu đãi PSG Fashion");
                message.setText("Chào bạn "+khachHang.getTen()+" thân mến,\n" +
                        "\n" +
                        "Chúc mừng bạn đã chính thức thăng hạng lên "+khachHang.getThuHang().getTen()+" trong Hội viên Ưu đãi PSG Fashion! Đây là một thành quả đáng tự hào mà bạn đã đạt được và chúng tôi xin gửi lời chúc mừng chân thành nhất đến bạn.\n" +
                        "\n" +
                        "Chúng tôi biết rằng bạn đã luôn ủng hộ chúng tôi trong suốt thời gian qua và chúng tôi xin cảm ơn bạn vô cùng. Thăng hạng của bạn không chỉ là một sự công nhận về sự hỗ trợ của bạn, mà còn là sự thể hiện về sự kiên nhẫn và sự cam kết của bạn đối với thương hiệu PSG Fashion.\n" +
                        "\n" +
                        "Với thăng hạng này, bạn sẽ được hưởng nhiều ưu đãi độc quyền hơn, bao gồm:\n" +
                        "\n" +
                        "Giảm giá đặc biệt cho các sản phẩm mới nhất của PSG Fashion.\n" +
                        "Quyền truy cập trước vào các sự kiện và chương trình khuyến mãi sắp tới.\n" +
                        "Đặc quyền tham gia vào các buổi triển lãm thời trang và hậu trường.\n" +
                        "Quà tặng độc đáo và các ưu đãi đặc biệt khác dành riêng cho Hội viên Ưu đãi.\n" +
                        "Chúng tôi rất mong muốn tiếp tục chia sẻ những trải nghiệm thú vị và mới mẻ với bạn trong tương lai. Hãy tiếp tục ủng hộ PSG Fashion và chúng tôi cam kết sẽ không ngừng phấn đấu để mang đến cho bạn những sản phẩm thời trang tốt nhất và những trải nghiệm mua sắm đáng nhớ.\n" +
                        "\n" +
                        "Nếu bạn có bất kỳ câu hỏi hoặc góp ý nào, xin vui lòng liên hệ với chúng tôi qua số điện thoại [số điện thoại] hoặc email [địa chỉ email]. Chúng tôi luôn sẵn sàng hỗ trợ bạn.\n" +
                        "\n" +
                        "Một lần nữa, chúc mừng bạn với sự thăng hạng đầy ý nghĩa này. Cảm ơn bạn vì đã là một phần quan trọng của cộng đồng PSG Fashion.\n" +
                        "\n" +
                        "Trân trọng,\n" +
                        "Nguyễn Trọng Tùng Anh\n" +
                        "Giám đốc\n" +
                        "PSG Fashion");
                emailSender.send(message);
            }
        }
    }
}
