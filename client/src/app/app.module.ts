import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; 
import { NgOptimizedImage } from '@angular/common';
import { FormsModule }   from '@angular/forms';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthComponent } from './views/auth/auth.component';
import { WrapperAppComponent } from './components/wrapper-app/wrapper-app.component';
import { ToastrModule } from 'ngx-toastr';
import { HomeComponent } from './views/home/home.component';
import { BoxImgProfileComponent } from './components/box-img-profile/box-img-profile.component';
import { PostComponent } from './components/post/post.component';
import { ButtonFilterComponent } from './components/button-filter/button-filter.component';
import { PostCommentsComponent } from './components/post-comments/post-comments.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    WrapperAppComponent,
    HomeComponent,
    BoxImgProfileComponent,
    PostComponent,
    ButtonFilterComponent,
    PostCommentsComponent,
  ],
  imports: [
    BrowserModule, 
    BrowserAnimationsModule,
    HttpClientModule,
    NgOptimizedImage,
    AppRoutingModule,
    FormsModule,
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
