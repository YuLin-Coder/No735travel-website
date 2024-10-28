package com.wink.domain;

import com.admin.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Mr.Ye
 * @Description: TODO(旅游线路商品实体类)
 */
public class Route implements Serializable {
    private Integer rid;//线路id
    private String rname;//线路名称
    private Double price;//价格
    private String routeIntroduce;//线路介绍
    private String rimage;//缩略图
    private MultipartFile rimageStr;
    private Integer rflag;   //是否上架，0代表没有上架，1代表是上架
    private String rflagStr;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date rdate;   //上架时间
    private String rdateStr;
    private String isThemeTour;//是否主题旅游，0代表不是，1代表是
    private String isThemeTourStr;
    private Integer count;//收藏数量
    private Integer cid;//所属分类
    private Integer sid;//所属商家

    private Category category;//所属分类
    private Seller seller;//所属商家
    private List<RouteImg> routeImgList;//商品详情图片列表

    public MultipartFile getRimageStr() {
        return rimageStr;
    }

    public void setRimageStr(MultipartFile rimageStr) {
        this.rimageStr = rimageStr;
    }

    public String getRdateStr() {
        if (rdate!=null){
            rdateStr = DateUtils.date2String(rdate,"yyyy-MM-dd HH:mm");
        }
        return rdateStr;
    }

    public void setRdateStr(String rdateStr) {
        this.rdateStr = rdateStr;
    }

    public String getRflagStr() {
        if (rflag == 0)
            rflagStr="否";
        if (rflag==1)
            rflagStr="是";
        return rflagStr;
    }

    public void setRflagStr(String rflagStr) {
        this.rflagStr = rflagStr;
    }

    public String getIsThemeTourStr() {
        if ("0".equals(isThemeTour))
            isThemeTourStr="否";
        if ("1".equals(isThemeTour))
            isThemeTourStr="是";
        return isThemeTourStr;
    }

    public void setIsThemeTourStr(String isThemeTourStr) {
        this.isThemeTourStr = isThemeTourStr;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public Integer getRflag() {
        return rflag;
    }

    public void setRflag(Integer rflag) {
        this.rflag = rflag;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

    public Route(Integer rid, String rname, Double price, String routeIntroduce, Integer rflag, Date rdate, String isThemeTour, Integer count, Integer cid, String rimage, Integer sid) {
        this.rid = rid;
        this.rname = rname;
        this.price = price;
        this.routeIntroduce = routeIntroduce;
        this.rflag = rflag;
        this.rdate = rdate;
        this.isThemeTour = isThemeTour;
        this.count = count;
        this.cid = cid;
        this.rimage = rimage;
        this.sid = sid;
    }

    public Route() {
    }
}
