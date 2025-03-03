package com.chaduvuko.V1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaduvuko.V1.model.Certificate;
import com.chaduvuko.V1.model.Course;
import com.chaduvuko.V1.model.Enrollment;
import com.chaduvuko.V1.model.Lecture;
import com.chaduvuko.V1.model.Module;
import com.chaduvuko.V1.model.Quiz;
import com.chaduvuko.V1.model.QuizQuestion;
import com.chaduvuko.V1.model.User;
import com.chaduvuko.V1.model.Video;
import com.chaduvuko.V1.repository.CertificateRepository;
import com.chaduvuko.V1.repository.CourseRepository;
import com.chaduvuko.V1.repository.EnrollmentRepository;
import com.chaduvuko.V1.repository.LectureRepository;
import com.chaduvuko.V1.repository.ModuleRepository;
import com.chaduvuko.V1.repository.QuizQuestionRepository;
import com.chaduvuko.V1.repository.QuizRepository;
import com.chaduvuko.V1.repository.UserRepository;
import com.chaduvuko.V1.repository.VideoRepository;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/chaduvuko/v1/")
public class ChaduvukoController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private LectureRepository lectureRepository;

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuizQuestionRepository quizQuestionRepository;

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private CertificateRepository certificateRepository;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	private static final int OTP_EXPIRATION_TIME = 300000; // 5 minutes expiration time for OTP
    private Map<String, String> otpStorage = new HashMap<>();
    private Map<String, Long> otpTimestampStorage = new HashMap<>();
    
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
    	
    	System.out.println("Tesing OTP --------------->"+email);
        // Generate OTP
        String otp = generateOtp();
        
        // Check if user exists
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with that email!");
        }

        // Store OTP and its timestamp
        otpStorage.put(email, otp);
        otpTimestampStorage.put(email, System.currentTimeMillis());

        // Send OTP via email
        sendOtpEmail(email, otp);

        return ResponseEntity.ok("OTP sent to your email!");
    }

    // Helper method to generate random OTP
    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    // Helper method to send OTP email
    private void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP for Chaduvuko");
        message.setText("Your OTP is: " + otp);

        javaMailSender.send(message); // Send the email
    }

    // Verify OTP
    @CrossOrigin("http://localhost:4200")
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        // Check if OTP exists and hasn't expired
        if (!otpStorage.containsKey(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP not generated for this email.");
        }

        String storedOtp = otpStorage.get(email);
        Long timestamp = otpTimestampStorage.get(email);

        // Check expiration time (5 minutes)
        if (System.currentTimeMillis() - timestamp > OTP_EXPIRATION_TIME) {
            otpStorage.remove(email);
            otpTimestampStorage.remove(email);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP expired.");
        }

        // Check if the OTP matches
        if (!storedOtp.equals(otp)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP.");
        }

        // OTP valid
        return ResponseEntity.ok("OTP verified successfully.");
    }

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
	
	// Api for Users model

	@CrossOrigin("http://localhost:4200")
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@CrossOrigin("http://localhost:4200")
	@GetMapping("/users/{username}")
	public User getUser(@PathVariable String username) {
		System.out.println(username);
		return userRepository.findByUsername(username);
	}

	@CrossOrigin("http://localhost:4200")
	@PostMapping("/users/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		// Check if username or email already exists
		
		System.out.println("Testing: "+user);
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return ResponseEntity.badRequest().body("Username already taken!");
		}
		if (userRepository.findByEmail(user.getEmail()) != null) {
			return ResponseEntity.badRequest().body("Email is already registered!");
		}
//	
		// Hash the password before saving
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
//
//		// Save the new user
		userRepository.save(user);
		System.out.println("response ---------------"+ ResponseEntity.ok("Registration successful!"));
		return ResponseEntity.ok("Registration successful!");
	}

	// Api for Courses model
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	// Api for modules model
	@GetMapping("/modules")
	public List<Module> getAllModules() {
		return moduleRepository.findAll();
	}

	// Api for lectures model
	@GetMapping("/lectures")
	public List<Lecture> getAllLectures() {
		return lectureRepository.findAll();
	}

	// Api for videos model
	@GetMapping("/videos")
	public List<Video> getAllVideos() {
		return videoRepository.findAll();
	}

	// Api for quizes model
	@GetMapping("/quizes")
	public List<Quiz> getAllQuizes() {
		return quizRepository.findAll();
	}

	// Api for quiz-questions model
	@GetMapping("/quizQuestions")
	public List<QuizQuestion> getAllQuizQuestions() {
		return quizQuestionRepository.findAll();
	}

	// Api for enrollements model
	@GetMapping("/enrollments")
	public List<Enrollment> getAllEnrollments() {
		return enrollmentRepository.findAll();
	}

	// Api for certificates model
	@GetMapping("/certificates")
	public List<Certificate> getAllCertificates() {
		return certificateRepository.findAll();
	}

}
