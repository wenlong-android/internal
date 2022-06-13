package com.ebig.crosso.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.ebig.crosso.R;
import com.ebig.crosso.R2;
import com.ebig.crosso.bean.aop.RamInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemoryView extends LinearLayout {

    @BindView(R2.id.tvLowRunning)
    TextView tvLowRunning;
    @BindView(R2.id.tvleft)
    TextView tvleft;
    @BindView(R2.id.tvMax)
    TextView tvMax;
    @BindView(R2.id.tvUse)
    TextView tvUse;
    @BindView(R2.id.tvNativeMax)
    TextView tvNativeMax;
    @BindView(R2.id.tvNativeUse)
    TextView tvNativeUse;
    @BindView(R2.id.tvNativeLeft)
    TextView tvNativeLeft;
    @BindView(R2.id.tvNativeSystem)
    TextView tvNativeSystem;

    public MemoryView(Context context) {
        super(context);
    }

    public MemoryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_memory, this, true);
        ButterKnife.bind(this);
        //initView();
    }


    public void setContent( RamInfo ramInfo) {
        tvLowRunning.setText(ramInfo.getLowRunning());
        tvleft.setText(ramInfo.getFree());
        tvMax.setText(ramInfo.getMax());
        tvUse.setText(ramInfo.getUse());
        tvNativeMax.setText(ramInfo.getNativeAll());
        tvNativeUse.setText(ramInfo.getNativeExsit());
        tvNativeLeft.setText(ramInfo.getNativeFree());
        tvNativeSystem.setText(ramInfo.getSystemFree());
    }
}
