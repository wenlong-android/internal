package com.ebig.socket.dispatch;

import com.ebig.socket.entity.CmdRequestInfo;

import java.util.LinkedList;

public class CmdQueue {
    private LinkedList<CmdRequestInfo> list = new LinkedList();

    public void put(CmdRequestInfo t){ //加入数据
        list.addFirst(t);
    }
    public CmdRequestInfo get(){ //取出先加入的数据
        return  list.removeLast();
    }
    public boolean isEmpt(){
        return  list.isEmpty();
    }

    public int getSize(){
        return  list.size();
    }

}
