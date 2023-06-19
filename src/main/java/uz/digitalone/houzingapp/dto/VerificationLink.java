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

    @Value("${server.port}")
    private Integer port;

    public VerificationLink(String token, String linkText){
        this.link = "<h1>Please, verify your account using this " +
                    "<a href=\"http://houzing-app.herokuapp.com/api/public/verification/" + token + "\", target=\"_blank\">" + linkText + "</a>" +
                    "</h1>";
    }
    // String link = "<a href=\"http://localhost:" +port+"/api/public/verification/" + token + "\", target=\"_blank\">Faollashtirish uchun havola</a>";
}

