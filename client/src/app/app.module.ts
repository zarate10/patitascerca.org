import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; 
import { NgOptimizedImage } from '@angular/common';
import { FormsModule }   from '@angular/forms';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthComponent } from './views/auth/page/auth.component';
import { WrapperAppComponent } from './shared/components/wrapper-app/wrapper-app.component';
import { ToastrModule } from 'ngx-toastr';
import { HomeComponent } from './views/home/page/home.component';
import { BoxImgProfileComponent } from './shared/components/box-img-profile/box-img-profile.component';
import { PostComponent } from './views/home/components/post/post.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    WrapperAppComponent,
    HomeComponent,
    BoxImgProfileComponent,
    PostComponent,
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
