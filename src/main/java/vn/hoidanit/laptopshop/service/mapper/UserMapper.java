package vn.hoidanit.laptopshop.service.mapper;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.dto.UserDTO;

@Service
public class UserMapper {

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setFullName(userDTO.getFirstName() + " " + userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            return user;
        }
    }
}