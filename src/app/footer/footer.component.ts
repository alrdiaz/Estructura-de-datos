import {
  Component,
  ɵclearResolutionOfComponentResourcesQueue,
} from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
})
export class FooterComponent {
  autor: any = {
    name: 'Alejandro Romero',
    email: 'mailto:aromero4@eafit.edu.co',
    github: 'https://github.com/alrdiaz',
  };
}
