import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  tittle: string = 'Clientes';

  modules: [
    { name: 'Dashboard'; route: '#'; icon: 'space_dashboard' },
    { name: 'Listado'; route: '#'; icon: 'contact_page' },
    { name: 'Registro'; route: '#'; icon: 'group_add' }
  ];
}
