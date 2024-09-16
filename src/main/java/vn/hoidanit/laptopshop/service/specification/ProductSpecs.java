package vn.hoidanit.laptopshop.service.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpecs {
    // Case 1: Min-price
    public static Specification<Product> queryByMinPrice(Double price) {
        return (root, query, builder) -> builder.ge(root.get(Product_.PRICE), price);
    }

    // Case 2: Max-price
    public static Specification<Product> queryByMaxPrice(Double price) {
        return (root, query, builder) -> builder.le(root.get(Product_.PRICE), price);
    }

    // Case 3: Factory
    public static Specification<Product> queryByFactory(String factory) {
        return (root, query, builder) -> builder.equal(root.get(Product_.FACTORY), factory);
    }

    // Case 4: Multiple Factory
    public static Specification<Product> queryByMultiFactory(List<String> factory) {
        return (root, query, builder) -> builder.in(root.get(Product_.FACTORY)).value(factory);
    }

    // Case 5: 10-toi-15-trieu && 15-toi-20-trieu
    public static Specification<Product> matchPrice(double min, double max) {
        return (root, query, builder) -> builder.and(
                builder.le(root.get(Product_.PRICE), max),
                builder.ge(root.get(Product_.PRICE), min));
    }

    // Case 6: Multi Range Price
    public static Specification<Product> matchMultiPrice(double min, double max) {
        return (root, query, builder) -> builder.between(root.get(Product_.PRICE), min, max);
    }

}
