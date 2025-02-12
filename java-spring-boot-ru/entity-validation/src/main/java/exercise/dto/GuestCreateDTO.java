package exercise.dto;

// BEGIN

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GuestCreateDTO {
    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "\\+[0-9]{11,13}")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}")
    private String clubCard;
    @Future
    private LocalDate cardValidUntil;
} /*
   Имя пользователя name должно быть не пустым
Электронная почта email должна быть валидной
Номер телефона phoneNumber должен начинаться с символа + и содержать от 11 до 13 цифр
Номер клубной карты clubCard должен состоять ровно из четырех цифр
Срок действия клубной карты cardValidUntil должен быть еще не истекшим
    */
// END
