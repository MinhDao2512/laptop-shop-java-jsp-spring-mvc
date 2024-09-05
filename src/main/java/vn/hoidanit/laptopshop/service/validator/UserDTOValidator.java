package vn.hoidanit.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.service.UserService;
import vn.hoidanit.laptopshop.service.dto.UserDTO;

@Service
public class UserDTOValidator implements ConstraintValidator<UserDTOConstraint, UserDTO> {

    private final UserService userService;

    public UserDTOValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(UserDTO value, ConstraintValidatorContext context) {
        boolean valid = true;
        // logic
        if (value.getPassword() == "") {
            context.buildConstraintViolationWithTemplate("Password không thể để trống.")
                    .addPropertyNode("password")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        } else {
            if (value.getPassword().length() < 8) {
                context.buildConstraintViolationWithTemplate("Password phải có ít nhất 8 ký tự.")
                        .addPropertyNode("password")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            } else if (!value.getPassword().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$")) {
                context.buildConstraintViolationWithTemplate(
                        "Password phải bao gồm ký tự in hoa, in thường và ký tự đặc biệt.")
                        .addPropertyNode("password")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
        }

        if (value.getConfirmPassword() == "") {
            context.buildConstraintViolationWithTemplate("ConfirmPassword không được bỏ trống.")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        } else {
            if (!value.getPassword().equals(value.getConfirmPassword())) {
                context.buildConstraintViolationWithTemplate("ConfirmPassword không trùng khớp với Password.")
                        .addPropertyNode("confirmPassword")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
        }

        if (value.getFirstName() == "") {
            context.buildConstraintViolationWithTemplate("FirstName không được bỏ trống.")
                    .addPropertyNode("firstName")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        if (value.getEmail() == "") {
            context.buildConstraintViolationWithTemplate("Email không được bỏ trống.")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        } else {
            if (!value.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
                context.buildConstraintViolationWithTemplate("Email chưa đúng định dạng: example@gamil.com")
                        .addPropertyNode("email")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            } else if (userService.checkExistsUserByEmail(value.getEmail())) {
                context.buildConstraintViolationWithTemplate("Email đã tồn tại, vui long sử dụng email khác.")
                        .addPropertyNode("email")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
                valid = false;
            }
        }
        // end logic
        return valid;
    }

}