import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './components/welcome/welcome.component';
import { AddPersonComponent } from './components/add-person/add-person.component';
import { PersonTableComponent } from './components/person-table/person-table.component';

const routes: Routes = [  
{ path: '', component: WelcomeComponent }, 
{ path: 'add-person', component: AddPersonComponent },
{ path: 'show-persons', component: PersonTableComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
