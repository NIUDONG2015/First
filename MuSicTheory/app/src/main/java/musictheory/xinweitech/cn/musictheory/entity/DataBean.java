package musictheory.xinweitech.cn.musictheory.entity;

import java.io.Serializable;

/**
 * Created by niudong on 2017/1/13.
 */


public class DataBean implements Serializable {

    private String image;
    private String text;

    public DataBean(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
