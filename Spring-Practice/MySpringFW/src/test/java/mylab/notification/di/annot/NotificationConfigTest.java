package mylab.notification.di.annot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.notification.di.annot.config.NotificationConfig;

@ExtendWith(SpringExtension.class) 
@ContextConfiguration(classes = NotificationConfig.class) 
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testNotificationManager() {
        // 1. NotificationManager의 레퍼런스가 Not Null 인지 검증
        assertNotNull(notificationManager, "NotificationManager가 null인 확인");

        // 2. 이메일 서비스 검증
        EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
        assertNotNull(emailService, "이메일 서비스가 null인지 확인");
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // 3. SMS 서비스 검증
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
        assertNotNull(smsService, "SMS 서비스가 null인지 확인");
        assertEquals("SKT", smsService.getProvider());

        // 4. 실제 메서드 실행
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}