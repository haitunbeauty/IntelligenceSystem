package com.manage.intelligence.httpcallbacks;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

public abstract class PostCallBack implements Callback {

    @Override
    public void onStart(Request request) {

    }

    @Override
    public void onCacheSuccess(Response response) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void uploadProgress(Progress progress) {

    }

    @Override
    public void downloadProgress(Progress progress) {

    }

    @Override
    public Object convertResponse(okhttp3.Response response) throws Throwable {
        return null;
    }
}
