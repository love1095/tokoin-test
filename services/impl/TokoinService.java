package com.mycompany.tokointest.services.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private TokoinUserService userService;

	@Autowired
	private TokoinTicketsService ticketsService;

	@Autowired
	private TokoinOrganizationService organizationService;

	public void search() throws Exception {
		System.out.println("BAT DAU TIEN TRINH");
		Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap type");
        String type = scanner.nextLine();
        System.out.println("Nhap key");
        String key = scanner.nextLine();
        System.out.println("Nhap value");
        String value = scanner.nextLine();
        TokoinType tokoinType = TokoinType.findBy(Integer.parseInt(type));
        List<String> response = processTokoinSearch(key, value, tokoinType);
		printData(response);
		System.out.println("KET THUC TIEN TRINH");
	}

	private List<String> processTokoinSearch(String key, String value, TokoinType tokoinType) throws Exception {
		List<String> response = new LinkedList<>();
		if (tokoinType.name().equals(TokoinType.USERS.name())) {
			UserField field = UserField.findBy(Integer.parseInt(key));
			response = userService.userSearchProcess(tokoinType, field, value);
		}
		if (tokoinType.name().equals(TokoinType.TICKETS.name())) {
			TicketField field = TicketField.findBy(Integer.parseInt(key));
			response = ticketsService.userSearchProcess(tokoinType, field, value);
		}
		if (tokoinType.name().equals(TokoinType.ORGANIZATION.name())) {
			OrganizationField field = OrganizationField.findBy(Integer.parseInt(key));
			response = organizationService.userSearchProcess(tokoinType, field, value);
		}
		return response;
	}

	void printData(List<String> response) {
		if (response.isEmpty()) {
			System.out.println("No data found");
			return;
		}
		for (int i = 0; i < response.size(); i++) {
			System.out.println("Data found" + i + " :" + response.get(i));
		}
	}

}
