import { Component } from '@angular/core';
import { Person } from './model/person';
import { Form, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  person!: Person;
  addPersonForm: any;
  constructor(private formBuilder: FormBuilder){

    this.addPersonForm = this.formBuilder.group({
      id: ['',[Validators.required, Validators.minLength(5)]],
      name: ['',[Validators.required, Validators.minLength(2)]],
      birthday:['',[Validators.required, Validators.minLength(5)]],
    })

  }

}
