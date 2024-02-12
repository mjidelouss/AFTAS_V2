import { Component } from '@angular/core';
import { FishService } from 'src/app/service/fish.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-fish',
  templateUrl: './add-fish.component.html',
  styleUrls: ['./add-fish.component.scss']
})
export class AddFishComponent {
  constructor(private fishService: FishService, private router:Router) {}

  addFish(fish: any) {
    this.fishService.addFish(fish).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Fish Added',
          text: 'Fish added successfully!',
        });
        this.router.navigate(['/fish']);
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Failed to add Fish. Please try again.',
        });
      }
    );
  }
}
