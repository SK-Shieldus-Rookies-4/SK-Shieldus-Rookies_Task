package mylab.student.di.xml;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab.student.di.xml")
public class StudentSpringTest {

	  @Autowired
	    @Qualifier("javaCourse")
	    Course course;
	    
	    @Resource
	    GradeService gradeService;
	    
	    @Test
	    public void testCourse() {
	        assertNotNull(course);
	        assertEquals("C101", course.getCourseId());
	        assertEquals("자바 프로그래밍", course.getCourseName());
	        assertEquals(3, course.getStudents().size());
	        assertEquals(85.0, course.getAverageScore(), 0.01); // (95+88+72)/3
	    }

	    @Test
	    public void testGradeService() {
	        assertNotNull(gradeService);
	        List<Student> highScoreStudents = gradeService.getHighScoreStudents(90);
	        assertEquals(1, highScoreStudents.size());
	        assertEquals("홍길동", highScoreStudents.get(0).getName());

	        assertEquals("A", gradeService.calculateGrade("S001"));
	        assertEquals("B", gradeService.calculateGrade("S002"));
	        assertEquals("C", gradeService.calculateGrade("S003"));
	    }
}
