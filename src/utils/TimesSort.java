package utils;

import bean.ViewBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class TimesSort {

    public static ArrayList<ViewBean> dataCompare(ArrayList<ViewBean> list, boolean isNew) throws ParseException {
        for (int i = 0; i < list.size() - 1; i++) {// 控制趟数
            for (int j = 0; j < list.size() - i - 1; j++) {
                ViewBean listj = list.get(j);
                ViewBean listjj = list.get(j + 1);
                if (isNew) {
                    if (listj.getData().getTime() < listjj.getData().getTime()) {
                        ViewBean tmp = listj;
                        list.set(j, listjj);
                        list.set(j + 1, tmp);
                    }
                } else {
                    if (listj.getData().getTime() > listjj.getData().getTime()) {
                        ViewBean tmp = listj;
                        list.set(j, listjj);
                        list.set(j + 1, tmp);
                    }
                }
            }
        }
        return list;
    }
}
