package registrationClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import loginServer.UserData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.util.Scanner;


public class RegistrationClient {

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final ObjectMapper objectMapper = new ObjectMapper();
        final HttpClient client = HttpClient.newHttpClient();

        try {
            System.out.println("Enter a Username");
            System.out.print("Input > ");
            final String username = in.nextLine();

            System.out.println("Enter a Password");
            System.out.print("Input > ");
            final String password = in.nextLine();

            System.out.println("Enter an E-mail address");
            System.out.print("Input > ");
            final String email = in.nextLine();

            final String uri = MessageFormat
                    .format("http://localhost:8080/login/{0}/{1}/{2}", username, password, email);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri))
                    .POST(HttpRequest.BodyPublishers.ofString("")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final String body = response.body();
            if (response.statusCode() == 200) {
                final UserData userData =
                        objectMapper.readValue(body, UserData.class);
                System.out.println("Registration was successful!");
                System.out.println("User Data > " + userData);
            } else {
                if (body != null && !body.isEmpty()) {
                    System.out.println(body);
                } else {
                    System.out.println(response.toString());
                }
            }
            System.out.println();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
