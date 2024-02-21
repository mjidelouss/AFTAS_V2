import { Component, AfterViewInit, OnInit } from '@angular/core';
import { JURY_ROUTES } from "./jury-menu";
import { RouteInfo } from '../sidebar/sidebar.metadata';
import { Router, ActivatedRoute, RouterModule } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule, NgIf } from '@angular/common';
//declare var $: any;

@Component({
  selector: 'app-jury-sidebar',
  standalone: true,
  imports:[RouterModule, CommonModule, NgIf],
  templateUrl: './jury-sidebar.component.html'
})
export class JurySidebarComponent implements OnInit {
  showMenu = '';
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
    private route: ActivatedRoute
  ) {}

  // End open close
  ngOnInit() {
    this.sidebarnavItems = JURY_ROUTES.filter(sidebarnavItem => sidebarnavItem);
  }
}
