package comp_user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "comp_user")
@Entity
public class CompUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long compUserId;

	@Column(unique = true, nullable = false)
	private String compUserLoginId; // 로그인 아이디

	@Column(nullable = false)
	private String compUserPassword; // 로그인 비밀번호

	@Column(nullable = false)
	private String compUserName;

	@Column(nullable = false)
	private String compUserPhone;

	@Column(unique = true, nullable = false)
	private String compUserEmail;

	@Column(nullable = false)
	private String compUserNickname;

	@Column(nullable = false)
	private String compRegNumber; // 사업자등록번호


	@Builder
	public CompUser(Long compUserId, String compUserLoginId, String compUserPassword, String compUserName, String compUserPhone, String compUserEmail, String compUserNickname, String compRegNumber) {
		this.compUserId = compUserId;
		this.compUserLoginId = compUserLoginId;
		this.compUserPassword = compUserPassword;
		this.compUserName = compUserName;
		this.compUserPhone = compUserPhone;
		this.compUserEmail = compUserEmail;
		this.compUserNickname = compUserNickname;
		this.compRegNumber = compRegNumber;
	}

	public void update(CompUserRequest.UpdateDTO updateDTO) {
		this.compUserPassword = updateDTO.getCompUserPassword();
		this.compUserName = updateDTO.getCompUserName();
		this.compUserPhone = updateDTO.getCompUserPhone();
		this.compUserEmail = updateDTO.getCompUserEmail();
		this.compUserNickname = updateDTO.getCompUserNickname();
		this.compRegNumber = updateDTO.getCompRegNumber();
	}

}