import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; 
import { NgOptimizedImage } from '@angular/common';

import { AppComponent } from './app.component';
import { LoginComponent } from './views/login/login.component';
import { PostDetailComponent } from './views/post-detail/post-detail.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PostDetailComponent
  ],
  imports: [
    BrowserModule, 
    HttpClientModule,
    NgOptimizedImage,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
