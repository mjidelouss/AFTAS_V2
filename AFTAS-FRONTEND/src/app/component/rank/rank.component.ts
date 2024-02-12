import { Component } from '@angular/core';
import { RankService } from 'src/app/service/rank.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rank',
  templateUrl: './rank.component.html',
  styleUrls: ['./rank.component.scss']
})
export class RankComponent {
  winners: any[] = [];
  firstPlace: any = {};
  secondPlace: any = {};
  thirdPlace: any = {};
  competition: any = {};

  constructor(private rankService: RankService, private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras.state) {
      this.competition = navigation?.extras.state['competition'];
    }
  }

  ngOnInit(): void {
    this.getWinners();
  }

  getWinners() {
    this.rankService.getWinners(this.competition).subscribe(
      (response) => {
        this.winners = response.data;
        this.firstPlace = this.winners[0];
        this.secondPlace = this.winners[1];
        this.thirdPlace = this.winners[2];
      },
      (error) => {
        console.log("Error Fetching Ranks", error);
      }
    );
  }

}
