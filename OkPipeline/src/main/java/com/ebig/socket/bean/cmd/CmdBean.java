package com.ebig.socket.bean.cmd;


import com.ebig.log.ELog;
import com.ebig.utils.HexUtils;
import com.ebig.utils.HexUtils;
import com.ebig.utils.StrUtils;

public class CmdBean extends BaseCmd {
    private String start;
    private String lenL;
    private String lenH;
    private String order;
    private String address1;
    private String address2;
    private String address3;
    private String data;
    private String crc;
    private String end;
    private String hostIp;
    private String type;

    public CmdBean() {
    }

    public String getStart() {
        return start;
    }

    public String getLenL() {
        return lenL;
    }

    public String getLenH() {
        return lenH;
    }

    public String getOrder() {
        return order;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getData() {
        return data;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    //    public String getCrc() {
//        return crc;
//    }

    public String getEnd() {
        return end;
    }
//=====================


    public void setStart(String start) {
        this.start = start;
    }

    public void setLenL(String lenL) {
        this.lenL = lenL;
    }

    public void setLenH(String lenH) {
        this.lenH = lenH;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public void setData(String data) {
        this.data = data;
    }


    public void setEnd(String end) {
        this.end = end;
    }
//=====================


    public void setStart(int start) {
        this.start = HexUtils.int2Hex(start);
    }

    public void setLenL(int lenL) {
        this.lenL = HexUtils.int2Hex(lenL);
    }

    public void setLenH(int lenH) {
        this.lenH = HexUtils.int2Hex(lenH);
    }

    public void setOrder(int order) {
        this.order = HexUtils.int2Hex(order);
    }

    public void setAddress1(int address1) {
        this.address1 = HexUtils.int2Hex(address1);
    }

    public void setAddress2(int address2) {
        this.address2 = HexUtils.int2Hex(address2);
    }

    public void setAddress3(int address3) {
        this.address3 = HexUtils.int2Hex(address3);
    }

    public void setData(int data) {
        this.data = HexUtils.int2Hex(data);
    }

//    public void setCrcH(int crcH) {
//        this.crcH = HexUtils.int2Hex(crcH) ;
//    }
//
//    public void setCrcL(int crcL) {
//        this.crcL =  HexUtils.int2Hex(crcL);
//    }

    public void setEnd(int end) {
        this.end = HexUtils.int2Hex(end);
    }

    @Override
    public String toString() {
        return "CmdBean{" +
                "start='" + start + '\'' +
                ", lenL='" + lenL + '\'' +
                ", lenH='" + lenH + '\'' +
                ", order='" + order + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", data='" + data + '\'' +
//                ", crcH='" + crcH + '\'' +
//                ", crcL='" + crcL + '\'' +
                ", crc='" + crc + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    @Override
    public String getWholeCmd() {
        if (StrUtils.empty(getData())) {
            ELog.print("大协议 >> >> >> >>[开头:" + getStart() + "]"
                    + "[长度:" + getLenL() + getLenH() + "]" +
                    "[类型:" + getOrder() + "]" +
                    "[地址:" + getAddress1() + getAddress2() + getAddress3() + "]"
                    + "[CRC:" + getCrc() + "]" +
                    "[结束" + getEnd() + "]");
            return getStart() + getLenL() + getLenH() + getOrder() + getAddress1() + getAddress2() + getAddress3() + getData()
                    + getCrc() + getEnd();

        }
       ELog.print("大协议 >> >> >> >>[开头:" + getStart() + "]"
                + "[长度:" + getLenL() + getLenH() + "]" +
                "[类型:" + getOrder() + "]" +
                "[地址:" + getAddress1() + getAddress2() + getAddress3() + "]" +
                "[数据:" + getData() + "]" +
                "[CRC:" + getCrc() + "]" +
                "[结束" + getEnd() + "]");
        return getStart() + getLenL() + getLenH() + getOrder()+ getAddress1() + getAddress2() + getAddress3()
                + getData() + getCrc() + getEnd();
    }


    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    @Override
    public String getHostIp() {
        return hostIp;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }
}
