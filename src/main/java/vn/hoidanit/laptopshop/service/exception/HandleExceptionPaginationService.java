package vn.hoidanit.laptopshop.service.exception;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class HandleExceptionPaginationService {
    public int isExistsPageParameter(Optional<String> pageOptional) {
        try {
            if (pageOptional.isPresent()) {
                return Integer.parseInt(pageOptional.get());
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }
}
