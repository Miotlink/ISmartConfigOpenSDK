package com.miotlink.smart.utils;

import com.miotlink.sdk.entity.FirstData;

import java.util.ArrayList;
import java.util.List;

public interface Utils {

    public interface Consts{
        public static final int SMART_TYPE_AP=0;
        public static final int SMART_TYPE_SA_1=1;
        public static final int SMART_TYPE_7681=2;
        public static final int SMART_TYPE_HF=3;
        public static final int SMART_TYPE_SA_4=4;

        public static List<FirstData> smartList=new ArrayList<>();
    }
}
