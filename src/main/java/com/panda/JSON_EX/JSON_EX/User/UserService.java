package com.panda.JSON_EX.JSON_EX.User;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    // json 파일을 읽어와서 DB에 저장하는 메소드
    public void update() {
        try{

        // ClassPathResource로 user-data.json 파일을 로드
        ClassPathResource resource = new ClassPathResource("user-data.json");

        // JSON 파일을 읽어 User 객체 리스트로 변환
        List<User> users = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<User>>() {});

        // 변환한 User 객체 리스트를 DB에 저장
        userRepository.saveAll(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 모든 사용자의 post_count를 출력하는 메소드
    public int sum() {
        int sum = 0;
        List<User> users = userRepository.findAll();
        for(User user : users) {
            sum += user.getPost_count();
        }
        return sum;
    }

    // 특정 사용자의 post_count를 출력하는 메소드
    public int sumByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get().getPost_count();
    }

    public String getUserName(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get().getUsername();
    }
}
