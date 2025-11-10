package com.workshop4.helloworldbackend.config;

import com.workshop4.helloworldbackend.entity.User;
import com.workshop4.helloworldbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            logger.info("Loading sample data...");
            loadSampleUsers();
            logger.info("Sample data loaded successfully!");
        } else {
            logger.info("Database already contains data, skipping sample data loading.");
        }
    }

    private void loadSampleUsers() {
        // Sample user 1 - สมชาย ใจดี (ตามรูป)
        User user1 = new User();
        user1.setMemberId("LBK001234");
        user1.setFirstName("สมชาย");
        user1.setLastName("ใจดี");
        user1.setEmail("somchai@example.com");
        user1.setPhone("081-234-5678");
        user1.setBirthDate(LocalDate.of(1990, 5, 15));
        user1.setGender("Male");
        user1.setAddress("123 ถนนสุขุมวิท");
        user1.setCity("กรุงเทพมหานคร");
        user1.setCountry("ประเทศไทย");
        user1.setPostalCode("10110");
        user1.setBio("สมาชิก LBK Gold ตั้งแต่ปี 2566");
        user1.setMembershipLevel("Gold");
        user1.setPoints(15420);
        user1.setRegistrationDate(LocalDate.of(2023, 6, 15));
        user1.setIsActive(true);

        // Sample user 2 - สมหญิง รักดี
        User user2 = new User();
        user2.setMemberId("LBK001235");
        user2.setFirstName("สมหญิง");
        user2.setLastName("รักดี");
        user2.setEmail("somying@example.com");
        user2.setPhone("082-345-6789");
        user2.setBirthDate(LocalDate.of(1985, 8, 22));
        user2.setGender("Female");
        user2.setAddress("456 ถนนพระราม 4");
        user2.setCity("กรุงเทพมหานคร");
        user2.setCountry("ประเทศไทย");
        user2.setPostalCode("10120");
        user2.setBio("สมาชิก Platinum ระดับ VIP");
        user2.setMembershipLevel("Platinum");
        user2.setPoints(28750);
        user2.setRegistrationDate(LocalDate.of(2022, 3, 10));
        user2.setIsActive(true);

        // Sample user 3 - วิชัย มั่นคง
        User user3 = new User();
        user3.setMemberId("LBK001236");
        user3.setFirstName("วิชัย");
        user3.setLastName("มั่นคง");
        user3.setEmail("vichai@example.com");
        user3.setPhone("083-456-7890");
        user3.setBirthDate(LocalDate.of(1992, 12, 3));
        user3.setGender("Male");
        user3.setAddress("789 ถนนพระราม 9");
        user3.setCity("กรุงเทพมหานคร");
        user3.setCountry("ประเทศไทย");
        user3.setPostalCode("10310");
        user3.setBio("สมาชิก Silver มาใหม่");
        user3.setMembershipLevel("Silver");
        user3.setPoints(5680);
        user3.setRegistrationDate(LocalDate.of(2024, 1, 20));
        user3.setIsActive(true);

        // Sample user 4 - กานต์ธิดา สวยงาม
        User user4 = new User();
        user4.setMemberId("LBK001237");
        user4.setFirstName("กานต์ธิดา");
        user4.setLastName("สวยงาม");
        user4.setEmail("kanthida@example.com");
        user4.setPhone("084-567-8901");
        user4.setBirthDate(LocalDate.of(1988, 3, 18));
        user4.setGender("Female");
        user4.setAddress("321 ถนนเพชรบุรี");
        user4.setCity("กรุงเทพมหานคร");
        user4.setCountry("ประเทศไทย");
        user4.setPostalCode("10400");
        user4.setBio("สมาชิกที่ไม่ได้ใช้งาน");
        user4.setMembershipLevel("Bronze");
        user4.setPoints(1200);
        user4.setRegistrationDate(LocalDate.of(2023, 9, 5));
        user4.setIsActive(false); // Inactive user for testing

        // Sample user 5 - ธนากร เจริญสุข
        User user5 = new User();
        user5.setMemberId("LBK001238");
        user5.setFirstName("ธนากร");
        user5.setLastName("เจริญสุข");
        user5.setEmail("thanakorn@example.com");
        user5.setPhone("085-678-9012");
        user5.setBirthDate(LocalDate.of(1995, 7, 10));
        user5.setGender("Male");
        user5.setAddress("654 ถนนลาดพร้าว");
        user5.setCity("กรุงเทพมหานคร");
        user5.setCountry("ประเทศไทย");
        user5.setPostalCode("10310");
        user5.setBio("สมาชิก Gold ชอบช้อปปิ้งออนไลน์");
        user5.setMembershipLevel("Gold");
        user5.setPoints(12890);
        user5.setRegistrationDate(LocalDate.of(2023, 11, 25));
        user5.setIsActive(true);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);

        logger.info("Created {} sample users", 5);
    }
}