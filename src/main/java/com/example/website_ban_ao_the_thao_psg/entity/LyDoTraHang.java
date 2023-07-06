package com.example.website_ban_ao_the_thao_psg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "ly_do_tra_hang")
public class LyDoTraHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "mo _ta")
    private String moTa;

    @Column(name = "ly_do_tra_hang")
    private String lyDoTraHang;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "video")
    private String video;

    @Column(name = "trang_thai")
    private String trangThai;
}
