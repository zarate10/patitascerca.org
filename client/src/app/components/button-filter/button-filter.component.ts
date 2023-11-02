import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-button-filter',
  templateUrl: './button-filter.component.html',
  styleUrls: ['./button-filter.component.css']
})
export class ButtonFilterComponent {
  @Input() textBtn!: string; 
  @Input() color: string = 'default';

}
