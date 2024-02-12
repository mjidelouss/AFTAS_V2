package com.example.aftas.controller;

import com.example.aftas.Dtos.request.MemberRequest;
import com.example.aftas.entities.Member;
import com.example.aftas.mappers.MemberMapper;
import com.example.aftas.response.ResponseMessage;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/all")
    public ResponseEntity getMembers() {
        List<Member> members = memberService.getMembers();
        if (members.isEmpty()) {
            return ResponseMessage.notFound("Members Not Found");
        } else {
            return ResponseMessage.ok("Success", members);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        if (member == null) {
            return ResponseMessage.notFound("Member Not Found");
        } else {
            return ResponseMessage.ok("Success", member);
        }
    }

    @PostMapping("")
    public ResponseEntity addMember(@RequestBody @Valid MemberRequest memberRequest) {
        Member member = MemberMapper.mapMemberRequestToMember(memberRequest);
        Member member1 = memberService.addMember(member);
        if(member1 == null) {
            return ResponseMessage.badRequest("Failed To Create Member");
        } else {
            return ResponseMessage.created("Member Created Successfully", member1);
        }
    }

    @GetMapping("")
    public ResponseEntity searchMember(@RequestParam String searchTerm) {
        List<Member> members = memberService.searchMember(searchTerm);
        if (members.isEmpty()) {
            return ResponseMessage.notFound("Member Not Found");
        } else {
            return ResponseMessage.ok("Success", members);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMember(@RequestBody @Valid MemberRequest memberRequest, @PathVariable Long id) {
        Member member = MemberMapper.mapMemberRequestToMember(memberRequest);
        Member member1 = memberService.updateMember(member, id);
        if (member1 == null) {
            return ResponseMessage.badRequest("Member Not Updated");
        } else {
            return ResponseMessage.created("Member Updated Successfully", member1);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        if (member == null) {
            return ResponseMessage.notFound("Member Not Found");
        } else {
            memberService.deleteMember(id);
            return ResponseMessage.ok("Member Deleted Successfully", member);
        }
    }
}
