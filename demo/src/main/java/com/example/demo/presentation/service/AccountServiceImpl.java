package com.example.demo.presentation.service;
import com.example.demo.persistence.Repository.RoleRepository;
import com.example.demo.persistence.Repository.UserRepository;
import com.example.demo.persistence.entity.ErrorNotification;
import com.example.demo.persistence.entity.Role;
import com.example.demo.persistence.entity.User;
import com.example.demo.presentation.model.request.User.PostDeleteUser;
import com.example.demo.presentation.model.request.User.PostUpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountServiceImpl {
    @Autowired
    UserRepository nguoiDungRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> dangKyNguoiDung(User nguoiDung) {
        if (nguoiDungRepository.existsByUsername(nguoiDung.getUsername())) {
            return ResponseEntity.badRequest().body(new ErrorNotification("Tên Đăng Nhập Đã Tồn Tại"));
        }

        String encode = bCryptPasswordEncoder.encode(nguoiDung.getPassword());
        nguoiDung.setPassword(encode);
        nguoiDung.setActiveCode(UUID.randomUUID().toString());
        nguoiDung.setActive(false);
        if (nguoiDung.getRoleList() == null) {
            nguoiDung.setRoleList(new ArrayList<>());
        }
        Role defaultRole = roleRepository.findByRoleName("ROLE_USER");
        nguoiDung.getRoleList().add(defaultRole);

        nguoiDungRepository.save(nguoiDung);

        sendEMailActive(nguoiDung.getEmail(), nguoiDung.getActiveCode());
        return ResponseEntity.ok("Đã Đăng Ký Thành Công");
    }
    public ResponseEntity<?> capNhatNguoiDung(PostUpdateUser request) {

        Optional<User> optionalUser = nguoiDungRepository.findById(request.getUserId());
        if (!optionalUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorNotification("Người dùng không tồn tại"));
        }

        User nguoiDungHienTai = optionalUser.get();

        // Cập nhật thông tin cơ bản
        nguoiDungHienTai.setFirstName(request.getFirstName());
        nguoiDungHienTai.setLastName(request.getLastName());
        nguoiDungHienTai.setEmail(request.getEmail());
        nguoiDungHienTai.setPhoneNumber(request.getPhoneNumber());

        // Mã hóa mật khẩu nếu có
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            nguoiDungHienTai.setPassword(encodedPassword);
        }
        if (request.getRoleList() != null && !request.getRoleList().isEmpty()) {
            List<Role> roleListFromDb = new ArrayList<>();
            for (Role role : request.getRoleList()) {
                if (role.getRoleId() != null) {
                    Role existingRole = roleRepository.findById(role.getRoleId()).orElse(null);
                    if (existingRole != null) {
                        roleListFromDb.add(existingRole);
                    }
                }
            }
            nguoiDungHienTai.setRoleList(roleListFromDb);
        }
        nguoiDungRepository.save(nguoiDungHienTai);
        return ResponseEntity.ok("Cập nhật thông tin người dùng thành công");
    }

    public ResponseEntity<?> xoaNguoiDung(PostDeleteUser request) {
        Optional<User> optionalNguoiDung = nguoiDungRepository.findById(request.getUserId());
        if (!optionalNguoiDung.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorNotification("Người dùng không tồn tại"));
        }
        nguoiDungRepository.deleteById(request.getUserId());

        return ResponseEntity.ok("Xóa người dùng thành công");
    }

    public void sendEMailActive(String email,String activeCode){
        String subject="kích hoạt tài khoản của bạn tại web";
        String text="Vui lòng sử dụng mã sau để kich hoat <"+email+">:<br/> <h1>"+activeCode+"</h1>";
        emailService.sendEmail("dodinhtuanyb2k4@gmail.com",email,text,subject);
    }

}
