package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    @NotBlank
    @Pattern(regexp = "\\S+", message = "Строка не может содержать пробелов")
    private String login;
    private String name;
    @NotNull
    @PastOrPresent
    private LocalDate birthday;
    @NotBlank
    @Email
    private final String email;
}


