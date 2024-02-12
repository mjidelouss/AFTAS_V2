import { Component } from '@angular/core';
import { LevelService } from 'src/app/service/level.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-level',
  templateUrl: './add-level.component.html',
  styleUrls: ['./add-level.component.scss']
})
export class AddLevelComponent {

  constructor(private levelService: LevelService, private router:Router) {}

  addLevel(level: any) {
    console.log(level)
    this.levelService.addLevel(level).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Level Added',
          text: 'Level added successfully!',
        });
        this.router.navigate(['/level']);
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Failed to add Level. Please try again.',
        });
      }
    );
  }

}
