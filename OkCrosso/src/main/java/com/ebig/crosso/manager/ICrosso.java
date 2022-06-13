package com.ebig.crosso.manager;

import android.content.Context;

public interface ICrosso {
    public Crosso defaultConfig();
    public Crosso debug(boolean debug);
    public Crosso stackTrace(long frequency);
    public Crosso cleanFrequency(int day);
    public Crosso ram();
    public Crosso hardWare();
    public Crosso server();
    public Crosso consume();
    public Crosso exception();
    public Crosso userClick();
    public Crosso crash();
    public void start();


}
