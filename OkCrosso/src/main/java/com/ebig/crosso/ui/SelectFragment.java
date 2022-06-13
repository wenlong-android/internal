package com.ebig.crosso.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ebig.crosso.R;

import java.util.List;


public class SelectFragment extends Fragment implements View.OnClickListener {

    private View mView;
    private RelativeLayout rl_finish;
    private TextView tvstart,tvend,tvSelect;
    private Button btn_select;
    private FilterActivity mFilterDialog;

    public SelectFragment() {

    }
   private FilterCall fragmentCall;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_select, container, false);
        rl_finish=(RelativeLayout) mView.findViewById(R.id.rl_finish);
        tvstart=(TextView)mView.findViewById(R.id.tvstart);
        tvend=(TextView)mView.findViewById(R.id.tvend);
        tvSelect=(TextView) mView.findViewById(R.id.tvSelect);
        btn_select=(Button)mView.findViewById(R.id.btn_select);
        btn_select.setOnClickListener(this);
        return mView;
    }

    public void addListenner(FilterCall filterCall) {
        this.fragmentCall=filterCall;
    }

    @Override
    public void onClick(View v) {
//        if (v.getId()==R.id.tvFliter){
//            mFilterDialog=new FilterActivity(getActivity());
//            mFilterDialog.setFilterCall(new FilterCall() {
//                @Override
//                public void onSelectResult(long start, long end, List<String> list) {
//                    if (fragmentCall!=null) {
//                        fragmentCall.onSelectResult(start,end,list);
//                    }
//                }
//            });
//            mFilterDialog.show();
//        }else if (v.getId()==R.id.rl_finish){
//            getActivity().finish();
//        }
    }




}
