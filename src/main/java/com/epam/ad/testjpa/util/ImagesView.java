package com.epam.ad.testjpa.util;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class ImagesView {

    private List<String> images;
//    private String effect = "fade";

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 6; i++) {
            images.add("nature" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }
//    public String getEffect() {
//        return effect;
//    }
//
//    public void setEffect(String effect) {
//        this.effect = effect;
//    }
}
