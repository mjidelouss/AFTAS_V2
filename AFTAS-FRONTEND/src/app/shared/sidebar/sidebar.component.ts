import { Component, AfterViewInit, OnInit } from '@angular/core';
import { ROUTES } from './menu-items';
import { RouteInfo } from './sidebar.metadata';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule, NgIf } from '@angular/common';
import {Subscription} from "rxjs";
import {AuthService} from "../../service/auth.service";
//declare var $: any;

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports:[RouterModule, CommonModule, NgIf],
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {
  showMenu = '';
  AuthUserSub! : Subscription;
  userRole = '';
  showSubMenu = '';
  public sidebarnavItems:RouteInfo[]=[];
  // this is for the open close
  addExpandClass(element: string) {
    if (element === this.showMenu) {
      this.showMenu = '0';
    } else {
      this.showMenu = element;
    }
  }

  constructor(
    private modalService: NgbModal,
    private router: Router,
    private route: ActivatedRoute,
    private authService : AuthService,
  ) {}

  // End open close
  ngOnInit() {
    this.AuthUserSub = this.authService.AuthenticatedUser$.subscribe({
      next : user => {
        if (user) {
          this.userRole = user.role.name;
        }
      }
    })
    this.sidebarnavItems = ROUTES.filter(sidebarnavItem => sidebarnavItem);
  }

  isItemVisible(sidebarnavItem: any): boolean {
    if (sidebarnavItem.requiredRole === 'ROLE_MANAGER' && this.userRole !== 'ROLE_MANAGER') {
      return false;
    } else if (sidebarnavItem.requiredRole !== 'ROLE_MEMBER' && this.userRole == 'ROLE_MEMBER') {
      return false;
    }
    return true;
  }
}
