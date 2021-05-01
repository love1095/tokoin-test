package com.mycompany.tokointest.services;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.mycompany.tokointest.models.OrganizationField;
import com.mycompany.tokointest.models.TicketField;
import com.mycompany.tokointest.models.TokoinType;
import com.mycompany.tokointest.models.UserField;

/**
 * TokionService.
 *
 * @author Love
 *
 */
@Service
public class TokoinService {

//	@Autowired
//	private TokoinUserService userService;
//
//	@Autowired
//	private TokoinTicketsService ticketsService;
//
//	@Autowired
//	private TokoinOrganizationService organizationService;

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws Exception {
		System.out.println("BAT DAU TIEN TRINH");
		Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap type");
        String type = scanner.nextLine();
        System.out.println("Nhap key");
        String key = scanner.nextLine();
        System.out.println("Nhap value");
        String value = scanner.nextLine();
        TokoinType tokoinType = TokoinType.findBy(Integer.parseInt(type));
		if (tokoinType.name().equals(TokoinType.USERS.name())) {
			UserField field = UserField.findBy(Integer.parseInt(key));
	        TokoinUserService.userSearchProcess(tokoinType, field, value);
		}
		if (tokoinType.name().equals(TokoinType.TICKETS.name())) {
			TicketField field = TicketField.findBy(Integer.parseInt(key));
	        TokoinTicketsService.userSearchProcess(tokoinType, field, value);
		}
		if (tokoinType.name().equals(TokoinType.ORGANIZATION.name())) {
			OrganizationField field = OrganizationField.findBy(Integer.parseInt(key));
	        TokoinOrganizationService.userSearchProcess(tokoinType, field, value);
		}
	}
}
