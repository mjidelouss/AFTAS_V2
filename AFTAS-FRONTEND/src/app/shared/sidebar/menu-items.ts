import { RouteInfo } from './sidebar.metadata';

export const ROUTES: RouteInfo[] = [

  {
    path: '/competition',
    title: 'Competitions',
    icon: '🎯',
    class: '',
    requiredRole: 'ROLE_MEMBER',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/hunt',
    title: 'Hunts',
    icon: '🧜🏻‍♂️',
    class: '',
    extralink: false,
    requiredRole: '',
    submenu: []
  },
  {
    path: '/component/fish',
    title: 'Fishes',
    icon: '🐟',
    class: '',
    requiredRole: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/level',
    title: 'Levels',
    icon: '⭐',
    class: '',
    requiredRole: '',
    extralink: false,
    submenu: []
  },
  {
    path: '/component/member',
    title: 'Members',
    icon: '👥',
    class: '',
    extralink: false,
    requiredRole: 'ROLE_MANAGER',
    submenu: []
  },
  {
    path: '/component/register-competition',
    title: 'Register',
    icon: '➕',
    class: '',
    extralink: false,
    requiredRole: '',
    submenu: []
  },
  {
    path: '/component/podium',
    title: 'Podium',
    icon: '🏆',
    class: '',
    extralink: false,
    requiredRole: 'ROLE_MEMBER',
    submenu: []
  },
];
