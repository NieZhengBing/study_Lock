package com.nzb.rwlock;
/**
 * @author M
 * @create 2018/2/13
 */
public class NumSyn implements IGoodsNum{

    private GoodsVo goods;

    @Override
    public synchronized GoodsVo getGoodsNumber() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.goods;
    }

    @Override
    public synchronized void setGoodsNumber(int changeNumber) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.goods.setGoodsVoNumber(changeNumber);
    }
}
