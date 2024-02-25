import { RouteInfo } from '../sidebar/sidebar.metadata';

export const JURY_ROUTES: RouteInfo[] = [

  {
    path: '/jury-dashboard',
    title: 'Competitions',
    icon: '🎯',
    class: '',
    requiredRole: 'ROLE_JURY',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/hunt',
    title: 'Hunts',
    icon: '🧜🏻‍♂️',
    requiredRole: 'ROLE_JURY',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/fish',
    title: 'Fishes',
    icon: '🐟',
    requiredRole: 'ROLE_JURY',
    class: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/level',
    title: 'Levels',
    icon: '⭐',
    class: '',
    requiredRole: 'ROLE_JURY',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/register-competition',
    title: 'Register',
    icon: '➕',
    class: '',
    requiredRole: 'ROLE_JURY',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/podium',
    title: 'Podium',
    icon: '🏆',
    class: '',
    extralink: false,
    requiredRole: 'ROLE_JURY',
    submenu: []
  },
];
