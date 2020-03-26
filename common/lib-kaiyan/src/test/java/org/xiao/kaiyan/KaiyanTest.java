package org.xiao.kaiyan;

import org.junit.Test;
import org.xiao.kaiyan.api.RetrofitManager;
import org.xiao.kaiyan.entity.CardList;
import org.xiao.kaiyan.entity.Card;

import java.io.IOException;

public class KaiyanTest {
    @Test
    public void getAllRec() throws IOException {
        CardList cardList = RetrofitManager.INSTANCE.getKaiyanApi().getAllRec().execute().body();
        for (Card card : cardList.getItemList()) {
            System.out.println(card.getType());
            System.out.println(card.getData().getTitle());
            System.out.println(card.getData().getText());
            System.out.println();
        }
    }

    @Test
    public void getFindMore() throws IOException {
        CardList cardList = RetrofitManager.INSTANCE.getKaiyanApi().getFindMore().execute().body();
        for (Card card : cardList.getItemList()) {
            System.out.println(card.getType());
            System.out.println(card.getData().getTitle());
            System.out.println(card.getData().getText());
            System.out.println();
        }
    }

    @Test
    public void getDaily() throws IOException {
        CardList cardList = RetrofitManager.INSTANCE.getKaiyanApi().getFeed().execute().body();
        for (Card card : cardList.getItemList()) {
            System.out.println(card.getType());
            System.out.println(card.getData().getTitle());
            System.out.println(card.getData().getText());
            System.out.println();
        }
    }
}
