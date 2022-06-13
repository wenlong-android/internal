package com.ebig.socket.device;

import com.ebig.log.ELog;
import com.ebig.utils.StrUtils;
import com.ebig.socket.bean.cmd.CmdBean;
import com.ebig.socket.common.CMD;
import com.ebig.socket.entity.IT;
import com.ebig.socket.entity.TypeConstance;
import com.ebig.socket.entity.CmdRequestInfo;
import com.ebig.socket.utils.ModbusCrc16Utils;

/**
 * 指令 =起始符+长度低8位+长度高8位+地址1+地址2+地址3+数据+crcL+crcH+结束符
 * 数据长度=order+地址柜+地址2+地址3+数据长度；没有数据区默认为 4
 * CRC计算域=长度（LenL,LenH）+数据长度
 * */
public class CmdCompiler {
    private int cmdDefaultLen= IT.CMD_DEFALUT_LEN;
    private static CmdBean cmdBean;
    private static class Lazy{
        private static CmdCompiler manager=new CmdCompiler();
    }
    public static CmdCompiler l(){
        return Lazy.manager;
    }
    public CmdCompiler with(CmdRequestInfo taskInfo){
        cmdBean=new CmdBean();
        //设置开头
        cmdBean.setStart(CMD.START);
        //设置命令
        cmdBean.setOrder(taskInfo.order());
        cmdBean.setLenL(taskInfo.cmd().length()/2+cmdDefaultLen);
        cmdBean.setLenH(TypeConstance.C_00);
        cmdBean.setAddress1(taskInfo.cargo());
        cmdBean.setAddress2(taskInfo.layer());
        cmdBean.setAddress3(taskInfo.site());
        cmdBean.setData(taskInfo.cmd());
        cmdBean.setCrc(ModbusCrc16Utils.getCrc(cmdBean));
        //设置结尾
        cmdBean.setEnd(CMD.END);

        taskInfo.setData(taskInfo.cmd());
//        switch (taskInfo.order()){
//            case CType.TypeLock:
//                break;
//            case CType.TypeCardReader:
//                break;
//            case CType.TypeBackLight:
//                break;
//            case CType.TypeScale:
//                break;
//            case CType.TypeScan:
//                break;
//            case CType.TypeFinger:
//                break;
//            case CType.TypeColorLight:
//                break;
//        }
        return Lazy.manager;
    }

    public String makeFinal(){

        return cmdBean.getWholeCmd();
    }

    //指定位
//    public CmdCompiler onlyCargo(int position){
//        cmdBean.setAddress1(CMD.DATA_1);
//        cmdBean.setAddress2(CMD.DATA_0);
//        cmdBean.setAddress3(CMD.DATA_0);
//        return Lazy.manager;
//    }

//    public CmdCompiler openOnlyDoor(){
//        cmdBean.setAddress1(CMD.DATA_1);
//        cmdBean.setAddress2(CMD.DATA_0);
//        cmdBean.setAddress3(CMD.DATA_0);
//        cmdBean.setData(HexUtils.int2Hex(CMD.LOCK_OPEN_ONLY_DOOR)
//                +HexUtils.int2Hex(CMD.LOCK_STATUS_ON));
//        cmdBean.setLenL(CMD.EXTRA_FIXED_LEN+CMD.LOCK_DATA_LENGTH);
//        cmdBean.setLenH(CMD.DATA_0);
//        String crc= ModbusCrc16Utils.getCrc(cmdBean);
//        if (StrUtils.notEmpty(crc)&&crc.length()==4) {
//            cmdBean.setCrcL(crc.substring(0,2));
//            cmdBean.setCrcH(crc.substring(2,4));
//        }
//
//        return Lazy.manager;
//    }

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
        builder.append(cmdBean.getCrc());
        builder.append(cmdBean.getEnd());
        String cmd=builder.toString();
       ELog.print("整体命令为："+cmd);
        return cmd;
    }

    public String makeCrc(){
         /*数据长度=order+地址柜+地址2+地址3+数据长度；没有数据区默认为 4
          CRC计算域=长度（LenL,LenH）+数据长度*/
//
//       String crcAear= cmdBean.getLenL()+cmdBean.getLenH()+cmdBean.getOrder()
//        +cmdBean.getAddress1()+cmdBean.getAddress2()+cmdBean.getAddress3()
//                +cmdBean.getData();
//
       //ELog.print("待生产crc的数据域字符串："+crcAear);

        return ModbusCrc16Utils.getCrc(cmdBean);
    }

}