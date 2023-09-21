import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IPost } from 'src/app/interfaces/IPost';
import { PostService } from 'src/app/services/post.service';

@Component({
    selector: 'app-post-detail',
    templateUrl: './post-detail.component.html',
    styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent {
    id!: number;
    post!: IPost; 

    constructor(private route: ActivatedRoute, private postService: PostService) {
        this.route.params.subscribe( params => this.id = params['id'] );
    }

    ngOnInit() {
        this.postService.getPostById(this.id).subscribe(data => {
            this.post = data; 
        });  
    }
}
