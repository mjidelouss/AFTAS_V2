import { Routes } from '@angular/router';
import { FishComponent } from './fish/fish.component';
import { MemberComponent } from './member/member.component';
import { HuntComponent } from './hunt/hunt.component';
import { PodiumComponent } from './podium/podium.component';
import { LevelComponent } from './level/level.component';
import { RegisterCompetitionComponent } from './register-competition/register-competition.component';
import { CompetitionDetailComponent } from './competition-detail/competition-detail.component';
import { AddCompetitionComponent } from './add-competition/add-competition.component';
import { EditCompetitionComponent } from './edit-competition/edit-competition.component';
import { AddMemberComponent } from './add-member/add-member.component';
import { EditMemberComponent } from './edit-member/edit-member.component';
import { MemberDetailsComponent } from './member-details/member-details.component';
import { AddLevelComponent } from './add-level/add-level.component';
import { AddFishComponent } from './add-fish/add-fish.component';
import { RankComponent } from './rank/rank.component';
import {authGuard} from "../helpers/auth.guard";

export const ComponentsRoutes: Routes = [
	{
		path: '',
		children: [
			{
				path: 'podium',
				component: PodiumComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_USER', 'ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'member',
				component: MemberComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER']}
			},
			{
				path: 'hunt',
				component: HuntComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'fish',
				component: FishComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'level',
				component: LevelComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'register-competition',
				component: RegisterCompetitionComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'competition-detail',
				component: CompetitionDetailComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'add-competition',
				component: AddCompetitionComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'edit-competition',
				component: EditCompetitionComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'add-member',
				component: AddMemberComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER']}
			},
			{
				path: 'edit-member',
				component: EditMemberComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER']}
			},
			{
				path: 'member-detail',
				component: MemberDetailsComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER']}
			},
			{
				path: 'add-level',
				component: AddLevelComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'add-fish',
				component: AddFishComponent,
        canActivate: [authGuard],
        data: {roles: ['ROLE_MANAGER', 'ROLE_JURY']}
			},
			{
				path: 'ranking',
				component: RankComponent
			}
		]
	}
];
