package com.ebig.socket;
import com.ebig.utils.StrUtils;
import com.ebig.socket.entity.CmdResultInfo;
import com.ebig.utils.HexUtils;

import java.util.List;

public class BaseMatchFactoryHelper {
    private static final int STANDRE_EXTRALEN=4;



    public static List<String> getCmdArray(String cmd) {
        if (StrUtils.empty(cmd)){
            return null;
        }
       return StrUtils.spiltHex(cmd);
    }
    public static String getCmdData(List<String> cmdArray) {
        //获取数据长度
        int len= HexUtils.hex2int(cmdArray.get(1));
        //除了数据区外其他标准必备字段长度为10
        if (cmdArray.size()>STANDRE_EXTRALEN) {
            int cmdArrayLen=cmdArray.size();
            StringBuilder sb=new StringBuilder();
            for (int i=7;i<cmdArrayLen-3;i++){
                sb.append(cmdArray.get(i));
            }
            return sb.toString();
        }
        return "";
    }
}
