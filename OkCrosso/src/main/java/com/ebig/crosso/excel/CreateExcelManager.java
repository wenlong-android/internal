package com.ebig.crosso.excel;

import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.ebig.crosso.manager.type.AopDbInfo;
import com.ebig.crosso.manager.type.AopField;
import com.ebig.crosso.manager.type.RecordType;
import com.ebig.crosso.utils.CroStrUtils;
import com.ebig.idl.CommonCall2;
import com.ebig.log.ELog;
import com.ebig.utils.TimeUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class CreateExcelManager implements IExcel {
    public static CreateExcelManager load() {
        return new CreateExcelManager();
    }

    private List<Label> labelList;
    public final static String CROPIMAGEROOT2 = Environment.getExternalStorageDirectory() + "/YDZN_LOG/";

    @Override
    public List<Label> createTitleLable() {
        List<Label> tempList = new ArrayList<>();
        tempList.add(new Label(0, 0, "序号"));
        tempList.add(new Label(1, 0, "发生时间"));
        tempList.add(new Label(2, 0, "记录类型"));
        tempList.add(new Label(3, 0, "记录详情"));
        tempList.add(new Label(4, 0, "内存信息"));
        tempList.add(new Label(5, 0, "线程信息"));
        tempList.add(new Label(6, 0, "堆栈信息"));
        tempList.add(new Label(7, 0, "责任人"));
        tempList.add(new Label(8, 0, "是否修改"));
        return tempList;
    }

    @Override
    public WritableCellFormat getBlackFont() {
        WritableFont font1 = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
        WritableCellFormat format1 = new WritableCellFormat(font1);
        return format1;
    }

    @Override
    public WritableCellFormat getRedFont() {
        WritableFont font1 = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
        WritableCellFormat format1 = new WritableCellFormat(font1);
        try {
            format1.setBackground(Colour.RED);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format1;
    }

    @Override
    public WritableCellFormat getFont(boolean isBlack) {
        if (isBlack) {
            return getBlackFont();
        } else {
            return getRedFont();
        }
    }

    @Override
    public void writeNewExl(List<AopDbInfo> mAopDbInfoList, CommonCall2<Boolean, String> isFinishCall) {
        labelList = new ArrayList<>();
        labelList.addAll(createTitleLable());
        try {
            WritableFont font1 = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
            WritableCellFormat format1 = new WritableCellFormat(font1);
            format1.setAlignment(jxl.format.Alignment.CENTRE);
            for (int i = 0; i < mAopDbInfoList.size(); i++) {
                final AopDbInfo mTimeRecrodBean = mAopDbInfoList.get(i);
                ELog.print("最终生成数据：" + mTimeRecrodBean.toString());
                craetFinalLeabl(mTimeRecrodBean, i, format1);
            }


        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            write(isFinishCall);
        }
    }

    @Override
    public void craetFinalLeabl(AopDbInfo bean, int i, WritableCellFormat format1) {
        WritableCellFormat formatFinal=null;
        if (bean.getEvent() == RecordType.aopCrash ||
                bean.getEvent() == RecordType.exception) {
            formatFinal = getRedFont();
            ELog.print("cratetFinalLeabl:红色标签");
        }else {
            formatFinal=format1;
        }
        System.out.println("fixData==第" + i + "个数据，" + bean.toString());
        Label label_1 = new Label(0, i + 1, (i + 1) + "", formatFinal);
        Label label_2 = new Label(1, i + 1, bean.getTimeStamp(), formatFinal);
        Label label_3 = new Label(2, i + 1, CroStrUtils.getType(bean.getEvent()), formatFinal);
        Label label_4 = new Label(3, i + 1, bean.getDetail(), formatFinal);
        Label label_5 = new Label(4, i + 1, bean.getMemory(), formatFinal);
        Label label_6 = new Label(5, i + 1, bean.getThread(), formatFinal);
        Label label_7 = new Label(6, i + 1, bean.getStack(), formatFinal);
        Label label_8 = new Label(7, i + 1, "WWL", formatFinal);
        Label label_9 = new Label(8, i + 1, "否", formatFinal);
        labelList.add(label_1);
        labelList.add(label_2);
        labelList.add(label_3);
        labelList.add(label_4);
        labelList.add(label_5);
        labelList.add(label_6);
        labelList.add(label_7);
        labelList.add(label_8);
        labelList.add(label_9);
    }

    @Override
    public synchronized void write(CommonCall2<Boolean, String> isFinishCall) {
        WritableWorkbook writeBook = null;
        File file = new File(CROPIMAGEROOT2);
        final String fileName = "医智联Android日志监控" + TimeUtils.getCurrentDate() + ".xls";
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            try {
                writeBook = Workbook.createWorkbook(new File(CROPIMAGEROOT2, fileName));
                WritableSheet writeSheet = writeBook.createSheet("1", 0);
                for (int i = 0; i < labelList.size(); i++) {
                    ELog.print("write===i:" + i + "___" + labelList.get(i).getContents());
                    writeSheet.addCell(labelList.get(i));
                }
                writeBook.write();
            } catch (WriteException e) {
                e.printStackTrace();
                // LogTools.print("WriteException 1;"+e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            isFinishCall.onCommonCall(false, e.getMessage());
        } finally {
            if (writeBook != null) {
                try {
                    writeBook.close();
                } catch (Exception e) {
                    isFinishCall.onCommonCall(false, e.getMessage());
                }
            }
            File file1 = new File(CROPIMAGEROOT2, fileName);
            isFinishCall.onCommonCall(true, file1.getPath());
        }

    }
}
