import {Component, AfterViewInit, EventEmitter, Output, OnDestroy, OnInit} from '@angular/core';
import { NgbDropdownModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {AuthService} from "../../service/auth.service";
import {Subscription} from "rxjs";

declare var $: any;

@Component({
  selector: 'app-navigation-jury',
  standalone: true,
  imports:[NgbDropdownModule],
  templateUrl: './navigation-jury.html'
})
export class NavigationJuryComponent implements AfterViewInit, OnInit, OnDestroy {
  @Output() toggleSidebar = new EventEmitter<void>();
  AuthUserSub! : Subscription;

  public showSearch = false;

  constructor(private modalService: NgbModal, private authService : AuthService) {
  }

  ngOnInit(): void {
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next : user => {
      }
    })
  }
  handleLogout(event: Event) {
    event.preventDefault();
    this.authService.logout();
  }
  ngOnDestroy(): void {
    this.AuthUserSub.unsubscribe();
  }
  ngAfterViewInit() { }
}
