package sweetSys;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

public class AccountCreationService {

    private NotificationService notificationService = new NotificationService();
    private Map<String, String> accounts = new HashMap<>(); 

    /**
     * إنشاء حساب جديد وإرسال إشعارات.
     *
     * @param accountType نوع الحساب (User أو Owner أو Supplier)
     * @param name اسم الشخص
     * @param email البريد الإلكتروني
     * @throws MessagingException إذا حدثت مشكلة في إرسال البريد الإلكتروني
     */
    public void createAccount(String accountType, String name, String email) throws MessagingException {
        saveAccount(accountType, name, email);

        // إرسال الإشعارات
        if ("User".equalsIgnoreCase(accountType)) {
            notificationService.notifyUserOfNewAccount(email, name);
        } else if ("Owner".equalsIgnoreCase(accountType)) {
            notificationService.notifyOwnerOfNewAccount(email, name, email);
        }
        notificationService.notifyAdminOfNewAccount("admin@example.com", name, email);
    }

    /**
     * حفظ الحساب في قاعدة البيانات الوهمية.
     *
     * @param accountType نوع الحساب
     * @param name اسم الشخص
     * @param email البريد الإلكتروني
     */
    private void saveAccount(String accountType, String name, String email) {
        // منطق حفظ الحسابات: يتم هنا تخزين الحساب في قاعدة البيانات الوهمية
        // في حالة الاستخدام الفعلي، يجب استبدال هذا بالكود الفعلي لحفظ الحساب في قاعدة البيانات
        accounts.put(email, name);
        System.out.println("Account created: " + accountType + " - " + name + " (" + email + ")");
    }
}
