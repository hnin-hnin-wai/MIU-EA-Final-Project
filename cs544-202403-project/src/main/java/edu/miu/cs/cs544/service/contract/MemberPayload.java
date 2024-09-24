package edu.miu.cs.cs544.service.contract;

import java.io.Serial;
import java.io.Serializable;

import com.azure.core.annotation.Get;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MemberPayload implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Integer memberId;
	private String firstName;
	private String lastName;
	private String email;
	private String barCode;

}
