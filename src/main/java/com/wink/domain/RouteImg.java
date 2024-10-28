package com.wink.domain;

import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;

/**
 * @Author: Mr.Ye
 * @Description: TODO(旅游线路图片实体类)
 */
public class RouteImg implements Serializable {
    private Integer rgid;//商品图片id
    private Integer rid;//旅游商品id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图
    private MultipartFile bigFile;
    private MultipartFile smallFile;

    public MultipartFile getBigFile() {
        return bigFile;
    }

    public void setBigFile(MultipartFile bigFile) {
        this.bigFile = bigFile;
    }

    public MultipartFile getSmallFile() {
        return smallFile;
    }

    public void setSmallFile(MultipartFile smallFile) {
        this.smallFile = smallFile;
    }

    public int getRgid() {
        return rgid;
    }

    public void setRgid(int rgid) {
        this.rgid = rgid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public RouteImg(int rgid, int rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public RouteImg() {
    }
}
