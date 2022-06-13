package com.ebig.medical.demo.fragment;

import android.view.View;
import android.widget.Button;

import com.ebig.log.ELog;
import com.ebig.medical.demo.R;

import com.ebig.medical.demo.R2;
import com.ebig.socket.dispatchWrite.base.ICommand;
import com.ebig.socket.dispatchWrite.base.EbWriter;
import com.ebig.socket.dispatchWrite.lcd.LcdParam02CleanAll;
import com.ebig.socket.dispatchWrite.lcd.LcdParam06OnShelf;
import com.ebig.socket.dispatchWrite.lcd.LcdParam07OnBlack;
import com.ebig.socket.dispatchWrite.lcd.LcdParam08Chose;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class LedFragment extends BaseFrament {

    @BindView(R2.id.set_name)
    Button setName;

    @BindView(R2.id.set_spec)
    Button setSpec;

    @BindView(R2.id.set_num)
    Button setNum;

    @BindView(R2.id.set_nickName)
    Button setNickName;

    @BindView(R2.id.set_vender)
    Button setVender;

    @BindView(R2.id.set_barCode)
    Button setBarCode;

    @BindView(R2.id.set_unit)
    Button setUnit;

    @BindView(R2.id.set_model)
    Button setModel;

    @BindView(R2.id.set_sn)
    Button setSn;

    @BindView(R2.id.set_mode)
    Button setMode;

    @Override
    protected String getTagName() {
        return this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_led;
    }

    @Override
    protected void getParentView(View view) {

    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onDestory() {

    }

    @Override
    protected void onFragmentPause() {

    }

    @Override
    protected void onFragmentResume() {

    }

    @Override
    public void initSocketListenner() {

    }

    @Override
    public void onClientConnected() {

    }

    @Override
    public void onClientDisConnected() {

    }
    private int index=1;
    private Timer indexTimer;
    @OnClick({R2.id.set_name, R2.id.set_spec, R2.id.set_num, R2.id.set_nickName, R2.id.set_vender, R2.id.set_barCode, R2.id.set_unit, R2.id.set_model, R2.id.set_sn, R2.id.set_mode})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.set_name) {
            ICommand ICommand = EbWriter.l();
              index=1;
            indexTimer=new Timer();
            indexTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ICommand.withLcd().show(new LcdParam02CleanAll()).sendTo(1, 1, index);
                    index++;
                    if (index>5){
                        indexTimer.cancel();
                    }
                }
            },0,500);
//            ICommand.withLcd().show(new LcdParam02CleanAll()).sendTo(1, 1, 1);
//            ICommand.withLcd().show(new LcdParam02CleanAll()).sendTo(1, 1, 2);

//            ICommand.withLcd().show(new LcdParam02CleanAll()).sendTo(1, 1, 4);
//            ICommand.withLcd().show(new LcdParam02CleanAll()).sendTo(1, 1, 5);


        } else if (viewId == R.id.set_spec) {
            ICommand ICommand = EbWriter.l();
            LcdParam06OnShelf shelf = new LcdParam06OnShelf();
            for (int i = 1; i < 6; i++) {
                shelf.setName("测试商品10"+i);
                shelf.setSpecification("3.0*88.6*150");
                shelf.setModel("Model-NP-911");
                shelf.setNickName("测试别名");
                shelf.setVender("测试厂家名称");
                shelf.setBarCode("CODE123456789ABCDEF"+i);
                shelf.setIdentifier("SN2020A9W9JJ"+i);
                shelf.setUnit("个");
                shelf.setNumber((10*i)+"");
                shelf.setExpand1(90+"");
                String smg = shelf.getFinalCmdString();
                ELog.print("getFinalCmdString:" + smg + " len:" + smg.length());
                ICommand.withLcd().show(shelf).sendTo(1, 1, i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (viewId == R.id.set_num) {
            ICommand ICommand = EbWriter.l();
            LcdParam07OnBlack shelf = new LcdParam07OnBlack();
            for (int i = 1; i < 6; i++) {
                shelf.setName("测试商品10"+i);
                shelf.setSpecification("3.0*88.6*150");
                shelf.setModel("Model-NP-911");
                shelf.setNickName("测试别名");
                shelf.setVender("测试厂家名称");
                shelf.setBarCode("CODE123456789ABCDCD"+i);
                shelf.setIdentifier("SN2020A9W9VV"+i);
                shelf.setUnit("个");
                shelf.setNumber((10*i)+"");
                String smg = shelf.getFinalCmdString();
                ELog.print("getFinalCmdString:" + smg + " len:" + smg.length());
                ICommand.withLcd().show(shelf).sendTo(1, 1, i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (viewId == R.id.set_nickName) {
            ICommand ICommand = EbWriter.l();
            LcdParam08Chose shelf = new LcdParam08Chose();
            for (int i = 1; i < 6; i++) {
                shelf.setName("测试商品10"+i);
                shelf.setSpecification("3.0*88.6*150");
                shelf.setModel("Model-NP-911");
                shelf.setNickName("测试别名");
                shelf.setVender("测试厂家名称");
                shelf.setBarCode("CODE123456789ABFFFF"+i);
                shelf.setIdentifier("SN2020A9W9RR"+i);
                shelf.setUnit("个");
                shelf.setExpand1("80");
                shelf.setNumber((10*i)+"");
                String smg = shelf.getFinalCmdString();
                ELog.print("getFinalCmdString:" + smg + " len:" + smg.length());
                ICommand.withLcd().show(shelf).sendTo(1, 1, i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (viewId == R.id.set_vender) {
        } else if (viewId == R.id.set_barCode) {
        } else if (viewId == R.id.set_unit) {
        } else if (viewId == R.id.set_model) {
        } else if (viewId == R.id.set_sn) {
        } else if (viewId == R.id.set_mode) {
        }
    }
}
