package com.junie.callerblock.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by niejun on 2018/1/29.
 */
@Entity
public class CallNumber {


    @Id
    private Long id;

    private String number;

    private String name;

    private int type; //in / out

    private String province;

    private String city;

    private String des;

    private long calltime;

    @Generated(hash = 998075950)
    public CallNumber(Long id, String number, String name, int type,
            String province, String city, String des, long calltime) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.type = type;
        this.province = province;
        this.city = city;
        this.des = des;
        this.calltime = calltime;
    }

    @Generated(hash = 84234860)
    public CallNumber() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public long getCalltime() {
        return this.calltime;
    }

    public void setCalltime(long calltime) {
        this.calltime = calltime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
