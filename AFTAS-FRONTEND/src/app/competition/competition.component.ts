import { Component, AfterViewInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompetitionService } from '../service/competition.service';
import { PageEvent } from '@angular/material/paginator';
import Swal from 'sweetalert2';
import {Subscription} from "rxjs";
import {AuthService} from "../service/auth.service";

@Component({
  templateUrl: './competition.component.html'
})
export class CompetitionComponent {

  competitions: any[] = [];
  selectedStatus: string = '';
  pageSizeOptions: number[] = [5, 10, 20];
  pageSize: number = 5;
  pageIndex: number = 0;
  totalCompetitions: number = 0;
  AuthUserSub! : Subscription;

  constructor(private competitionService: CompetitionService, private router: Router, private authService : AuthService,) {}

  ngOnInit() {
    this.getCompetitions();
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next : user => {
        if(user) {
          if (user.role.name == "ROLE_MANAGER") {
            this.router.navigate(['competition']);
          } else if (user.role.name == "ROLE_JURY") {
            this.router.navigate(['jury-dashboard']);
          } else {
            this.router.navigate(['member-dashboard']);
          }
        } else {
          console.log("user is null")
        }
      }
    })
  }

  getCompetitions() {
    this.competitionService
      .getCompetitions(this.pageIndex, this.pageSize)
      .subscribe(
        (response) => {
          this.competitions = response.data;
          this.totalCompetitions = response.data.length;
        },
        (error) => {
          console.error('Error fetching Competitions:', error);
        }
      );
  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getCompetitions();
  }

  getCompetitionsByStatus() {
    this.competitionService.getCompetitionsByStatus(this.selectedStatus).subscribe(
      (response) => {
        this.competitions = response.data;
      },
      (error) => {
        console.error('Error fetching competitions:', error);
      }
    );
  }

  viewCompetition(competition: any) {
    this.router.navigate(['/competition-detail'], { state: { competition } });
  }

  editCompetition(competition: any) {
    this.router.navigate(['/edit-competition'], { state: { competition } });
  }

  deleteCompetition(id: number) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'You will not be able to recover this competition!',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.competitionService.deleteCompetition(id).subscribe(
          (response) => {
            Swal.fire({
              icon: 'success',
              title: 'Competition Deleted Successfully',
              showConfirmButton: false,
              timer: 1500
            });
            this.getCompetitions();
          },
          (error) => {
            Swal.fire({
              icon: 'error',
              title: 'Error Deleting Competition',
              text: 'An error occurred while deleting the competition. Please try again.',
              confirmButtonColor: '#d33',
              confirmButtonText: 'OK'
            });
          }
        );
      }
    });
  }

}
