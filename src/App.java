import java.net.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.Scanner;
import net.sf.json.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user id: ");

        String id = scanner.nextLine();
        scanner.close();

        URL url = new URL("https://discord.com/api/v9/users/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bot ODkxNTc0NTAzNTI2NzY4NzUx.YVAVdg.3FZkVXmCQrsB2FRUXic_zKqRX2g"); // Replace this with your token
        connection.setRequestProperty("Content-Type", "application/json");

        connection.setDoOutput(true);
        connection.connect();
        BufferedReader br = null;
        if (100 <= connection.getResponseCode() && connection.getResponseCode() <= 399) {
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }
        String responseBody = br.lines().collect(Collectors.joining());

        JSONObject json = (JSONObject) JSONSerializer.toJSON(responseBody);        
        String userid = json.getString("id");
        String username = json.getString("username");
        String avatar = json.getString("avatar");
        String discriminator = json.getString("discriminator");
        String public_flags = json.getString("public_flags");
        String banner = json.getString("banner");
        String banner_color = json.getString("banner_color");
        String accent_color = json.getString("accent_color");

        System.out.println("----------------------------");
        System.out.print("User ID: " + userid + "\nUsername: " + username + "\nAvatar link: https://cdn.discordapp.com/avatars/" + userid + "/" + avatar + ".png\n" + "Discriminator: " + discriminator + "\nPublic Flags: " + public_flags + "\nBanner: " + banner + "\nBanner Color: " + banner_color + "\nAccent Color: " + accent_color);

        connection.disconnect();
        
    }
}
