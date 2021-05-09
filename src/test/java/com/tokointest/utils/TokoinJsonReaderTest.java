package com.tokointest.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Unit test for #{TokoinJsonReader}.
 *
 * @author Love
 */
@RunWith(MockitoJUnitRunner.class)
public class TokoinJsonReaderTest {

	@InjectMocks @Spy
	private TokoinJsonReader underTest;

	@Test
	public void testFindJsonArrayShouldReturnCorrectDataWhenCalled() throws IOException, ParseException {
		// given
		String fileName = "jsondata/users.json";
		File file = mock(File.class);
		Reader reader = mock(Reader.class);
		JSONArray expected = mock(JSONArray.class);
        doReturn(file).when(underTest).findFileBy(fileName);
        doReturn(reader).when(underTest).initFileReader(file);
        ArgumentCaptor<JSONParser> captor = ArgumentCaptor.forClass(JSONParser.class);
        doReturn(expected).when(underTest).parseToJsonArray(captor.capture(), eq(reader));

		// when
		JSONArray actual = underTest.findJsonArray(fileName);

		// then
		assertNotNull(actual);
		assertSame(expected, actual);
	}

	@SuppressWarnings("static-access")
	@Test
	public void testFindJsonArrayShouldReturnNullWhenFileNotFound() throws IOException, ParseException {
		// given
		String fileName = "jsondata/users.json";
		File file = mock(File.class);
        doReturn(file).when(underTest).findFileBy(fileName);
        doThrow(IOException.class).when(underTest).initFileReader(file);

		// when
		JSONArray actual = underTest.findJsonArray(fileName);

		// then
		assertNull(actual);
        verify(underTest, never()).parseToJsonArray(any(JSONParser.class), any(Reader.class));
	}
}
