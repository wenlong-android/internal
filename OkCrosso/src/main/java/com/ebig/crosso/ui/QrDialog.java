package com.ebig.crosso.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.ebig.crosso.R;
import com.ebig.crosso.bean.aop.RamInfo;
import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.utils.CroAppUtils;
import com.ebig.crosso.utils.CroStrUtils;
import com.ebig.crosso.utils.CrossoTimeUtils;
import com.ebig.crosso.widget.MemoryView;
import com.ebig.utils.GsonUtils;
import com.google.gson.Gson;


public class QrDialog extends MjBaseDialog {
    private TextView tvContent;
    private ImageView ivQr;
    private AopDbInfo mAopDbInfo;
    private MemoryView mvMemory;
    private TextView tvType, tvEdtor, tvTime;
    private TextView tvStack,tvThread;

    public QrDialog(@NonNull Context context, AopDbInfo info) {
        super(context);
        this.mAopDbInfo = info;
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.dialog_qr);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (CroAppUtils.getScreenWidth() * 0.99);
        lp.height = (int) (CroAppUtils.getScreenHight() * 0.90);
        window.setAttributes(lp);
        initView();
        initData(mAopDbInfo);
    }

    private void initData(AopDbInfo mAopDbInfo) {
        String json = new Gson().toJson(mAopDbInfo);
        String memory = mAopDbInfo.getMemory();
        RamInfo ramInfo = new Gson().fromJson(memory, RamInfo.class);
        mvMemory.setContent(ramInfo);
        tvContent.setText(GsonUtils.stringToJSON(mAopDbInfo.getDetail()));
        //  ivQr.setImageBitmap(  ZxingUtils.createQRCode(json));
        tvStack.setText(mAopDbInfo.getStack());
        tvThread.setText(mAopDbInfo.getThread());
        tvType.setText(/*"类型:"+*/ CroStrUtils.getType(mAopDbInfo.getEvent()));
        tvTime.setText("发生时间："+CrossoTimeUtils.getDateFormat(mAopDbInfo.getId()));
       // tvEdtor.setText("责任人:"+(StrUtils.empty(mAopDbInfo.getAppResponsible())?"暂无":aopDbInfo.getAppResponsible()));
    }


    private void initView() {
        mvMemory = (MemoryView) findViewById(R.id.mvMemory);
        tvContent = (TextView) findViewById(R.id.tvContent);
        tvStack=(TextView)findViewById(R.id.tvStack);
        tvThread=(TextView)findViewById(R.id.tvThread);
        ivQr = (ImageView) findViewById(R.id.ivQr);
        tvType = (TextView) findViewById(R.id.tvType );
        tvEdtor = (TextView) findViewById(R.id.tvEdtor);
        tvTime = (TextView) findViewById(R.id.tvTime);
        findViewById(R.id.tvOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText(new Gson().toJson(mAopDbInfo,AopDbInfo.class));
            }
        });

        findViewById(R.id.print).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = mAopDbInfo.getDetail();
//                if (msg.length() > 10000) {
//                    msg = msg.substring(0, 10000);
//                }
                System.out.println("日志信息:" + msg);
            }
        });
    }

    public void shareText(String content) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        shareIntent.setType("text/plain");
        //设置分享列表的标题，并且每次都显示分享列表
        getContext().startActivity(Intent.createChooser(shareIntent, "分享到"));

    }
}


