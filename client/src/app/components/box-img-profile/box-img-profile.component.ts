import { Component, Input } from '@angular/core';

@Component({
    selector: 'boximg',
    templateUrl: './box-img-profile.component.html',
    styleUrls: ['./box-img-profile.component.css']
})
export class BoxImgProfileComponent {
    @Input() userImg!: string;
    @Input() alt!: string;
}
