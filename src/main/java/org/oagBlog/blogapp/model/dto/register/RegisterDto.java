package org.oagBlog.blogapp.model.dto.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.PackagePrivate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@PackagePrivate
public class RegisterDto {

    @Size(min = 3, max = 50)
    @NotNull(message = "Sizning ismingiz bosh bo'lishi mumkun emas!!!")
    @JsonProperty("first_name")
    String firstName;
    @Size(min = 3, max = 50)
    @NotNull(message = "Sizning famiyangiz bosh bo'lishi mumkun emas!!!")
    @JsonProperty("last_name")
    String lastName;

    @NotNull(message = "Sizning emailnigiz bosh bo'lishi mumkun emas!!!")
    @Email
    String email;
    @Size(min = 6, max = 100)
    @NotNull(message = "Sizning parolingiz bosh bo'lishi mumkun emas!!!")
    String password;
}
