package com.ebig.athena.okhttp.listener;

/**
 *
 *
 */
public class DisposeDataHandle
{
	public DisposeDataListener mListener = null;

	public String mSource = null;

	public DisposeDataHandle(DisposeDataListener listener)
	{
		this.mListener = listener;
	}


	public DisposeDataHandle(DisposeDataListener listener, String source)
	{
		this.mListener = listener;
		this.mSource = source;
	}
}