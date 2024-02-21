import { Component } from '@angular/core';
import {CompetitionService} from "../service/competition.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-member-dashboard',
  templateUrl: './member-dashboard.component.html',
  styleUrls: ['./member-dashboard.component.scss']
})
export class MemberDashboardComponent {
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
