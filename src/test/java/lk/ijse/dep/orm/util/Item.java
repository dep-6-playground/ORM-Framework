package lk.ijse.dep.orm.util;

import lk.ijse.dep.orm.annotation.Column;
import lk.ijse.dep.orm.annotation.Entity;
import lk.ijse.dep.orm.annotation.Id;

import java.io.Serializable;

/**
 * @author : Deshan Charuka <d.c.0729439631@gmail.com>
 * @since : 2021-01-20
 **/
@Entity
public class Item implements Serializable {
    @Id
    @Column("item_code")
    private String itemCode;
    @Column("item_description")
    private String des;
    @Column
    private int qty;

    public Item() {
    }

    public Item(String itemCode, String des, int qty) {
        this.itemCode = itemCode;
        this.des = des;
        this.qty = qty;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", des='" + des + '\'' +
                ", qty=" + qty +
                '}';
    }
}
