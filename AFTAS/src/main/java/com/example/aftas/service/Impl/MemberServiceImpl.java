package com.example.aftas.service.Impl;

import com.example.aftas.entities.Member;
import com.example.aftas.repository.MemberRepository;
import com.example.aftas.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }
    @Override
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> searchMember(String searchTerm) {
        return memberRepository.findByMembershipNumberOrNameOrFamilyName(searchTerm);
    }

    @Override
    public Member updateMember(Member member, Long id) {
        Member existingMember = getMemberById(id);
        existingMember.setName(member.getName());
        existingMember.setMembershipNumber(member.getMembershipNumber());
        existingMember.setFamilyName(member.getFamilyName());
        existingMember.setAccessDate(member.getAccessDate());
        existingMember.setNationality(member.getNationality());
        existingMember.setIdentityDocumentType(member.getIdentityDocumentType());
        existingMember.setIdentityNumber(member.getIdentityNumber());
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberByMembershipNumber(Integer membershipNumber) {
        return memberRepository.findByMembershipNumber(membershipNumber);
    }
}
