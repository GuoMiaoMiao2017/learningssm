package com.guomiaomiao.learningssm.other;

/**
 * Created by 15295 on 2018/6/11.
 */
public class Single {
    private static  Single s = new Single();
    private Single(){}
    public static Single getSingle(){
        return s;
    }
    public static int count;

    public int getCount() {
        return count++;
    }
}
