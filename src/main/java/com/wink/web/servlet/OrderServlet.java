package com.wink.web.servlet;

import com.wink.domain.Account;
import com.wink.domain.Order;
import com.wink.domain.ResultInfo;
import com.wink.domain.RouteOrder;
import com.wink.service.IOrderService;
import com.wink.service.impl.OrderServiceImpl;
import com.wink.util.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;


/**
 * @Author: Mr.Ye
 * @Description: TODO(酒店订单相关操作)
 */
@WebServlet("/order/*")
public class OrderServlet extends BaseServlet {

    /**
     * 新增线路订单
     * @param request
     * @param response
     * @throws Exception
     */
    public void OrderRoute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultInfo info = new ResultInfo();

        IOrderService IOrderService = new OrderServiceImpl();

        //1.获取参数
        Map<String, String[]> map = request.getParameterMap();
        if (map.size()==0 || map.size()!=9){
            info.setFlag(false);
            info.setErrorMsg("预定失败");
            String json = writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //2.1满足预定条件，查询账户余额
        String uid = map.get("uid")[0];
        String sid = map.get("sid")[0];
//        Account account = IOrderService.findAccount(Integer.parseInt(uid));
        Double paymoney = Double.valueOf(map.get("paymoney")[0]);
//        if (account.getMoney()< paymoney){
//            info.setFlag(false);
//            info.setErrorMsg("您的账户余额不足，请充值！");
//            String json = writeValueAsString(info);
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(json);
//            return;
//        }

        //2.2余额可以进行预定，进行转账
//        int target = IOrderService.findBySid(Integer.parseInt(sid));
//        IOrderService.transfer(Integer.parseInt(uid),target,paymoney);

        //2.3转账成功，生成订单，封装对象
        RouteOrder routeOrder = new RouteOrder();
        try {
            BeanUtils.populate(routeOrder,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //生成订单号
        routeOrder.setOrderId(UuidUtil.getUuid());

        //下单时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);
        routeOrder.setOrdertime(dateTime);

        //3.调用订单方法
        IOrderService.addROrder(routeOrder);

        //4.响应结果
        info.setFlag(true);
        writeValue(info,response);
    }
    /**
     * 新增酒店订单
     * @param request
     * @param response
     * @throws IOException
     */
    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ResultInfo info = new ResultInfo();

        IOrderService IOrderService = new OrderServiceImpl();

        //1.获取参数
        Map<String, String[]> map = request.getParameterMap();
        if (map.size()==0 || map.size()!=11){
            info.setFlag(false);
            info.setErrorMsg("预定失败");
            String json = writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //2.查询入住酒店和日期，判断是否可以入住
        String uid = map.get("uid")[0];
        String hid = map.get("hid")[0];
        String beginday = map.get("beginday")[0];
        String endday = map.get("endday")[0];
        //2.1调用service方法查询
        int count= IOrderService.selectOrder(uid,hid,beginday,endday,beginday,endday);
        if (count!=0){//已经存在预定
            info.setFlag(false);
            info.setErrorMsg("您在该时间内已预定！");
            String json = writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //2.2满足预定条件，查询账户余额
//        Account account = IOrderService.findAccount(Integer.parseInt(uid));
        Double paymoney = Double.valueOf(map.get("paymoney")[0]);
//        if (account.getMoney()< paymoney){
//            info.setFlag(false);
//            info.setErrorMsg("您的账户余额不足，请充值！");
//            String json = writeValueAsString(info);
//            response.setContentType("application/json;charset=utf-8");
//            response.getWriter().write(json);
//            return;
//        }

        //2.3余额可以进行预定，进行转账
        int target = IOrderService.findByhid(Integer.parseInt(hid));
        IOrderService.transfer(Integer.parseInt(uid),target,paymoney);

        //2.4转账成功，生成订单，封装对象
        Order order = new Order();
        try {
            BeanUtils.populate(order,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //生成订单号
        order.setOrderId(UuidUtil.getUuid());

        //下单时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTime = LocalDateTime.now().format(formatter);
        order.setOrdertime(dateTime);

        //3.调用订单方法
        IOrderService.addOrder(order);

        //4.响应结果
        info.setFlag(true);
        writeValue(info,response);
    }
}
