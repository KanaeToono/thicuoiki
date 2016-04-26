package com.example.conga.finaltest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.conga.finaltest.R;

import constants.Constant;

/**
 * Created by ConGa on 25/04/2016.
 */
public class DangKiNhanTinFragment extends Fragment implements View.OnClickListener{
    public static final String ITEM_NAME = "iTem";
    private static String TAG = DangKiNhanTinFragment.class.getSimpleName();
    private TextView mTextViewTieuDiem;
    private TextView mTextViewThongTinDaoTao;
    private TextView mTextViewCongTacSinhVien;
    private TextView mTextViewKhaoThiVaDamBaoChatLuong;
    private TextView mTextViewKhoaHocSauDaiHoc;
    private TextView mTextViewDangUy;
    private TextView mTextViewTDoanThanhNien;
    private TextView mTextViewTinSinhVien;
    private TextView mTextViewThongBao;
    private TextView mTextViewCoHoiNgheNghiep;
    private TextView mTextViewDoanThanhNienIt;
    // check box
    private CheckBox mCheckBoxTieuDiem;
    private CheckBox mCheckBoxThongTinDaoTao;
    private CheckBox mCheckBoxCongTacSinhVien;
    private CheckBox mCheckBoxKhaoThiVaDamBaoChatLuong;
    private CheckBox mCheckBoxKhoaHocSauDaiHoc;
    private CheckBox mCheckBoxDangUy;
    private CheckBox mCheckBoxDoanThanhNien;
    private CheckBox mCheckBoxTinSinhVien;
    private CheckBox mCheckBoxThongBao;
    private CheckBox mCheckBoxCoHoiNgheNghiep;
    private CheckBox mCheckBoxThanhNienIt;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "on create dang ki nhan tin frag");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dangkinhantin, container, false);
        // lay id cua cac textView
        mTextViewTieuDiem = (TextView) view.findViewById(R.id.textViewTieuDiem);
        mTextViewThongTinDaoTao = (TextView) view.findViewById(R.id.textViewThongTinDaoTao);
        mTextViewCongTacSinhVien = (TextView) view.findViewById(R.id.textViewCongTacSinhVien);
        mTextViewKhaoThiVaDamBaoChatLuong = (TextView) view.findViewById(R.id.textViewKhaoThi);
        mTextViewKhoaHocSauDaiHoc= (TextView) view.findViewById(R.id.textViewKhoaHocSauDaiHoc);
        mTextViewDangUy = (TextView) view.findViewById(R.id.textViewDangUy);
        mTextViewTDoanThanhNien = (TextView) view.findViewById(R.id.textViewDoanThanhNien);
        mTextViewTinSinhVien = (TextView) view.findViewById(R.id.textViewTinSinhVien);
        mTextViewThongBao = (TextView) view.findViewById(R.id.textViewThongBao);
        mTextViewCoHoiNgheNghiep = (TextView) view.findViewById(R.id.textViewCoHoiNgheNghiep);
        mTextViewDoanThanhNienIt = (TextView) view.findViewById(R.id.textViewDoanThanhNienIT);
        // lay id check box
        mCheckBoxTieuDiem = (CheckBox) view.findViewById(R.id.checkBoxTieuDiem);
        mCheckBoxThongTinDaoTao = (CheckBox) view.findViewById(R.id.checkBoxThongTinDaoTao);
        mCheckBoxCongTacSinhVien = (CheckBox) view.findViewById(R.id.checkBoxCongTacSinhVien);
        mCheckBoxKhaoThiVaDamBaoChatLuong = (CheckBox) view.findViewById(R.id.checkBoxKhaoThi);
        mCheckBoxKhoaHocSauDaiHoc = (CheckBox) view.findViewById(R.id.checkBoxKhoaHocSauDaiHoc);
        mCheckBoxDangUy = (CheckBox) view.findViewById(R.id.checkBoxDangUy);
        mCheckBoxDoanThanhNien = (CheckBox) view.findViewById(R.id.checkBoxDoanThanhNien);
        mCheckBoxTinSinhVien = (CheckBox) view.findViewById(R.id.checkBoxTinSinhVien);
        mCheckBoxThongBao = (CheckBox) view.findViewById(R.id.checkBoxThongBao);
        mCheckBoxCoHoiNgheNghiep = (CheckBox) view.findViewById(R.id.checkBoxCoHoiNgheNghiep);
        mCheckBoxThanhNienIt= (CheckBox) view.findViewById(R.id.checkBoxDoanThanhNienIt);
        // GAN XU KIEN CHO TUNG NUT
        mTextViewTieuDiem.setOnClickListener(this);
        mTextViewDoanThanhNienIt.setOnClickListener(this);
        mTextViewCoHoiNgheNghiep.setOnClickListener(this);
        mTextViewTinSinhVien.setOnClickListener(this);
        mTextViewKhaoThiVaDamBaoChatLuong.setOnClickListener(this);
        mTextViewDangUy.setOnClickListener(this);
        mTextViewKhoaHocSauDaiHoc.setOnClickListener(this);
        mTextViewTDoanThanhNien.setOnClickListener(this);
        mTextViewThongBao.setOnClickListener(this);
        mTextViewCongTacSinhVien.setOnClickListener(this);
        mTextViewThongTinDaoTao.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textViewTieuDiem:
                Fragment fragment = new TieuDiemFragment();
                Bundle bundle = new Bundle();
                if(mCheckBoxTieuDiem.isChecked()){
                    bundle.putInt(Constant.key, 1);

                }
                else {
                    bundle.putInt(Constant.key, 0);

                }
                fragment.setArguments(bundle);
                break;
             case R.id.textViewThongTinDaoTao:
                break;
             case R.id.textViewCongTacSinhVien:
                break;
             case R.id.textViewKhaoThi:
                break;
             case R.id.textViewKhoaHocSauDaiHoc:
                break;
             case R.id.textViewDangUy:
                break;
             case R.id.textViewDoanThanhNien:
                break;
             case R.id.textViewTinSinhVien:
                break;
             case R.id.textViewThongBao:
                break;
             case R.id.textViewCoHoiNgheNghiep:
                break;
             case R.id.textViewDoanThanhNienIT:
                break;
             default:
                 break;

        }
    }
}
