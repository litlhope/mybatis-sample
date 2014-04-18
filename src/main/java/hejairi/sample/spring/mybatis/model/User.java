package hejairi.sample.spring.mybatis.model;

import lombok.Data;

/**
 * Created by litlhope on 2014. 4. 15..
 */
@Data
public class User {
	private Long id;
	private String email;
	private String userName;
	private String tel;
	private String sex;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (email != null ? !email.equals(user.email) : user.email != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return email != null ? email.hashCode() : 0;
	}
}
