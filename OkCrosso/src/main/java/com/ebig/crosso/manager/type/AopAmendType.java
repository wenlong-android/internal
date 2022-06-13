package com.ebig.crosso.manager.type;

import androidx.annotation.StringDef;

@StringDef({AopAmendType.unAmend,/*维修改*/
        AopAmendType.amending,/*修改中*/
        AopAmendType.amended,/*已修改*/
        AopAmendType.cannotAmend,/*无法修改*/
        AopAmendType.later/*延后修改*/
})
public @interface AopAmendType {
    final static String unAmend = "0";
    final static String amending = "1";
    final static String amended = "2";;
    final static String cannotAmend = "3";
    final static String later = "4";
}
