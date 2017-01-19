package musictheory.xinweitech.cn.musictheory.entity;

import java.io.Serializable;

/**
 * Created by niudong on 2017/1/16.
 */


public class CheckBoxBean implements Serializable {
    public CheckBoxBean(String name, int id, Boolean checkStatus) {
        this.name = name;
        this.id = id;
        this.checkStatus = checkStatus;
    }

    public CheckBoxBean(Boolean checkStatus, String name) {
        this.name = name;
        this.checkStatus = checkStatus;
    }

    public String name;
    public int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean checkStatus;

    public Boolean getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Boolean checkStatus) {
        this.checkStatus = checkStatus;
    }
}
