package com.example.aftas.service;
import com.example.aftas.entities.Member;

import java.util.List;

public interface MemberService {
    Member getMemberById(Long id);
    Member addMember(Member member);
    List<Member> searchMember(String name);
    Member updateMember(Member member, Long id);
    void deleteMember(Long id);

    List<Member> getMembers();

    Member getMemberByMembershipNumber(Integer membershipNumber);
}
