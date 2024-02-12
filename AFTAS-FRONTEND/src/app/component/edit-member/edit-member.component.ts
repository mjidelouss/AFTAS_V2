import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MemberService } from 'src/app/service/member.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-member',
  templateUrl: './edit-member.component.html',
  styleUrls: ['./edit-member.component.scss']
})
export class EditMemberComponent {
  member: any;

  constructor(private router: Router, private memberService: MemberService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.member = navigation?.extras.state['member'];
    }
  }

  updateMember() {
    this.memberService.updateMember(this.member).subscribe(
      (response) => {
        this.member = response.data;
        Swal.fire({
          icon: 'success',
          title: 'Member Updated Successfully',
          showConfirmButton: false,
          timer: 1500
        });
        this.router.navigate(['/member']);
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error Updating Member',
          text: 'An error occurred while updating the member. Please try again.',
          confirmButtonColor: '#d33',
          confirmButtonText: 'OK'
        });
      }
    );
  }
}
