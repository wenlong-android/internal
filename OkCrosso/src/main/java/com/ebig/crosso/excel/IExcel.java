package com.ebig.crosso.excel;

import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.idl.Common2Call;
import com.ebig.idl.CommonCall2;

import java.util.List;

import jxl.write.Label;
import jxl.write.WritableCellFormat;

public interface IExcel {
    List<Label> createTitleLable();

    WritableCellFormat getBlackFont();

    WritableCellFormat getRedFont();

    WritableCellFormat getFont(boolean isBlack);

    void writeNewExl(List<AopDbInfo> mAopDbInfoList, CommonCall2<Boolean, String> isFinishCall);

    void write(CommonCall2<Boolean, String> isFinishCall);

    void craetFinalLeabl(AopDbInfo bean, int i, final WritableCellFormat format1);
}
