package com.ebig.socket.dispatchWrite.lcd;

import com.ebig.socket.entity.LcdC;
import com.ebig.socket.entity.LcdMode;
import com.ebig.socket.utils.CrossoStr;

public class LcdParam06OnShelf implements LcdParam    {
    private String name;/*商品名*/
    private String specification; /*规格参数*/
    private String model; /*型号*/
    private String nickName; /*别名*/
    private String  vender; /*厂商名称*/
    private String barCode; /*商品编码*/
    private String identifier; /*识别码*/
    private String mode;  /*显示模式*/
    private String expand1;/*扩展2*/
    private String expand2;/*扩展2*/
    private String expand3; /*扩展3*/
    private String expand4; /*扩展4*/
    private String expand5; /*扩展5*/
    private String expand6;  /*扩展6*/
    private String expand7; /*扩展7*/
    private String expand8;/*扩展8*/
    private String expand9; /*扩展9*/
    private String number; /*数量参数(库存)*/
    private String unit; /*数量单位*/
    private String template;
    public LcdParam06OnShelf() {
        setTemplate("01");
        setMode(LcdMode.modeSelect);
    }

    public String getExpand1() {
        return expand1;
    }

    public void setExpand1(String expand1) {
        this.expand1 = expand1;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    private String getMode() {
        return mode;
    }

    private void setMode(String mode) {
        this.mode = mode;
    }

    public String getExpand2() {
        return expand2;
    }

    public void setExpand2(String expand2) {
        this.expand2 = expand2;
    }

    public String getExpand3() {
        return expand3;
    }

    public void setExpand3(String expand3) {
        this.expand3 = expand3;
    }

    public String getExpand4() {
        return expand4;
    }

    public void setExpand4(String expand4) {
        this.expand4 = expand4;
    }

    public String getExpand5() {
        return expand5;
    }

    public void setExpand5(String expand5) {
        this.expand5 = expand5;
    }

    public String getExpand6() {
        return expand6;
    }

    public void setExpand6(String expand6) {
        this.expand6 = expand6;
    }

    public String getExpand7() {
        return expand7;
    }

    public void setExpand7(String expand7) {
        this.expand7 = expand7;
    }

    public String getExpand8() {
        return expand8;
    }

    public void setExpand8(String expand8) {
        this.expand8 = expand8;
    }

    public String getExpand9() {
        return expand9;
    }

    public void setExpand9(String expand9) {
        this.expand9 = expand9;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String getFinalCmdString() {
        StringBuilder stringBuilder=new StringBuilder();
        //private String name;/*商品名*/
        if (name!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.name,name));
        }
        if (specification!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.specification,specification));
        }
        if (model!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.model,model));
        }
        if (nickName!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.nickName,nickName));
        }
        if (vender!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.vender,vender));
        }
        if (barCode!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.barCode,barCode));
        }
        if (identifier!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.identifier,identifier));
        }
        if (mode!=null){
            stringBuilder.append( "fb01"+template);
            stringBuilder.append( "ff01"+mode);
        }
        if (expand1!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand1,expand1));
        }
        if (expand2!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand2,expand2));
        }
        if (expand3!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand3,expand3));
        }
        if (expand4!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand4,expand4));
        }
        if (expand5!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand5,expand5));
        }
        if (expand6!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand6,expand6));
        }
        if (expand7!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand7,expand7));
        }
        if (expand8!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand8,expand8));
        }
        if (expand9!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.expand9,expand9));
        }
        if (number!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.number,number));
        }
        if (unit!=null){
            stringBuilder.append(CrossoStr.getLcdCmd(LcdC.unit,unit));
        }
        return stringBuilder.toString();
    }
}
