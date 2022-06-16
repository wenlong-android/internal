package com.ebig.socket.device;

import com.ebig.log.ELog;
import com.ebig.utils.StrUtils;
import com.ebig.socket.common.CMD;
import com.ebig.utils.HexUtils;
import com.ebig.socket.utils.ModbusCrc16Utils;

import com.ebig.socket.bean.cmd.CmdBean;
import com.ebig.utils.HexUtils;

public class LockCmdCompiler {
    private static CmdBean cmdBean;
    private static class Lazy{
        private static LockCmdCompiler manager=new LockCmdCompiler();
    }
    public static LockCmdCompiler l(){
        return Lazy.manager;
    }
    public  LockCmdCompiler lock(){
        cmdBean=new CmdBean();
        //设置开头
        cmdBean.setStart(CMD.START);
        //设置结尾
        cmdBean.setEnd(CMD.END);
        //设置命令
        cmdBean.setOrder(CMD.ORDER_LOCK);
        return Lazy.manager;
    }
    //指定柜子
    public  LockCmdCompiler onCargo(int cargoNum){
        cmdBean.setAddress1(cargoNum);
        return Lazy.manager;
    }
    //指定层
    public  LockCmdCompiler onLevel(int level){
        cmdBean.setAddress2(level);
        return Lazy.manager;
    }

    //指定位
    public  LockCmdCompiler onPosition(int position){
        cmdBean.setAddress3(position);
        return Lazy.manager;
    }

    //指定位
    public  LockCmdCompiler onlyCargo(int position){
        cmdBean.setAddress1(CMD.DATA_1);
        cmdBean.setAddress2(CMD.DATA_0);
        cmdBean.setAddress3(CMD.DATA_0);
        return Lazy.manager;
    }

    public  LockCmdCompiler openOnlyDoor(){
        cmdBean.setAddress1(CMD.DATA_1);
        cmdBean.setAddress2(CMD.DATA_0);
        cmdBean.setAddress3(CMD.DATA_0);
        cmdBean.setData(HexUtils.int2Hex(CMD.LOCK_OPEN_ONLY_DOOR)
                +HexUtils.int2Hex(CMD.LOCK_STATUS_ON));
        cmdBean.setLenL(CMD.EXTRA_FIXED_LEN+CMD.LOCK_DATA_LENGTH);
        cmdBean.setLenH(CMD.DATA_0);
        String crc= ModbusCrc16Utils.getCrc(cmdBean);
        cmdBean.setCrc(crc);
//        if (StrUtils.notEmpty(crc)&&crc.length()==4) {
//            cmdBean.setCrcL(crc.substring(0,2));
//            cmdBean.setCrcH(crc.substring(2,4));
//        }

        return Lazy.manager;
    }

    public void print(){
       ELog.print(cmdBean.toString());
    }
    public String getWholeCmd(){
        StringBuilder builder=new StringBuilder();
        builder.append(cmdBean.getStart());
        builder.append(cmdBean.getLenL());
        builder.append(cmdBean.getLenH());
        builder.append(cmdBean.getOrder());
        builder.append(cmdBean.getAddress1());
        builder.append(cmdBean.getAddress2());
        builder.append(cmdBean.getAddress3());
        if (StrUtils.notEmpty(cmdBean.getData())){
            builder.append(cmdBean.getData());
        }
//        builder.append(cmdBean.getCrcL());
//        builder.append(cmdBean.getCrcH());
        builder.append(cmdBean.getCrc());
        builder.append(cmdBean.getEnd());
        String cmd=builder.toString();
       ELog.print("整体命令为："+cmd);
        return cmd;
    }

}