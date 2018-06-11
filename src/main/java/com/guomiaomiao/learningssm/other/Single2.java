package com.guomiaomiao.learningssm.other;

import org.springframework.stereotype.Component;

/**
 * Created by 15295 on 2018/6/11.
 */
@Component
public class Single2 {
    int count;
    public int getCount() {
        return count++;
    }
}
