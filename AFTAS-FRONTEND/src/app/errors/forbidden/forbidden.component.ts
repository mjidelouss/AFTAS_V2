import { Component } from '@angular/core';
import {Subscription} from "rxjs";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forbidden',
  templateUrl: './forbidden.component.html',
  styleUrls: ['./forbidden.component.scss']
})
export class ForbiddenComponent {

  AuthUserSub! : Subscription;

  constructor(private authService : AuthService, private router : Router) {
  }
  goHome() {
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
}
