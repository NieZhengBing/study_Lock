package com.nzb.rwlock;
/**
 * @author M
 * @create 2018/2/13
 */
public class GoodsVo {
    private final String id;
    private int totalSaleNumber;
    private int depotNumber;

    public GoodsVo(String id, int totalSaleNumber, int depotNumber) {
        this.id = id;
        this.totalSaleNumber = totalSaleNumber;
        this.depotNumber = depotNumber;
    }

    public int getTotalSaleNumber() {
        return totalSaleNumber;
    }

    public int getDepotNumber() {
        return depotNumber;
    }

    public void setGoodsVoNumber(int changeNumber) {
        this.totalSaleNumber += changeNumber;
        this.depotNumber -= changeNumber;
    }
}
