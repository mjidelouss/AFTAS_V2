import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FullComponent } from './layouts/full/full.component';
import { CompetitionDetailComponent } from './component/competition-detail/competition-detail.component';
import { AddCompetitionComponent } from './component/add-competition/add-competition.component';
import { EditCompetitionComponent } from './component/edit-competition/edit-competition.component';
import { AddMemberComponent } from './component/add-member/add-member.component';
import { EditMemberComponent } from './component/edit-member/edit-member.component';
import { MemberDetailsComponent } from './component/member-details/member-details.component';
import { AddLevelComponent } from './component/add-level/add-level.component';
import { LevelComponent } from './component/level/level.component';
import { FishComponent } from './component/fish/fish.component';
import { MemberComponent } from './component/member/member.component';
import { AddFishComponent } from './component/add-fish/add-fish.component';
import { RankComponent } from './component/rank/rank.component';
import {LoginComponent} from "./component/login/login.component";
import {RegisterComponent} from "./component/register/register.component";
import {AccessDeniedComponent} from "./component/access-denied/access-denied.component"
import {LockComponent} from "./component/lock/lock.component";
import {ForbiddenComponent} from "./errors/forbidden/forbidden.component";
import {authGuard} from "./helpers/auth.guard";
import {MemberDashboardComponent} from "./member-dashboard/member-dashboard.component";
import {MemberLayoutComponent} from "./layouts/member-layout/member-layout.component";
import {JuryLayoutComponent} from "./layouts/jury-layout/jury-layout.component";
import {JuryDashboardComponent} from "./jury-dashboard/jury-dashboard.component";

export const Approutes: Routes = [
  {
    path: '',
    component: FullComponent,
    children: [
      { path: '', redirectTo: '/competition', pathMatch: 'full' },
      {
        path: 'competition',
        loadChildren: () => import('./competition/competition.module').then(m => m.CompetitionModule),
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
      },
      {
        path: 'component',
        loadChildren: () => import('./component/component.module').then(m => m.ComponentsModule)
      },
      {
				path: 'level',
				component: LevelComponent
			},
      {
        path: 'member',
        component: MemberComponent
      },
      {
				path: 'fish',
				component: FishComponent
			},
      {
        path: 'competition-detail',
        component: CompetitionDetailComponent,
      },
      {
        path: 'add-competition',
        component: AddCompetitionComponent
      },
      {
				path: 'edit-competition',
				component: EditCompetitionComponent
			},
      {
        path: 'add-member',
        component: AddMemberComponent
      },
      {
        path: 'edit-member',
        component: EditMemberComponent
      },
      {
        path: 'member-detail',
        component: MemberDetailsComponent
      },
      {
				path: 'add-level',
				component: AddLevelComponent
			},
      {
        path: 'add-fish',
        component: AddFishComponent
      },
      {
				path: 'ranking',
				component: RankComponent
			},
    ]
  },
  {
    path: '',
    component: MemberLayoutComponent,
    children: [
      { path: '', redirectTo: '/member-dashboard', pathMatch: 'full' },
      {
        path: 'component',
        loadChildren: () => import('./component/component.module').then(m => m.ComponentsModule)
      },
      {
        path: 'member-dashboard',
        component: MemberDashboardComponent
      },
      {
        path: 'ranking',
        component: RankComponent
      },
    ]
  },
  {
    path: '',
    component: JuryLayoutComponent,
    children: [
      { path: '', redirectTo: '/jury-dashboard', pathMatch: 'full' },
      {
        path: 'component',
        loadChildren: () => import('./component/component.module').then(m => m.ComponentsModule)
      },
      {
        path: 'jury-dashboard',
        component: JuryDashboardComponent
      },
      {
        path: 'level',
        component: LevelComponent
      },
      {
        path: 'fish',
        component: FishComponent
      },
      {
        path: 'competition-detail',
        component: CompetitionDetailComponent,
      },
      {
        path: 'add-competition',
        component: AddCompetitionComponent
      },
      {
        path: 'edit-competition',
        component: EditCompetitionComponent
      },
      {
        path: 'add-level',
        component: AddLevelComponent
      },
      {
        path: 'add-fish',
        component: AddFishComponent
      },
      {
        path: 'ranking',
        component: RankComponent
      },
    ]
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'lock',
    component: LockComponent
  },
  {
    path: 'not-found',
    component: AccessDeniedComponent
  },
  {
    path: 'forbidden',
    component: ForbiddenComponent
  },
  {
    path: '**',
    redirectTo: '/starter'
  }
];
