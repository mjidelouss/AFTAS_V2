package com.example.aftas.mappers;

import com.example.aftas.Dtos.request.MemberRequest;
import com.example.aftas.entities.Member;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class MemberMapper {

    private static final int MAX_MEMBERSHIP_NUMBER = 9999;
    private static AtomicInteger membershipCounter = new AtomicInteger(0);
    public static Member mapMemberRequestToMember(MemberRequest memberRequest) {
        return Member.builder()
                .name(memberRequest.getName())
                .familyName(memberRequest.getFamilyName())
                .nationality(memberRequest.getNationality())
                .identityNumber(memberRequest.getIdentityNumber())
                .identityDocumentType(memberRequest.getIdentityDocumentType())
                .accessDate(LocalDate.now())
                .membershipNumber(generateUniqueMembershipNumber())
                .build();
    }

    public static Integer generateUniqueMembershipNumber() {
        int currentNumber = membershipCounter.incrementAndGet();
        if (currentNumber > MAX_MEMBERSHIP_NUMBER) {
            membershipCounter.set(1);
        }
        return currentNumber;
    }
}
