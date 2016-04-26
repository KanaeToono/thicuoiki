package com.example.conga.finaltest.interfaces;


import com.example.conga.finaltest.models.ContentRss;
import com.example.conga.finaltest.models.RssItem;

import java.util.ArrayList;

/**
 * Created by ConGa on 19/04/2016.
 */
public interface ICRUDDatabase {
    //tieudiem
    public void addNewTieuDiem(RssItem rssItem);

    public ArrayList<RssItem> getAllTieuDiem();

    public void deleteItemTieuDiem(int id_rss);

    public void upDateStatusTieuDiem(int status, int id_rss);

    public int getCountTieuDiem();

    //thong tin dao tao
    public void addNewThongTinDaoTao(RssItem rssItem);

    public ArrayList<RssItem> getAllThongTinDaoTao();

    public void deleteItemThongTinDaoTao(int id_rss);

    public void upDateStatusThongTinDaoTao(int status, int id_rss);
    public int getCountThongTinDaoTao();
    // cong tac sinh vien
    public void addNewCongTacSinhVien(RssItem rssItem);

    public ArrayList<RssItem> getAllCongTacSinhVien();

    public void deleteItemCongTacSinhVien(int id_rss);

    public void upDateStatusCongTacSinhVien(int status, int id_rss);
    public int getCountCongTacSinhVien();

    // khao thi va dam bao chat luong
    public void addNewKhaoThi(RssItem rssItem);

    public ArrayList<RssItem> getAllKhaoThi();

    public void deleteItemKhaoThi(int id_rss);

    public void upDateStatusKhaoThi(int status, int id_rss);
    public int getCountKhaoThi();

    //khoa hoc sau dai hoc
    public void addNewKhoaHoc(RssItem rssItem);

    public ArrayList<RssItem> getAllKhoaHoc();

    public void deleteItemKhoaHoc(int id_rss);

    public void upDateStatusKhoaHoc(int status, int id_rss);

    public int getCountKhoaHoc();
    // dang uy
    public void addNewDangUy(RssItem rssItem);
    public ArrayList<RssItem> getAllDangUy();

    public void deleteItemDangUy(int id_rss);

    public void upDateStatusDangUy(int status, int id_rss);
    public int getCountDangUy();
    //doan thanh nien
    public void addNewDoanThanhNien(RssItem rssItem);

    public ArrayList<RssItem> getAllDoanThanhNien();

    public void deleteItemDoanThanhNien(int id_rss);

    public void upDateStatusDoanThanhNien(int status, int id_rss);
    public int getCountDoanThanhNien();

    // tin sinh vien
    public void addNewTinSinhVien(RssItem rssItem);

    public ArrayList<RssItem> getAllTinSinhVien();

    public void deleteItemTinSinhVien(int id_rss);

    public void upDateStatusTinSinhVien(int status, int id_rss);
    public int getCountTinSinhVien();
    //khoatin

    // thong bao
    public void addNewThongBao(RssItem rssItem);

    public ArrayList<RssItem> getAllThongBao();

    public void deleteItemThongBao(int id_rss);

    public void upDateStatusThongBao(int status, int id_rss);
    public int getCountThongBao();
    // doan thanh nien it
    public void addNewDoanThanhNienIt(RssItem rssItem);

    public ArrayList<RssItem> getAllDoanThanhNienIt();

    public void deleteItemDoanThanhNienIt(int id_rss);

    public void upDateStatusDoanThanhNienIt(int status, int id_rss);
    public int getCountDoanThanhNienIt();
    // co hoi nghe nghiep
    public void addNewCoHoiNgheNghiep(RssItem rssItem);

    public ArrayList<RssItem> getAllCoHoiNgheNghiep();

    public void deleteItemCoHoiNgheNghiep(int id_rss);

    public void upDateStatusCoHoiNgheNghiep(int status, int id_rss);

    public int getCountCoHoiNgheNghiep();

    //offline
    //offline
    public void addNewItemContent(ContentRss contentRss);
    public ArrayList<ContentRss> getAllItemsContent();
    public void deleteItemContent(int id_content);

}
