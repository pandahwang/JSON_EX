package com.panda.JSON_EX.JSON_EX.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    // json 파일을 읽어와서 DB에 저장하는 메소드
    public void update() {
        try {
            // ClassPathResource로 user-data.json 파일을 로드
            ClassPathResource resource = new ClassPathResource("user-data.json");

            // JSON 파일을 읽어 User 객체 리스트로 변환
            List<User> users = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<User>>() {
            });

            // 변환한 User 객체 리스트를 DB에 저장
            if(checkKey()){
            userRepository.saveAll(users);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // json 데이터 key 값을 확인 하는 메서드

    public boolean checkKey() {
        boolean isKeyChanged = false;
        try {
            Set<String> keys = new LinkedHashSet<>();
            keys.add("user_id");
            keys.add("username");
            keys.add("post_count");
            
            // ClassPathResource로 user-data.json 파일을 로드
            ClassPathResource resource = new ClassPathResource("user-data.json");

            // JSON 파일을 읽어 Map 객체 리스트로 변환
            List<Map<String, Object>> data = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Map<String, Object>>>() {
            });

            // json 파일 key 값 확인
            System.out.println(data.get(0).keySet());
            System.out.println(data.get(0).keySet().getClass());
            if (data.get(0).keySet().equals(keys)) {
                System.out.println("key 값 일치");
                isKeyChanged = true;
            } else {
                System.out.println("key 값 불일치");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return isKeyChanged;
    }


//    // 모든 사용자의 post_count를 출력하는 메소드
//    public int sum() {
//        int sum = 0;
//        List<User> users = userRepository.findAll();
//        for(User user : users) {
//            sum += user.getPost_count();
//        }
//        return sum;
//    }


//    // 특정 사용자의 post_count를 출력하는 메소드
//    public int sumByID(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        return user.get().getPost_count();
//    }


    // 특정 사용자의 이름을 가져옴
    public String getUserName(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get().getUsername();
    }



    // 모든 사용자의 post_count를 담은 User 객체를 return하는 메서드
    public User sum() {
        User user = new User();
        int sum = 0;
        List<User> users = userRepository.findAll();
        for(User u : users) {
            sum += u.getPost_count();
        }
        user.setPost_count(sum);
        user.setUsername("전체 ");
        return user;
    }

    // 특정 id들의 user 리스트를 반환하는 메서드
    public List<User> sumByIDs(Long id) {
        List<User> users = new ArrayList<>();
        List<Long> ids = cutId(id);                                 // 자른 id들을 ids에 가져옴
        try{
            for(Long i : ids) {
                Optional<User> user = userRepository.findById(i);  // 자른 id들로 user를 찾음
                users.add(user.get());                             // 찾은 user를 users에 추가
            }
        } catch(NoSuchElementException e){                          // id가 없을 때
            System.out.println("id가 존재하지 않습니다.");
        }

        return users;                                               // 찾은 user들을 리스트 반환
    }

    // 경로 변수 id를 잘라서 List ids로 반환하는 메서드
    public List<Long> cutId(Long id) {
        List<Long> ids = new ArrayList<>();

        String idToString = id + "";
        int length = idToString.length();
        int count = length/3;   // 자를 id의 개수
        try{
            for(int i=0; i<count; i++) {
                int start = i * 3;                                  // 자르기 시작 인덱스 번호
                int end = (i + 1) * 3;                              // 자르기 끝 인덱스 번호
                String subId = idToString.substring(start, end);    // id를 3자리씩 자름
                ids.add(Long.parseLong(subId));                     // ids에 자른 id를 Long으로 변환 후 추가
            }
        } catch(IndexOutOfBoundsException e){                       // id가 3자리씩 입력되지 않았을 때
            System.out.println("id는 3자리씩 입력해주세요");
        }
        return ids;
    }
}