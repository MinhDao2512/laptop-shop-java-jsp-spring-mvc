package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.service.OrderDetailService;
import vn.hoidanit.laptopshop.service.OrderService;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    public OrderController(OrderService orderService, OrderDetailService orderDetailService) {
        this.orderService = orderService;
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/admin/order")
    public String getDashboard(Model model) {
        List<Order> orders = this.orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "admin/order/show";
    }

    @GetMapping("/admin/order/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        List<OrderDetail> orderDetails = orderDetailService.getAllByOrder(order);

        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("id", id);
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("userDesc", order.getUser().getRole().getDescription());
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String postUpdateOrder(@ModelAttribute("order") Order order) {
        Order currentOrder = this.orderService.getOrderById(order.getId());
        currentOrder.setStatus(order.getStatus());
        this.orderService.createOrUpdateOrder(currentOrder);
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("id", order.getId());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postDeleteOrder(@ModelAttribute("order") Order order) {
        Order currentOrder = this.orderService.getOrderById(order.getId());
        List<OrderDetail> orderDetails = this.orderDetailService.getAllByOrder(currentOrder);
        if (orderDetails != null) {
            for (OrderDetail od : orderDetails) {
                this.orderDetailService.deleteOrderDetailById(od.getId());
            }
        }

        this.orderService.deleteOrderById(currentOrder.getId());
        return "redirect:/admin/order";
    }
}
