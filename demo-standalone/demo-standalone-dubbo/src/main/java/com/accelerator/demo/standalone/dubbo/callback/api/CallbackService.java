package com.accelerator.demo.standalone.dubbo.callback.api;

public interface CallbackService {
    
    void addListener(String key, CallbackListener listener);

}
