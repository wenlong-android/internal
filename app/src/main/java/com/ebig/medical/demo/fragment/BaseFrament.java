package com.ebig.medical.demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFrament extends Fragment implements BaseView, BaseFragmentIntefaces {


    protected abstract String getTagName();

    protected abstract int setContentView();

    protected abstract void getParentView(View view);

    protected abstract void onCreate();

    protected abstract void onDestory();

    protected abstract void onFragmentPause();

    protected abstract void onFragmentResume();

    private String tag = "BaseFragment";
    private Unbinder unbinder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);

        tag = getTagName();
        int layout = setContentView();
        View view = inflater.inflate(layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        getParentView(view);
        onCreate();

        return view;
    }


    @Override
    public void showErrorMsg(CharSequence msg) {

        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMsg(int msgId) {

    }

    @Override
    public void showMsg(CharSequence msg) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {
            Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
        }
    });
    }

    @Override
    public void showMsg(int msgId) {

    }

    @Override
    public void showLoading(Object cancel) {


    }

    @Override
    public void dismissLoading(Object cancel) {

    }

    @Override
    public void onLoadViewDismissed() {

    }

    @Override
    public void finishView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      // ELog.print(tag, "onDestroyView");

        onDestory();
    }

    @Override
    public void onStart() {
        super.onStart();
      // ELog.print(tag, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
     //  ELog.print(tag, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
      // ELog.print(tag, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
       //ELog.print(tag, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
      // ELog.print(tag, "onDestroy");
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            // 不在最前端显示 相当于调用了onPause();
            onFragmentPause();
          // ELog.print(tag, "onFragmentPause");
        } else {
            // 在最前端显示 相当于调用了onResume();
            onFragmentResume();
        //   ELog.print(tag, "onFragmentResume");
        }
    }

    public void onScanGunResult(String result) {

    }


}
