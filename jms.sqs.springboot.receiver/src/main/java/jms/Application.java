package jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    private static final String DEFAULT_EMAIL_BODY = "HELLO WORLD !!!";
    private static final String EXIT_COMMAND = "exit";

    public static void main(String[] args) {
        // Launch the application
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        // Initialize the jms message sending service
        EmailJmsSenderService emailJmsSenderService = context.getBean("emailJmsSenderService", EmailJmsSenderService.class);

        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        String emailBody = null;
        do {
            //  prompt for the user's name
            System.out.print("Enter the message body, leave empty for default message, type " + EXIT_COMMAND + " to terminate.\n");
            emailBody = scanner.nextLine();

        } while (actOnUserInput(emailJmsSenderService, emailBody));

        // Terminate the application
        context.close();
    }

    /**
     * @return True in case the user wants to continue running the application, false otherwise.
     */
    private static boolean actOnUserInput(EmailJmsSenderService emailJmsSenderService, String userInput) {

        if (StringUtils.isEmpty(userInput)) {
            emailJmsSenderService.sendEmail(new Email("info@example.com", DEFAULT_EMAIL_BODY));
        } else if (userInput.equalsIgnoreCase(EXIT_COMMAND)) {
            return false;
        } else {
            emailJmsSenderService.sendEmail(new Email("info@example.com", userInput));
        }

        return true;
    }
}
