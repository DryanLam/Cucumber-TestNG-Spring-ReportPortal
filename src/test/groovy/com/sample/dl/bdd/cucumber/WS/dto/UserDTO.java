package com.sample.dl.bdd.cucumber.WS.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

/**
 * @Accessors(fluent = true, chain = true)
 * @NoArgsConstructor
 * @JsonIgnoreProperties
 * @Data
 * @ToString
 * @EqualsAndHashCode
 * @EqualsAndHashCode(of = {"title", "body","userId","id"})
 */

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UserDTO {
    private String title;
    private String body;
    private int userId;
    private int id;

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) ob;
        return Objects.equals(userDTO.title, title)
                && Objects.equals(userDTO.body, body)
                && Objects.equals(userDTO.userId, userId)
                && Objects.equals(userDTO.id, id);
    }

    @Override
    public int hashCode() {
        return (title == null ? 0 : title.hashCode())
                + (body == null ? 0 : body.hashCode());
    }
}
