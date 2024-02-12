import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CompetitionService } from 'src/app/service/competition.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-competition',
  templateUrl: './edit-competition.component.html',
  styleUrls: ['./edit-competition.component.scss']
})
export class EditCompetitionComponent {
  competition: any;

  constructor(private router: Router, private competitionService: CompetitionService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.competition = navigation?.extras.state['competition'];
    }
  }

  updateCompetition() {
    this.competitionService.updateCompetition(this.competition).subscribe(
      (response) => {
        this.competition = response.data;
        Swal.fire({
          icon: 'success',
          title: 'Competition Updated Successfully',
          showConfirmButton: false,
          timer: 1500
        });
        this.router.navigate(['/competition']);
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error Updating Competition',
          text: 'An error occurred while updating the competition. Please try again.',
          confirmButtonColor: '#d33',
          confirmButtonText: 'OK'
        });
      }
    );
  }

}
