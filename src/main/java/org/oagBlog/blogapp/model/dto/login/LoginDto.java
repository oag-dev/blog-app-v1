package org.oagBlog.blogapp.model.dto.login;

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
public class LoginDto {

    @NotNull(message = "Sizning emailnigiz bosh bo'lishi mumkun emas!!!")
    @Email
    String email;
    @Size(min = 6, max = 100)
    @NotNull(message = "Sizning parolingiz bosh bo'lishi mumkun emas!!!")
    String password;
}
