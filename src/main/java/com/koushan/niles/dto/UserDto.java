package com.koushan.niles.dto;


import com.koushan.niles.model.Preference;
import com.koushan.niles.model.Sex;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
public class UserDto implements Serializable {
    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 1L;
	private String email;
    private String firstName;
    private String lastName;
    private Sex sex;
    private String contactNo;
    private String password;
    private Preference preference;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.email, entity.email) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.sex, entity.sex) &&
                Objects.equals(this.contactNo, entity.contactNo) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.preference, entity.preference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, sex, contactNo, password, preference);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "email = " + email + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "sex = " + sex + ", " +
                "contactNo = " + contactNo + ", " +
                "password = " + password + ", " +
                "preference = " + preference + ")";
    }
}
