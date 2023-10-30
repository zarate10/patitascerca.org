import { Component, Input } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { IPost } from 'src/app/models/IPost';
import { IUser } from 'src/app/models/IUser';

@Component({
    selector: 'home-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.css']
})
export class PostComponent {
    @Input() post!: IPost;
    @Input() user!: IUser; 

    constructor(private toastr: ToastrService) {}

    deletePost(id: number) {
        this.toastr.success("Publicación eliminada correctamente.")
        alert("Crear endpoint para borrar post " + id);
    }
}
