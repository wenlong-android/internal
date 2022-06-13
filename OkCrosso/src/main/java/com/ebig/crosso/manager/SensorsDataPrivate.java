package com.ebig.crosso.manager;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Keep;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;


import com.ebig.crosso.bean.aop.AopDeviceEntity;
import com.ebig.crosso.bean.aop.AopClickEntity;
import com.ebig.crosso.manager.type.RecordType;

import com.ebig.crosso.utils.CroDensityUtil;
import com.ebig.crosso.utils.CroThread;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Locale;

/*public*/ class SensorsDataPrivate {
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"
            + ".SSS", Locale.CHINA);


    @Keep
    protected static void trackViewOnClick(DialogInterface dialogInterface, int whichButton) {
        try {
            Dialog dialog = null;
            if (dialogInterface instanceof Dialog) {
                dialog = (Dialog) dialogInterface;
            }

            if (dialog == null) {
                return;
            }
            AopClickEntity details = new AopClickEntity();
            Context context = dialog.getContext();
            //将Context转成Activity
            Activity activity = SensorsDataPrivate.getActivityFromContext(context);

            if (activity == null) {
                activity = dialog.getOwnerActivity();
            }
            if (activity != null) {
                details.setActivity(activity.getClass().getCanonicalName());
            }

            Button button = null;
            if (dialog instanceof android.app.AlertDialog) {
                button = ((android.app.AlertDialog) dialog).getButton(whichButton);
            } else if (dialog instanceof androidx.appcompat.app.AlertDialog) {
                button = ((androidx.appcompat.app.AlertDialog) dialog).getButton(whichButton);
            }

            if (button != null) {
                details.setViewContent(button.getText().toString());
            }
            details.setViewType("Dialog");

            CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Keep
    protected static void trackViewOnClick(DialogInterface dialogInterface, int whichButton, boolean isChecked) {
        try {
            Dialog dialog = null;
            if (dialogInterface instanceof Dialog) {
                dialog = (Dialog) dialogInterface;
            }

            if (dialog == null) {
                return;
            }

            Context context = dialog.getContext();
            //将Context转成Activity
            Activity activity = SensorsDataPrivate.getActivityFromContext(context);
            AopClickEntity details = new AopClickEntity();
            if (activity == null) {
                activity = dialog.getOwnerActivity();
            }
            if (activity != null) {
                details.setActivity(activity.getClass().getCanonicalName());
            }

            ListView listView = null;
            if (dialog instanceof android.app.AlertDialog) {
                listView = ((android.app.AlertDialog) dialog).getListView();
            } else if (dialog instanceof androidx.appcompat.app.AlertDialog) {
                listView = ((androidx.appcompat.app.AlertDialog) dialog).getListView();
            }

            if (listView != null) {
                ListAdapter listAdapter = listView.getAdapter();
                Object object = listAdapter.getItem(whichButton);
                if (object != null) {
                    if (object instanceof String) {
                        details.setViewContent(object.toString());
                    }
                }
            }
            details.setViewType("Dialog");
            CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void trackViewOnClick(CompoundButton view, boolean isChecked) {
        try {
            Context context = view.getContext();
            if (context == null) {
                return;
            }
            Activity activity = SensorsDataPrivate.getActivityFromContext(context);
            AopClickEntity details = new AopClickEntity();
            try {
                String idString = context.getResources().getResourceEntryName(view.getId());
                if (!TextUtils.isEmpty(idString)) {
                    details.setViewId(idString);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (activity != null) {
                details.setActivity(activity.getClass().getCanonicalName());
            }

            String viewText = null;
            String viewType;
            if (view instanceof CheckBox) {
                viewType = "CheckBox";
                CheckBox checkBox = (CheckBox) view;
                if (!TextUtils.isEmpty(checkBox.getText())) {
                    viewText = checkBox.getText().toString();
                }
            } else if (view instanceof SwitchCompat) {
                viewType = "SwitchCompat";
                SwitchCompat switchCompat = (SwitchCompat) view;
                if (!TextUtils.isEmpty(switchCompat.getTextOn())) {
                    viewText = switchCompat.getTextOn().toString();
                }
            } else if (view instanceof ToggleButton) {
                viewType = "ToggleButton";
                ToggleButton toggleButton = (ToggleButton) view;
                if (isChecked) {
                    if (!TextUtils.isEmpty(toggleButton.getTextOn())) {
                        viewText = toggleButton.getTextOn().toString();
                    }
                } else {
                    if (!TextUtils.isEmpty(toggleButton.getTextOff())) {
                        viewText = toggleButton.getTextOff().toString();
                    }
                }
            } else if (view instanceof RadioButton) {
                viewType = "RadioButton";
                RadioButton radioButton = (RadioButton) view;
                if (!TextUtils.isEmpty(radioButton.getText())) {
                    viewText = radioButton.getText().toString();
                }
            } else {
                viewType = view.getClass().getCanonicalName();
            }

            //Content
            if (!TextUtils.isEmpty(viewText)) {
                details.setViewContent(viewText);
            }

            if (!TextUtils.isEmpty(viewType)) {
                details.setViewType(viewType);
            }
            CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Keep
    protected static void trackExpandableListViewChildOnClick(ExpandableListView expandableListView, View view,
                                                              int groupPosition, int childPosition) {
        try {
            Context context = expandableListView.getContext();
            if (context == null) {
                return;
            }
            AopClickEntity details = new AopClickEntity();
            Activity activity = SensorsDataPrivate.getActivityFromContext(context);
            if (activity != null) {
                details.setActivity(activity.getClass().getCanonicalName());
            }

            if (childPosition != -1) {
                details.setViewContent(String.format(Locale.CHINA, "%d:%d", groupPosition, childPosition));
            } else {
                details.setViewContent(String.format(Locale.CHINA, "%d", groupPosition));
            }

            String idString = SensorsDataPrivate.getViewId(expandableListView);
            if (!TextUtils.isEmpty(idString)) {
                details.setViewId(idString);
            }
            details.setViewType("ExpandableListView");

            String viewText = null;
            if (view instanceof ViewGroup) {
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    viewText = SensorsDataPrivate.traverseViewContent(stringBuilder, (ViewGroup) view);
                    if (!TextUtils.isEmpty(viewText)) {
                        viewText = viewText.substring(0, viewText.length() - 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (!TextUtils.isEmpty(viewText)) {
                details.setViewContent(viewText);
            }

            CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Keep
    protected static void trackViewOnClick(android.widget.AdapterView adapterView, View view, int position) {
        try {
            Context context = adapterView.getContext();
            if (context == null) {
                return;
            }
            AopClickEntity details = new AopClickEntity();

            Activity activity = SensorsDataPrivate.getActivityFromContext(context);
            String idString = SensorsDataPrivate.getViewId(adapterView);
            if (!TextUtils.isEmpty(idString)) {
                details.setViewId(idString);
            }

            if (activity != null) {
                details.setActivity(activity.getClass().getCanonicalName());
            }

            details.setViewContent(String.valueOf(position));
            if (adapterView instanceof Spinner) {
                details.setViewType("Spinner");
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    if (item instanceof String) {
                        details.setViewContent(item.toString());
                    }
                }
            } else {
                if (adapterView instanceof ListView) {

                    details.setViewType("ListView");
                } else if (adapterView instanceof GridView) {
                    details.setViewType("GridView");
                }

                String viewText = null;
                if (view instanceof ViewGroup) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        viewText = SensorsDataPrivate.traverseViewContent(stringBuilder, (ViewGroup) view);
                        if (!TextUtils.isEmpty(viewText)) {
                            viewText = viewText.substring(0, viewText.length() - 1);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    viewText = SensorsDataPrivate.getElementContent(view);
                }
                //$element_content
                if (!TextUtils.isEmpty(viewText)) {
                    details.setViewContent(viewText);
                }
            }
            CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * MenuItem 被点击，自动埋点
     *
     * @param object   Object
     * @param menuItem MenuItem
     */
    @Keep
    protected static void trackViewOnClick(Object object, MenuItem menuItem) {
        try {
            Context context = null;
            if (object instanceof Context) {
                context = (Context) object;
            }
            AopClickEntity details = new AopClickEntity();


            details.setViewType("menuItem");
            details.setViewContent(menuItem.getTitle().toString());
            if (context != null) {
                String idString = null;
                try {
                    idString = context.getResources().getResourceEntryName(menuItem.getItemId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(idString)) {
                    details.setViewId(idString);
                }

                Activity activity = SensorsDataPrivate.getActivityFromContext(context);
                if (activity != null) {
                    details.setActivity(activity.getClass().getCanonicalName());
                }
            }

            CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * View 被点击，自动埋点
     *
     * @param view View
     */
    @Keep
    public static void trackViewOnClick(View view) {
        CroThread.getIns().runOnMainThread(new Runnable() {
            @Override
            public void run() {
                propertyValuesHolderDown(view);
            }
        });
        AopClickEntity details = new AopClickEntity();
        getTragetInfo(view, details);
        CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
    }

    private static void getTragetInfo(View view, AopClickEntity details) {
        details.setViewType(SensorsDataPrivate.getElementType(view));
        details.setViewId(SensorsDataPrivate.getViewId(view));
        details.setViewContent(SensorsDataPrivate.getElementContent(view));
        Activity activity = SensorsDataPrivate.getActivityFromView(view);
        if (activity != null) {
            details.setActivity(activity.getClass().getCanonicalName());
        }
    }

    public static AopDeviceEntity getAopDeviceEntity(Context context) {
        AopDeviceEntity info = new AopDeviceEntity();
        info.setManufacturer(Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER);
        if (TextUtils.isEmpty(Build.MODEL)) {
            info.setModel("UNKNOWN");
        } else {
            info.setModel(Build.MODEL.trim());
        }
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        info.set$screen(CroDensityUtil.px2dip(context, displayMetrics.widthPixels) + "x" + CroDensityUtil.px2dip(context, displayMetrics.heightPixels));
        info.setVersion(Build.VERSION.RELEASE == null ? "UNKNOWN" : Build.VERSION.RELEASE);
        return info;

    }

    /**
     * 获取 Android ID
     *
     * @param mContext Context
     * @return String
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context mContext) {
        String androidID = "";
        try {
            androidID = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return androidID;
    }

    /**
     * 获取 view 的 anroid:id 对应的字符串
     *
     * @param view View
     * @return String
     */
    private static String getViewId(View view) {
        String idString = null;
        try {
            if (view.getId() != View.NO_ID) {
                idString = view.getContext().getResources().getResourceEntryName(view.getId());
            }
        } catch (Exception e) {
            //ignore
        }
        return idString;
    }

    private static String getElementType(View view) {
        if (view == null) {
            return null;
        }

        String viewType = null;
        if (view instanceof CheckBox) { // CheckBox
            viewType = "CheckBox";
        } else if (view instanceof SwitchCompat) {
            viewType = "SwitchCompat";
        } else if (view instanceof RadioButton) { // RadioButton
            viewType = "RadioButton";
        } else if (view instanceof ToggleButton) { // ToggleButton
            viewType = "ToggleButton";
        } else if (view instanceof Button) { // Button
            viewType = "Button";
        } else if (view instanceof CheckedTextView) { // CheckedTextView
            viewType = "CheckedTextView";
        } else if (view instanceof TextView) { // TextView
            viewType = "TextView";
        } else if (view instanceof ImageButton) { // ImageButton
            viewType = "ImageButton";
        } else if (view instanceof ImageView) { // ImageView
            viewType = "ImageView";
        } else if (view instanceof RatingBar) {
            viewType = "RatingBar";
        } else if (view instanceof SeekBar) {
            viewType = "SeekBar";
        }
        return viewType;
    }

    private static String traverseViewContent(StringBuilder stringBuilder, ViewGroup root) {
        try {
            if (root == null) {
                return stringBuilder.toString();
            }

            final int childCount = root.getChildCount();
            for (int i = 0; i < childCount; ++i) {
                final View child = root.getChildAt(i);

                if (child.getVisibility() != View.VISIBLE) {
                    continue;
                }

                if (child instanceof ViewGroup) {
                    traverseViewContent(stringBuilder, (ViewGroup) child);
                } else {
                    CharSequence viewText = null;
                    if (child instanceof CheckBox) {
                        CheckBox checkBox = (CheckBox) child;
                        viewText = checkBox.getText();
                    } else if (child instanceof SwitchCompat) {
                        SwitchCompat switchCompat = (SwitchCompat) child;
                        viewText = switchCompat.getTextOn();
                    } else if (child instanceof RadioButton) {
                        RadioButton radioButton = (RadioButton) child;
                        viewText = radioButton.getText();
                    } else if (child instanceof ToggleButton) {
                        ToggleButton toggleButton = (ToggleButton) child;
                        boolean isChecked = toggleButton.isChecked();
                        if (isChecked) {
                            viewText = toggleButton.getTextOn();
                        } else {
                            viewText = toggleButton.getTextOff();
                        }
                    } else if (child instanceof Button) {
                        Button button = (Button) child;
                        viewText = button.getText();
                    } else if (child instanceof CheckedTextView) {
                        CheckedTextView textView = (CheckedTextView) child;
                        viewText = textView.getText();
                    } else if (child instanceof TextView) {
                        TextView textView = (TextView) child;
                        viewText = textView.getText();
                    } else if (child instanceof ImageView) {
                        ImageView imageView = (ImageView) child;
                        if (!TextUtils.isEmpty(imageView.getContentDescription())) {
                            viewText = imageView.getContentDescription().toString();
                        }
                    }

                    if (!TextUtils.isEmpty(viewText)) {
                        stringBuilder.append(viewText.toString());
                        stringBuilder.append("-");
                    }
                }
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return stringBuilder.toString();
        }
    }

    @Keep
    protected static void trackTabHost(String tabName) {
        AopClickEntity details = new AopClickEntity();
        try {

            JSONObject properties = new JSONObject();

            properties.put("$element_type", "TabHost");
            properties.put("$element_content", tabName);
            CrossoDataAPI.getInstance().track(RecordType.aopUserClick, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 View 上显示的文本
     *
     * @param view View
     * @return String
     */
    private static String getElementContent(View view) {
        if (view == null) {
            return null;
        }

        CharSequence viewText = null;
        if (view instanceof CheckBox) { // CheckBox
            CheckBox checkBox = (CheckBox) view;
            viewText = checkBox.getText();
        } else if (view instanceof SwitchCompat) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            viewText = switchCompat.getTextOn();
        } else if (view instanceof RadioButton) { // RadioButton
            RadioButton radioButton = (RadioButton) view;
            viewText = radioButton.getText();
        } else if (view instanceof ToggleButton) { // ToggleButton
            ToggleButton toggleButton = (ToggleButton) view;
            boolean isChecked = toggleButton.isChecked();
            if (isChecked) {
                viewText = toggleButton.getTextOn();
            } else {
                viewText = toggleButton.getTextOff();
            }
        } else if (view instanceof Button) { // Button
            Button button = (Button) view;
            viewText = button.getText();
        } else if (view instanceof CheckedTextView) { // CheckedTextView
            CheckedTextView textView = (CheckedTextView) view;
            viewText = textView.getText();
        } else if (view instanceof TextView) { // TextView
            TextView textView = (TextView) view;
            viewText = textView.getText();
        } else if (view instanceof SeekBar) {
            SeekBar seekBar = (SeekBar) view;
            viewText = String.valueOf(seekBar.getProgress());
        } else if (view instanceof RatingBar) {
            RatingBar ratingBar = (RatingBar) view;
            viewText = String.valueOf(ratingBar.getRating());
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            if (!TextUtils.isEmpty(imageView.getContentDescription())) {
                viewText = imageView.getContentDescription().toString();
            }
        }
        if (viewText != null) {
            return viewText.toString();
        }
        return null;
    }

    /**
     * 获取 View 所属 Activity
     *
     * @param view View
     * @return Activity
     */
    private static Activity getActivityFromView(View view) {
        Activity activity = null;
        if (view == null) {
            return null;
        }

        try {
            Context context = view.getContext();
            if (context != null) {
                if (context instanceof Activity) {
                    activity = (Activity) context;
                } else if (context instanceof ContextWrapper) {
                    while (!(context instanceof Activity) && context instanceof ContextWrapper) {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }

    @TargetApi(11)
    private static String getToolbarTitle(Activity activity) {
        try {
            ActionBar actionBar = activity.getActionBar();
            if (actionBar != null) {
                if (!TextUtils.isEmpty(actionBar.getTitle())) {
                    return actionBar.getTitle().toString();
                }
            } else {
                if (activity instanceof AppCompatActivity) {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
                    androidx.appcompat.app.ActionBar supportActionBar = appCompatActivity.getSupportActionBar();
                    if (supportActionBar != null) {
                        if (!TextUtils.isEmpty(supportActionBar.getTitle())) {
                            return supportActionBar.getTitle().toString();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取 Activity 的 title
     *
     * @param activity Activity
     * @return Activity 的 title
     */
    @TargetApi(11)
    private static String getActivityTitle(Activity activity) {
        try {
            if (activity != null) {
                try {
                    String activityTitle = null;
                    if (!TextUtils.isEmpty(activity.getTitle())) {
                        activityTitle = activity.getTitle().toString();
                    }

                    String toolbarTitle = getToolbarTitle(activity);
                    if (!TextUtils.isEmpty(toolbarTitle)) {
                        activityTitle = toolbarTitle;
                    }

                    if (TextUtils.isEmpty(activityTitle)) {
                        PackageManager packageManager = activity.getPackageManager();
                        if (packageManager != null) {
                            ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 0);
                            if (activityInfo != null) {
                                if (!TextUtils.isEmpty(activityInfo.loadLabel(packageManager))) {
                                    activityTitle = activityInfo.loadLabel(packageManager).toString();
                                }
                            }
                        }
                    }

                    return activityTitle;
                } catch (Exception e) {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Activity getActivityFromContext(Context context) {
        Activity activity = null;
        try {
            if (context != null) {
                if (context instanceof Activity) {
                    activity = (Activity) context;
                } else if (context instanceof ContextWrapper) {
                    while (!(context instanceof Activity) && context instanceof ContextWrapper) {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activity;
    }

    private static void addIndentBlank(StringBuilder sb, int indent) {
        try {
            for (int i = 0; i < indent; i++) {
                sb.append('\t');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String formatJson(String jsonStr) {
        try {
            if (null == jsonStr || "".equals(jsonStr)) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            char last;
            char current = '\0';
            int indent = 0;
            boolean isInQuotationMarks = false;
            for (int i = 0; i < jsonStr.length(); i++) {
                last = current;
                current = jsonStr.charAt(i);
                switch (current) {
                    case '"':
                        if (last != '\\') {
                            isInQuotationMarks = !isInQuotationMarks;
                        }
                        sb.append(current);
                        break;
                    case '{':
                    case '[':
                        sb.append(current);
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            indent++;
                            addIndentBlank(sb, indent);
                        }
                        break;
                    case '}':
                    case ']':
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            indent--;
                            addIndentBlank(sb, indent);
                        }
                        sb.append(current);
                        break;
                    case ',':
                        sb.append(current);
                        if (last != '\\' && !isInQuotationMarks) {
                            sb.append('\n');
                            addIndentBlank(sb, indent);
                        }
                        break;
                    default:
                        sb.append(current);
                }
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void propertyValuesHolderDown(final View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0.9f, 0.9f, 0.91f, 0.92f, 0.93f, 0.92f, 0.91f, 0.90f, 0.89f, 0.88f, 0.89f, 0.90f, 0.91f, 0.92f, 0.94f, 0.95f, 0.96f, 0.97f, 0.98f, 0.99f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.9f, 0.9f, 0.91f, 0.92f, 0.93f, 0.92f, 0.91f, 0.90f, 0.89f, 0.88f, 0.89f, 0.90f, 0.91f, 0.92f, 0.94f, 0.95f, 0.96f, 0.97f, 0.98f, 0.99f, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.9f, 0.9f, 0.91f, 0.92f, 0.93f, 0.92f, 0.91f, 0.90f, 0.89f, 0.88f, 0.89f, 0.90f, 0.91f, 0.92f, 0.94f, 0.95f, 0.96f, 0.97f, 0.98f, 0.99f, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(200).start();

    }
}
