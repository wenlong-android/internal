package com.ebig.medical.demo.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.ebig.medical.demo.R;
import com.ebig.medical.demo.R2;
import com.ebig.socket.common.AndPipe;
import com.ebig.socket.dispatchWrite.colorLight.CLightParam;

import butterknife.BindView;
import butterknife.OnClick;

public class ColorLightFragment extends BaseFrament {
    @BindView(R2.id.redToogle)
    ToggleButton redToogle;
    @BindView(R2.id.red_on_milli)
    EditText redOnMilli;
    @BindView(R2.id.red_off_milli)
    EditText redOffMilli;
    @BindView(R2.id.greenToogle)
    ToggleButton greenToogle;
    @BindView(R2.id.green_on_milli)
    EditText greenOnMilli;
    @BindView(R2.id.green_off_milli)
    EditText greenOffMilli;
    @BindView(R2.id.yellowToogle)
    ToggleButton yellowToogle;
    @BindView(R2.id.yellow_on_milli)
    EditText yellowOnMilli;
    @BindView(R2.id.yellow_off_milli)
    EditText yellowOffMilli;
    @BindView(R2.id.beedToogle)
    ToggleButton beedToogle;
    @BindView(R2.id.beed_on_milli)
    EditText beedOnMilli;
    @BindView(R2.id.beed_off_milli)
    EditText beedOffMilli;
    @BindView(R2.id.lastSecond)
    EditText lastSecond;
    @BindView(R2.id.btn_sendCmd)
    Button btnSendCmd;

    @Override
    protected String getTagName() {
        return this.getClass().getName();
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_color_light;
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

    @OnClick({R2.id.redToogle, R2.id.greenToogle, R2.id.yellowToogle, R2.id.btn_sendCmd})
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.redToogle) {
        } else if (viewId == R.id.greenToogle) {
        } else if (viewId == R.id.yellowToogle) {
        } else if (viewId == R.id.btn_sendCmd) {
            sendCmd();
        }
    }

    private void sendCmd() {
        CLightParam.BlConfig config = new CLightParam.BlConfig();
        if (redToogle.isChecked()) {
            config.redOn(Integer.valueOf(redOnMilli.getText().toString()), Integer.valueOf(redOffMilli.getText().toString()));
        }
        if (greenToogle.isChecked()) {
            config.greenOn(Integer.valueOf(greenOnMilli.getText().toString()), Integer.valueOf(greenOffMilli.getText().toString()));
        }

        if (yellowToogle.isChecked()) {
            config.greenOn(Integer.valueOf(yellowOnMilli.getText().toString()), Integer.valueOf(yellowOffMilli.getText().toString()));
        }

        if (beedToogle.isChecked()) {
            config.greenOn(Integer.valueOf(beedOnMilli.getText().toString()), Integer.valueOf(beedOffMilli.getText().toString()));
        }
        config.duration(Integer.valueOf(lastSecond.getText().toString()));

        CLightParam param = new CLightParam(config);

        AndPipe.colorLight().config(param) .sendTo(1, 0, 0);
    }
}
