package com.ebig.medical.demo.fragment;

import androidx.annotation.StringRes;

public interface BaseView {
    /**
     * 显示错误消息提示
     *
     * @param msg
     */
    void showErrorMsg(CharSequence msg);

    /**
     * 显示错误消息提示
     *
     * @param msgId 消息引用id
     */
    void showErrorMsg(@StringRes int msgId);

    /**
     * 显示消息提示
     *
     * @param msg
     */
    void showMsg(CharSequence msg);

    /**
     * 显示消息提示
     *
     * @param msgId 消息引用id
     */
    void showMsg(@StringRes int msgId);


    /**
     * 显示加载进度动画
     * <p>
     * 不建议使用此方法，因为没有关联到Dialog关闭时的一些处理，比如：当正在请求网络，显示了正在加载的Dialog，
     * 而用户不想等待，强制按返回键时，即可认为用户放弃此次请求，此时，应该同时取消网络请求；
     * 因此建议使用重载方法{@link BaseView#showLoading(Object)},如传入Disposable为null,效果与此方法一致，即不关联网络请求
     * <p>
     * 显示加载进度动画
     * <p>
     * 关联到加载进度条Dialog,实现Dialog关闭时同步取消网络请求(取消正在请求的网络连接)
     */
    void showLoading(Object cancel);

    /**
     * 隐藏加载进度动画
     *
     * @param cancel
     */
    void dismissLoading(Object cancel);

    /**
     * 加载进度条关闭了
     */
    void onLoadViewDismissed();

    /**
     * 关闭View
     */
    void finishView();
}
