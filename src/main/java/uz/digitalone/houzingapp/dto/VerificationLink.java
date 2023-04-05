package uz.digitalone.houzingapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@NoArgsConstructor
public class VerificationLink {
    private String token;
    private String path;
    private String text;
    private String link;


    public VerificationLink(String token, String text, String host){
        this.link = "<h1>Please, verify your account using this " +
                    "<a href=\"http://"+host+"/api/public/verification/" + token + "\", target=\"_blank\">" + text + "</a>" +
                    "</h1>";
    }
    // String link = "<a href=\"http://localhost:" +port+"/api/public/verification/" + token + "\", target=\"_blank\">Faollashtirish uchun havola</a>";
}

