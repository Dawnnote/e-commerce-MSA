package com.shipshoe.userservice.service;

import com.shipshoe.userservice.Address;
import com.shipshoe.userservice.dto.UserFormDto;
import com.shipshoe.userservice.dto.UserRequestDto;
import com.shipshoe.userservice.dto.UserResponseDto;
import com.shipshoe.userservice.entity.User;
import com.shipshoe.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserResponseDto save(UserRequestDto request) {
        Address address = new Address(request.getState(), request.getCity(),
                                        request.getZipcode(), request.getStreet());
        User user = new User(request, address);
        validateUser(user);

        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public void validateUser(User user) {
        User findUser = userRepository.findByEmail(user.getEmail());
        if (findUser==null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public UserResponseDto findUser(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(IllegalArgumentException::new);

        if (findUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserFormDto form) {

        User findUser = userRepository.findById(id).orElseThrow();


        String encode = passwordEncoder.encode(form.getPassword());
        form.setPassword(encode);

        findUser.updateUser(form);
        userRepository.save(findUser);

        return new UserResponseDto(findUser);
    }
}
