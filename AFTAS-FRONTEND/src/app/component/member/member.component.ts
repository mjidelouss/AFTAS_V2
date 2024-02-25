import { Component } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import { FormsModule } from '@angular/forms';
import {NgFor, NgIf} from '@angular/common';
import { Router } from '@angular/router';
import { MemberService } from 'src/app/service/member.service';
import Swal from 'sweetalert2';
import {Subscription} from "rxjs";


@Component({
  selector: 'app-member',
  standalone: true,
  imports: [NgFor, FormsModule, NgIf],
  templateUrl: 'member.component.html'
})
export class MemberComponent {
  members!:any[];
  searchTerm: string = '';
  constructor(private memberService: MemberService, private authService : AuthService, private router:Router) {

  }

  goToAddMember() {
    this.router.navigate(['/add-member'])
  }

  viewMember(member: any) {
    this.router.navigate(['/member-detail'], { state: { member } });
  }

  editMember(member: any) {
    this.router.navigate(['/edit-member'], { state: { member } });
  }

  searchMembers() {
    this.memberService.searchMembers(this.searchTerm).subscribe((response) => {
      this.members = response.data;
    },
    (error) => {
      console.error('Error Searching for Member', error);
    });
  }

  activateAccount(id: number) {
    this.memberService.activateAccount(id).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Account Activated Successfully',
          showConfirmButton: false,
          timer: 1500
        });
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error Activating Account',
          text: 'An error occurred while activating the account. Please try again.',
          confirmButtonColor: '#d33',
          confirmButtonText: 'OK'
        });
      }
    );
  }

  deleteMember(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will not be able to recover this member!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.memberService.deleteMember(id).subscribe(
          (response) => {
            Swal.fire({
              icon: 'success',
              title: 'Member Deleted Successfully',
              showConfirmButton: false,
              timer: 1500
            });
            this.getMembers();
          },
          (error) => {
            Swal.fire({
              icon: 'error',
              title: 'Error Deleting Member',
              text: 'An error occurred while deleting the member. Please try again.',
              confirmButtonColor: '#d33',
              confirmButtonText: 'OK'
            });
          }
        );
      }
    });
  }

  ngOnInit() {
    setTimeout(()=>{
      $('#membertables').DataTable({
       pagingType: 'full_numbers',
       pageLength: 5,
       processing: true,
       lengthMenu : [5, 10, 25],
      });
   }, 1);
   this.getMembers()
  }

  getMembers() {
      this.memberService.getMembers().subscribe(
        (response) => {
          this.members = response.data;
        },
        (error) => {
          console.error('Error fetching members:', error);
        }
      );
    }
}
