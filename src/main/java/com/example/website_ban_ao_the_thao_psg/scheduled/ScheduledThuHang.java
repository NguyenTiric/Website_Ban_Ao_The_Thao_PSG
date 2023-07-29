package com.example.website_ban_ao_the_thao_psg.scheduled;

import com.example.website_ban_ao_the_thao_psg.entity.TaiKhoan;
import com.example.website_ban_ao_the_thao_psg.entity.ThuHang;
import com.example.website_ban_ao_the_thao_psg.repository.TaiKhoanRepository;
import com.example.website_ban_ao_the_thao_psg.repository.ThuHangRepository;
import com.example.website_ban_ao_the_thao_psg.service.ThuHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Component
public class ScheduledThuHang {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private ThuHangRepository thuHangRepository;

    @Autowired
    public JavaMailSender emailSender;

    Random random = new Random();
    int rd = random.nextInt(1000000);
    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("tunganh.devj@gmail.com");
        message.setSubject("Random number:" + rd);
        message.setText("Chào bạn,\n" +
                "\n" +
                "Chúc mừng bạn đã đạt được mức rank mới là: \" + mucThuHang + \" trong cuộc hành trình thể thao của bạn! Đó là một thành tựu tuyệt vời mà bạn đã đạt được và chúng tôi rất tự hào vì được đồng hành cùng bạn trong suốt thời gian qua.\n" +
                "\n" +
                "Với tinh thần chiến đấu không ngừng, bạn đã chinh phục mọi khó khăn, vượt qua mọi thử thách và tiến bước vững chắc trên con đường thành công. Chúng tôi hiểu rằng, như cầu thủ trong câu lạc bộ PSG, bạn không chỉ đang theo đuổi đam mê bóng đá mà còn đại diện cho một phong cách sống thể thao và khích lệ hàng triệu người hâm mộ trên khắp thế giới.\n" +
                "\n" +
                "Và để giúp bạn tiếp tục tỏa sáng trên sân cỏ và ngoài đời, chúng tôi đã chọn lựa những bộ trang phục thể thao của PSG tối ưu nhất, từ chất liệu, thiết kế cho đến xu hướng mới nhất. Chúng tôi luôn đặt chất lượng và sự thoải mái lên hàng đầu để bạn cảm nhận niềm tự hào khi mặc áo PSG.\n" +
                "\n" +
                "Không chỉ là một cửa hàng bán quần áo thể thao, chúng tôi còn là người bạn đồng hành, người cổ vũ và luôn sẵn lòng hỗ trợ bạn trong mọi hoạt động thể thao và cuộc sống hàng ngày. Chúng tôi tin tưởng rằng, bên cạnh những bộ trang phục chất lượng, tinh thần PSG cũng sẽ truyền cảm hứng và năng lượng tích cực cho bạn vươn tới những đỉnh cao mới.\n" +
                "\n" +
                "Hãy luôn kiên nhẫn, rèn luyện và không ngừng phấn đấu, bởi chúng tôi tin rằng thành công không đến từ sự may mắn mà là do sự cống hiến và đam mê của bạn. Và chúng tôi sẽ luôn ở đây, sát cánh bên bạn, để cùng nhau viết tiếp những trang sử vinh quang cho đội bóng PSG và cho chính bạn.\n" +
                "\n" +
                "Cảm ơn bạn đã là một phần quan trọng của cộng đồng PSG Sportswear. Hãy tiếp tục gắn bó với chúng tôi và chúng tôi hứa sẽ không ngừng nâng cao chất lượng dịch vụ để đáp ứng mọi nhu cầu và mong muốn của bạn.\n" +
                "\n" +
                "Chúc mừng và thành công rực rỡ!\n" +
                "\n" +
                "Trân trọng,\n" +
                "PSG Sportswear Team");
        System.out.println("Sent");
        // Send Message!
        this.emailSender.send(message);
    }

    @Scheduled(fixedRate = 5000)
    public void updateThuHang() {
        SimpleMailMessage message = new SimpleMailMessage();

        List<TaiKhoan> taiKhoans = this.taiKhoanRepository.findAll();
        List<ThuHang> activeThuHangList = this.thuHangRepository.findAllByActive();

        for (TaiKhoan taiKhoan : taiKhoans) {
            BigDecimal soTienDaChiTieu = taiKhoan.getSoTienDaChiTieu();
            Integer soLuongDonHangThanhCong = taiKhoan.getSoLuongDonHangThanhCong();
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
                taiKhoan.setThuHang(selectedThuHang);
                taiKhoanRepository.save(taiKhoan);
            }
        }
    }
}
