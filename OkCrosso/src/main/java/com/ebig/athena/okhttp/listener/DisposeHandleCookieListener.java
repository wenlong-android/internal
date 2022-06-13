package com.ebig.athena.okhttp.listener;

import java.util.ArrayList;
public interface DisposeHandleCookieListener extends DisposeDataListener
{
	public void onCookie(ArrayList<String> cookieStrLists);
}
