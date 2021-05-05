package jp.ne.interspace.accesstrade.service;

import static java.util.Collections.emptyList;
import static jp.ne.interspace.accesstrade.bean.PostbackStatus.NEEDED;
import static jp.ne.interspace.accesstrade.bean.SalesLogStatusEnum.APPROVED;
import static jp.ne.interspace.accesstrade.bean.SalesLogStatusEnum.HOLD;
import static jp.ne.interspace.accesstrade.bean.SalesLogStatusEnum.NEW;
import static jp.ne.interspace.accesstrade.bean.SalesLogStatusEnum.REJECTED;
import static jp.ne.interspace.accesstrade.config.AccessTradeConstant.NULL_STRING;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.joda.time.DateTimeZone.UTC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.sqs.model.SendMessageBatchRequestEntry;
import com.google.common.collect.ImmutableMap;
import com.mycompany.tokointest.services.TokoinOrganizationService;
import com.mycompany.tokointest.services.TokoinTicketsService;
import com.mycompany.tokointest.services.TokoinUserService;

import jp.ne.interspace.accesstrade.bean.MonthlyClosingBean;
import jp.ne.interspace.accesstrade.bean.PostbackStatus;
import jp.ne.interspace.accesstrade.bean.ResultBean;
import jp.ne.interspace.accesstrade.bean.ResultCsvBean;
import jp.ne.interspace.accesstrade.bean.SalesLogBean;
import jp.ne.interspace.accesstrade.bean.SalesLogStatusEnum;
import jp.ne.interspace.accesstrade.bean.SalesLogUpdateCsvBean;
import jp.ne.interspace.accesstrade.bean.UpdateStatusConversionItem;
import jp.ne.interspace.accesstrade.bean.mongoDB.ConversionUpdateMessageBody;
import jp.ne.interspace.accesstrade.bean.mongoDB.UpdateConversionStatusMessageBody;
import jp.ne.interspace.accesstrade.dataaccess.ActionApprovalDao;
import jp.ne.interspace.accesstrade.dataaccess.SalesLogDao;
import jp.ne.interspace.accesstrade.util.AccessTradeUtil;
import jp.ne.interspace.accesstrade.util.DateUtil;
import jp.ne.interspace.accesstrade.util.IsToolsConstant.Country;
import jp.ne.interspace.accesstrade.util.SalesLogStatusUtils;

/**
 * Unit Test for {@link TokoinService}.
 *
 * @author Love
 */
@RunWith(MockitoJUnitRunner.class)
public class TokoinServiceTest {

	@InjectMocks @Spy
	public TokoinService underTest;

	@Mock
	private TokoinUserService userService;

	@Mock
	private TokoinTicketsService ticketsService;

	@Mock
	private TokoinOrganizationService organizationService;

	@Test
	public void testGetLimitedListToDisplayShouldReturnCorrectWhenSizeOfListEqualMaxNumberToDisplay() {
		//given
		int maxNumberToDisplay = 3;
		List<Integer> resultCsvs = Arrays.asList(1, 2, 3);
		List<Integer> expected = Arrays.asList(1, 2, 3);

		//when
		List<Integer> actual = underTest.getLimitedListToDisplay(resultCsvs, maxNumberToDisplay);

		//then
		assertEquals(expected, actual);
	}
}
