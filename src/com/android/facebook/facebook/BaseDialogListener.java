package com.android.facebook.facebook;

import com.app.milesoft.facebook.DialogError;
import com.app.milesoft.facebook.FacebookError;
import com.app.milesoft.facebook.Facebook.DialogListener;



/**
 * Skeleton base class for RequestListeners, providing default error 
 * handling. Applications should handle these error conditions.
 *
 */
public abstract class BaseDialogListener implements DialogListener {

    public void onFacebookError(FacebookError e) {
        e.printStackTrace();
    }

    public void onError(DialogError e) {
        e.printStackTrace();        
    }

    public void onCancel() {        
    }
    
}
