import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { IPost } from 'src/app/models/IPost';
import { IUser } from 'src/app/models/IUser';
import { CommentsService } from 'src/app/services/comments.service';

@Component({
    selector: 'app-post-comments',
    templateUrl: './post-comments.component.html',
    styleUrls: ['./post-comments.component.css']
})
export class PostCommentsComponent {
    @Input() post!: IPost; 
    @Input() user!: IUser; 
    @Input() isOpenComments!: boolean; 
    @Output() closeComments = new EventEmitter(); 

    comentarios: any = []; 

    constructor(private commentsService: CommentsService) {};

    ngOnInit() {
        this.getComments();
    }

    close() {
        this.closeComments.emit();
    }

    postComment(form: NgForm) {
        const { comentario } = form.value; 

        const nuevoComentario = { 
            post: {
                id: this.post.id
            }, 
            usuario: {
                id: this.user.id, 
                username: this.user.username
            },
            comentario
        }

        this.comentarios.push(nuevoComentario);
        this.commentsService.toComment(nuevoComentario).subscribe(data => {
            this.post.totalComentarios++;
            form.reset();
        })
    }

    getComments() {
        this.commentsService.getCommentsByPost(this.post.id).subscribe(data => {
            this.comentarios = data; 
        })
    }
}
