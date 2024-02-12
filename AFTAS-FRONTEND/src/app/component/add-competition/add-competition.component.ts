import { Component } from '@angular/core';
import { CompetitionService } from 'src/app/service/competition.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-add-competition',
  templateUrl: './add-competition.component.html',
  styleUrls: ['./add-competition.component.scss']
})
export class AddCompetitionComponent {

  constructor(private competitionService: CompetitionService, private router: Router) {}

  addCompetition(competition: any) {
    this.competitionService.addCompetition(competition).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Competition Added',
          text: 'Competition added successfully!',
        });
        this.router.navigate(['/competition']);
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Failed to add competition. Please try again.',
        });
      }
    );
  }
  
}
