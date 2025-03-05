package com.bjpowernode.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    // 姓名
    private String name;

    //状态
    private String status;

    //余额
    private BigDecimal money;

    // 是否已借书
    private boolean isLend;

    public User(String id, String name, String status, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public boolean isLend() {
        return isLend;
    }

    public void setLend(boolean lend) {
        isLend = lend;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", money=" + money +
                ", isLend=" + isLend +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isLend == user.isLend && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(status, user.status) && Objects.equals(money, user.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, money, isLend);
    }

    public User() {
    }

    public User(String name, String status, String id, BigDecimal money, boolean isLend) {
        this.name = name;
        this.status = status;
        this.id = id;
        this.money = money;
        this.isLend = isLend;
    }

}
