
package com.xzydonate.basesdk.mvp;

import android.support.annotation.NonNull;

public interface IView {

    void showLoading();

    void hideLoading();

    void showMessage(@NonNull String message);

}
