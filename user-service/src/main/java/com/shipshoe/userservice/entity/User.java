package com.shipshoe.userservice.entity;


import com.shipshoe.userservice.Address;
import com.shipshoe.userservice.BaseEntity;
import com.shipshoe.userservice.dto.UserFormDto;
import com.shipshoe.userservice.dto.UserRequestDto;
import com.shipshoe.userservice.dto.auth.SignUpRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String name;

    @Embedded
    private Address address;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식에 맞지 않습니다.")
    private String phone;

    @Enumerated(value = EnumType.STRING)
    private RoleType role;

    protected User() {
    }

    public User(UserRequestDto request, Address address) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        this.address = address;
        this.phone = request.getPhone();
        this.role = RoleType.USER;
    }

    public User(SignUpRequestDto dto) {
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.name = dto.getName();
        this.phone = dto.getPhone();
        this.address = new Address(dto.getState(), dto.getCity(), dto.getZipcode(), dto.getStreet());
        this.role = RoleType.USER;
    }

    public void updateUser(UserFormDto form) {
        this.password = form.getPassword();
        this.phone = form.getPhone();
        this.address = new Address(form.getState(), form.getCity(), form.getZipcode(), form.getStreet());
    }
}
