package pwr.edu.pl.travelly.persistence.user.entity;

import lombok.extern.java.Log;
import pwr.edu.pl.travelly.core.user.dto.LoggedUserDto;
import pwr.edu.pl.travelly.core.user.dto.UserDto;
import pwr.edu.pl.travelly.core.user.form.CreateUserForm;
import pwr.edu.pl.travelly.persistence.localisation.entity.LocalisationMapper;

import java.util.Objects;

public class UserMapper {

    public static User toEntity(final CreateUserForm registerUserForm) {
        return new User().toBuilder()
                .userName(registerUserForm.getEmail())
                .password(registerUserForm.getPassword())
                .firstName(registerUserForm.getFirstName())
                .lastName(registerUserForm.getLastName())
                .build();
    }

    public static LoggedUserDto toLoggedUserDto(final User user) {
        return LoggedUserDto.builder()
                .uuid(user.getUuid())
                .password(user.getPassword())
                .userName(user.getUserName())
                .role(user.getRole().getName())
                .build();
    }

    public static UserDto toDto(final User user) {
        if(Objects.isNull(user.getLocalisation())){
            return toNewUserDto(user);
        }
        return UserDto.builder()
                .uuid(user.getUuid())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .description(user.getDescription())
                .languages(user.getLanguages())
                .role(user.getRole().getName())
                .localisation(user.getLocalisation() != null ? LocalisationMapper.toDto(user.getLocalisation()) : null)
                .build();
    }

    private static UserDto toNewUserDto(final User user) {
        return UserDto.builder()
                .uuid(user.getUuid())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .description(user.getDescription())
                .languages(user.getLanguages())
                .role(user.getRole().getName())
                .build();
    }
}
