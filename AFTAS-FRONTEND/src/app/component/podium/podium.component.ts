import { Component } from '@angular/core';
import { CompetitionService } from 'src/app/service/competition.service';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-podium',
  standalone: true,
  templateUrl: './podium.component.html',
  imports: [NgFor],
  styleUrls: ['./podium.component.scss']
})
export class PodiumComponent {
  competitions!:any[];

  constructor(private competitionService:CompetitionService, private router: Router) {}

  ngOnInit() {
    this.getCompetitions();
  }

  getCompetitions() {
    this.competitionService.getSimpleCompetitions().subscribe(
      (response) => {
        this.competitions = response.data.map((competition: any, index: number) => ({
          ...competition,
          imageUrl: 'assets/images/competition.PNG',
        }));
      },
      (error) => {
        console.error('error fetching Competitions', error);
      }
    )
  }

  getPodium(competition: any) {
    this.router.navigate(['/ranking'], { state: { competition } });
  }

}
