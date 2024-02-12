import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MemberService } from 'src/app/service/member.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-member',
  templateUrl: './add-member.component.html',
  styleUrls: ['./add-member.component.scss']
})
export class AddMemberComponent {
  member: any;
  identityDocumentTypes = ['IDENTITY_CARD', 'PASSPORT', 'DRIVING_LICENSE'];

  constructor(private router: Router, private memberService: MemberService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.member = navigation?.extras.state['member'];
    }
  }

  goBack() {
    this.router.navigate(['/member'])
  }

  addMember(memberData: any) {
    this.memberService.addMember(memberData).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Member Added',
          text: 'Member added successfully!',
        });
        this.router.navigate(['/member']);
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Failed to add Member. Please try again.',
        });
      }
    );
  }

}
