package com.butterfly.spotter.processor;

import com.butterfly.spotter.model.AbstractHttpObject;
import com.google.gson.Gson;

/**
 * @author : Nadim
 * @since : 12/14/13
 */
public abstract class AbstractProcessor<T> {
    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getJsonFromObject() {
        if (content != null) {
            return new Gson().toJson(content);
        }
        ///check it
        return null;
    }
    public abstract void process();
}
