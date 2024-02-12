import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { RankService } from 'src/app/service/rank.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-competition',
  templateUrl: './register-competition.component.html',
  styleUrls: ['./register-competition.component.scss']
})
export class RegisterCompetitionComponent {

  constructor(private router: Router, private rankService: RankService) {
    
  }

  registerMemberToCompetition(data: any) {
    this.rankService.registerMemberToCompetition(data).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Member Registered To Competition Successfully',
          showConfirmButton: false,
          timer: 1500
        });
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error Registering Member To Competition',
          text: 'An error occurred while registering the member to the competition. Please try again.',
          confirmButtonColor: '#d33',
          confirmButtonText: 'OK'
        });
      }
    );
  }

}
