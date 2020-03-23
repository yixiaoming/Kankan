package org.xiao.kaiyan;

import org.junit.Test;
import org.xiao.kaiyan.api.RetrofitManager;
import org.xiao.kaiyan.entity.AllRec;
import org.xiao.kaiyan.entity.AllRecItem;

import java.io.IOException;

public class KaiyanTest {
    @Test
    public void getAllRec() throws IOException {
        AllRec allRec = RetrofitManager.INSTANCE.getKaiyanApi().getAllRec().execute().body();
        for (AllRecItem item : allRec.getItemList()) {
            System.out.println(item.getData().getDataType());
        }
    }

    @Test
    public void getFindMore() throws IOException {
        AllRec allRec = RetrofitManager.INSTANCE.getKaiyanApi().getFindMore().execute().body();
        for (AllRecItem item : allRec.getItemList()) {
            System.out.println(item.getType());
            System.out.println(item.getData().getTitle());
            System.out.println(item.getData().getText());
            System.out.println();
        }
    }

    @Test
    public void getDaily() throws IOException {
        AllRec allRec = RetrofitManager.INSTANCE.getKaiyanApi().getFeed().execute().body();
        for (AllRecItem item : allRec.getItemList()) {
            System.out.println(item.getData().getDataType());
        }
    }
}
