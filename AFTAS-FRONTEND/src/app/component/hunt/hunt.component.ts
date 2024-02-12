import { Component } from '@angular/core';
import { HuntService } from 'src/app/service/hunt.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hunt',
  templateUrl: './hunt.component.html',
  styleUrls: ['./hunt.component.scss']
})
export class HuntComponent {

  constructor(private huntService: HuntService, private router:Router) {}

  addHunt(hunt: any) {
    this.huntService.addHunt(hunt).subscribe(
      (response) => {
        Swal.fire({
          icon: 'success',
          title: 'Hunt Added Successfully',
          showConfirmButton: false,
          timer: 1500
        });
      },
      (error) => {
        Swal.fire({
          icon: 'error',
          title: 'Error Adding Hunt',
          text: 'An error occurred while adding the hunt. Please try again.',
          confirmButtonColor: '#d33',
          confirmButtonText: 'OK'
        });
      }
    );
  }
}
