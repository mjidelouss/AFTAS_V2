import { Component } from '@angular/core';
import { LevelService } from 'src/app/service/level.service';
import { NgFor } from '@angular/common';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-level',
  templateUrl: './level.component.html',
  styleUrls: ['./level.component.scss']
})
export class LevelComponent {
    levels!:any[]
    constructor(private levelService:LevelService) {}

    ngOnInit() {
      this.getLevels();
    }

    getLevels() {
      this.levelService.getLevels().subscribe(
        (response) => {
          this.levels = response.data;
        },
        (error) => {
          console.error('error fetching levels', error)
        }
      )
    }

    deleteLevel(id: number) {
      Swal.fire({
        title: 'Are you sure?',
        text: 'You will not be able to recover this level!',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
      }).then((result) => {
        if (result.isConfirmed) {
          this.levelService.deleteLevel(id).subscribe(
            (response) => {
              Swal.fire({
                icon: 'success',
                title: 'Level Deleted Successfully',
                showConfirmButton: false,
                timer: 1500
              });
            },
            (error) => {
              Swal.fire({
                icon: 'error',
                title: 'Error Deleting Level',
                text: 'An error occurred while deleting the level. Please try again.',
                confirmButtonColor: '#d33',
                confirmButtonText: 'OK'
              });
            }
          );
        }
      });
    }
}
