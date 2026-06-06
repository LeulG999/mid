package edu.miu.utility;

import edu.miu.dto.BookDto;
import junit.framework.TestCase;

public class JsonConverterTest extends TestCase {
    public void testBookDtoConvertsToJsonWithEscapedStringsAndNullBorrower() {
        JsonConverter jsonConverter = new JsonConverter();
        BookDto bookDto = new BookDto("B-1", "Clean \"Code\"", "Robert C. Martin", 2008, 50, "POPULAR", null);

        assertEquals(
                "{\"bookId\":\"B-1\",\"title\":\"Clean \\\"Code\\\"\",\"author\":\"Robert C. Martin\",\"publishedYear\":2008,\"timesBorrowed\":50,\"category\":\"POPULAR\",\"borrowedByMemberId\":null}",
                jsonConverter.bookToJson(bookDto));
    }
}
