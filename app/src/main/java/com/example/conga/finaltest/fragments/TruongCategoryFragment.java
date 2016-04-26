package com.example.conga.finaltest.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conga.finaltest.R;

import java.util.ArrayList;
import java.util.List;

public class TruongCategoryFragment extends Fragment {
    private static String TAG = TruongCategoryFragment.class.getSimpleName();
    public static final String ITEM_NAME = "item";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, " Khoi tao cac chuyen muc co trong truong");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chuyenmuctruongtablayoutfragment, container, false);

        //view payer
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        //tablayout
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    // Thiet lap su kien viewpayer
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        //getString
        String tieuDiem =  getString(R.string.action_tieu_diem);
        String thongTinDaoTao= getString(R.string.action_thong_tin_dao_tao);
        String congTacSinhVien= getString(R.string.action_cong_tac_sinh_vien);
        String khaoThi= getString(R.string.action_khao_thi_va_dam_bao_chat_luong);
        String khoaHocSauDaiHoc= getString(R.string.action_khoa_hoc_sau_dai_hoc);
        String dangUy= getString(R.string.action_dang_uy);
        String doanThanhNien= getString(R.string.action_doan_thanh_nien);
        String tinSinhVien= getString(R.string.action_tin_sinh_vien);
        //adpter
        adapter.addFrag(new TieuDiemFragment(), tieuDiem);
        adapter.addFrag(new ThongTinDaoTaoFragment(), thongTinDaoTao);
        adapter.addFrag(new CongTacSinhVienFragment(), congTacSinhVien);
        adapter.addFrag(new KhaoThiFragment(), khaoThi);
        adapter.addFrag(new KhoaHocSauDaoTaoFragment(), khoaHocSauDaiHoc);
        adapter.addFrag(new DangUyFragment(), dangUy);
        adapter.addFrag(new DoanThanhNienFragment(), doanThanhNien);
        adapter.addFrag(new TinSinhVienFragment(), tinSinhVien);
        // thiet lap view payer
        viewPager.setAdapter(adapter);

    }

    // class viewpayer
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }


        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("fragment_added", true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, " start");

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "PAUSE ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "STOP ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "on destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "DESTROY ");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "ATTACH");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "ON DETACH ");
    }
}
