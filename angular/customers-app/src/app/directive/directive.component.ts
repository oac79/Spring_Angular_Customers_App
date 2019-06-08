import { Component } from '@angular/core';

@Component({
  selector: 'app-directive',
  templateUrl: './directive.component.html'
})
export class DirectiveComponent {

  coursesList: string[] = ['TypeScript', 'JavaScript', 'Java SE', 'C#', 'PHP'];
  enable: boolean = true;
  constructor() { }

  setEnable(): void{
    this.enable = (this.enable==true)? false: true;
  }

}
