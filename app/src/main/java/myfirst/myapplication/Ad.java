package myfirst.myapplication;

/**
 * Created by Bare on 2017/10/26.
 */

public class Ad {
    //private int iconResId;
    private String img;
    private String intro;

    /*public Ad(int iconResId, String intro) {
        super();
        this.iconResId = iconResId;
        this.intro = intro;
    }*/

    public Ad(String img, String intro) {
        this.img = img;
        this.intro = intro;
    }

    /*public int getIconResId() {
        return iconResId;
    }*/

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIntro() {
        return intro;
    }

}
