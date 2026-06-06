package edu.miu.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.dto.BookDto;
import edu.miu.dto.MemberDto;

import java.util.List;

public class JsonConverter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String booksToJson(List<BookDto> books) {
        return toJson(books);
    }

    public String membersToJson(List<MemberDto> members) {
        return toJson(members);
    }

    public String bookToJson(BookDto book) {
        return toJson(book);
    }

    public String memberToJson(MemberDto member) {
        return toJson(member);
    }

    private String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Failed to convert value to JSON", exception);
        }
    }
}
