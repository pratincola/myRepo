package com.prateek.android.connexus;

import java.util.List;

/**
 * Created by prateek on 11/8/14.
 */
public interface OnDownloadListenerConnexusImages {
    public void onDownloadSuccess(List<ConnexusImages> c);
    public void onDownloadSuccess(ConnexusImages c);
    public void onDownloadFailure();

}
