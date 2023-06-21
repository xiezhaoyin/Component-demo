
package com.xzydonate.basesdk.mvp;

import androidx.annotation.NonNull;

public interface IView {

    void showLoading();

    void hideLoading();

    void showMessage(@NonNull String message);

}
