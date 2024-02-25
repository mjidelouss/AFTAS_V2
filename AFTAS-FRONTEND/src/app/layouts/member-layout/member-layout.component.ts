import { CommonModule } from "@angular/common";
import { Component, OnInit, HostListener } from "@angular/core";
import { Router, RouterModule } from "@angular/router";
import { NgbCollapseModule } from "@ng-bootstrap/ng-bootstrap";
import { NavigationMemberComponent } from "../../shared/header-member/navigation-member.component";
import { MemberSidebarComponent } from "../../shared/member-sidebar/member-sidebar.component";
import { DataTablesModule } from 'angular-datatables';

@Component({
  selector: 'app-member-layout',
  standalone: true,
  templateUrl: './member-layout.component.html',
  imports: [RouterModule, MemberSidebarComponent, NavigationMemberComponent, CommonModule, NgbCollapseModule, DataTablesModule],
  styleUrls: ['./member-layout.component.scss']
})
export class MemberLayoutComponent implements OnInit{

  constructor(public router: Router) {}
  public isCollapsed = false;
  public innerWidth: number = 0;
  public defaultSidebar: string = "";
  public showMobileMenu = false;
  public expandLogo = false;
  public sidebartype = "full";

  Logo() {
    this.expandLogo = !this.expandLogo;
  }

  ngOnInit() {
    if (this.router.url === "/") {
      this.router.navigate(["/member-dashboard"]);
    }
    this.defaultSidebar = this.sidebartype;
    this.handleSidebar();
  }

  @HostListener("window:resize", ["$event"])
  onResize() {
    this.handleSidebar();
  }

  handleSidebar() {
    this.innerWidth = window.innerWidth;
    if (this.innerWidth < 1170) {
      this.sidebartype = "full";
    } else {
      this.sidebartype = this.defaultSidebar;
    }
  }

  toggleSidebarType() {
    switch (this.sidebartype) {
      case "full":
        this.sidebartype = "mini-sidebar";
        break;

      case "mini-sidebar":
        this.sidebartype = "full";
        break;

      default:
    }
  }

}
