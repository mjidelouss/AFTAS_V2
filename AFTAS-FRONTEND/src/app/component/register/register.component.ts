import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})

export class RegisterComponent implements OnInit, OnDestroy{

  errorMessage! : string;
  AuthUserSub! : Subscription;
  identityDocumentTypes = ['IDENTITY_CARD', 'PASSPORT', 'DRIVING_LICENSE'];

  constructor(private authService : AuthService, private router : Router) {
  }

  ngOnInit() {
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next : user => {
        if(user) {
          console.log("Enterd Login Page this is the role " + user.role.name)
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

  onSubmitRegister(formRegister: NgForm) {
    if(!formRegister.valid){
      return;
    }
    const email = formRegister.value.email;
    const password = formRegister.value.password;
    const firstname = formRegister.value.firstname;
    const lastname = formRegister.value.lastname;
    const identityNumber = formRegister.value.identityNumber;
    const identityDocumentType = formRegister.value.identityDocumentType;
    const nationality = formRegister.value.nationality;

    this.authService.register(firstname, lastname, email, password, identityNumber, identityDocumentType, nationality).subscribe({
      next : userData => {
        this.router.navigate(['lock']);
      },
      error : err => {
        this.errorMessage = err;
        console.log(err);
      }

    })
  }
  ngOnDestroy() {
    this.AuthUserSub.unsubscribe();
  }

  protected readonly console = console;

}

