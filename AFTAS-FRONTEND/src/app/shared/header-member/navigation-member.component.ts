import {Component, AfterViewInit, EventEmitter, Output, OnInit, OnDestroy} from '@angular/core';
import { NgbDropdownModule, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {Subscription} from "rxjs";
import {AuthService} from "../../service/auth.service";

declare var $: any;

@Component({
  selector: 'app-navigation-member',
  standalone: true,
  imports:[NgbDropdownModule],
  templateUrl: './navigation-member.html'
})
export class NavigationMemberComponent implements AfterViewInit, OnInit, OnDestroy {
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
