package com.tony.binderpool;

import android.os.RemoteException;

/**
 * Created by user on 12/22/15.
 */
public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
