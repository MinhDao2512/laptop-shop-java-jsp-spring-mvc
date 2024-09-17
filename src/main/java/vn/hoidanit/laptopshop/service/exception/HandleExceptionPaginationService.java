package vn.hoidanit.laptopshop.service.exception;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.service.dto.ProductCriterialDTO;

@Service
public class HandleExceptionPaginationService {
    public int isExistsPageParameter(ProductCriterialDTO productCriterialDTO) {
        try {
            if (productCriterialDTO.getPage().isPresent()) {
                return Integer.parseInt(productCriterialDTO.getPage().get());
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }
}
