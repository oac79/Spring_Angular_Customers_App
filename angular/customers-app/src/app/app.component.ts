import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Welcome to Angular';

  course: string = 'Course Spring5 with Angular';
  teacher: string = 'Andrés Guzmán';
}
