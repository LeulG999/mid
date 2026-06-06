package edu.miu.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import edu.miu.model.Member;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({
        "memberId",
        "fullName",
        "email",
        "phoneNumber",
        "borrowedBookIds"
})
public class MemberDto {
    private final String memberId;
    private final String fullName;
    private final String email;
    private final String phoneNumber;
    private final List<String> borrowedBookIds;

    public MemberDto(
            String memberId,
            String fullName,
            String email,
            String phoneNumber,
            List<String> borrowedBookIds) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.borrowedBookIds = borrowedBookIds;
    }

    public static MemberDto from(Member member) {
        return new MemberDto(
                member.getMemberId(),
                member.getFullName(),
                member.getEmail(),
                member.getPhoneNumber(),
                new ArrayList<String>(member.getBorrowedBookIds()));
    }

    public String getMemberId() {
        return memberId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }
}
