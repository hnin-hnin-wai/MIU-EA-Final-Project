package edu.miu.cs.cs544;

import edu.miu.cs.cs544.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
(scanBasePackages = {"edu.miu.common", "edu.miu.cs.cs544"})
public class Application implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Autowired
	AttendanceRepository scanRecordRepository;
	@Override
	public void run(String... args) throws Exception {

	}
}
