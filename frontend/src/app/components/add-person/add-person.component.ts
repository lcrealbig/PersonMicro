import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Person } from 'src/app/model/person';
import { PersonService } from 'src/app/service/person.service';
import { PersonValidationService } from 'src/app/validation/person-validation.service';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent {
  onInput(): void {
  }
onSubmit() {
throw new Error('Method not implemented.');
}
  title = 'frontend';
  person!: Person;
  addPersonForm: any;
  constructor(private formBuilder: FormBuilder, private personService: PersonService, private personValidation: PersonValidationService){

    this.addPersonForm = this.formBuilder.group({
      id: ['',[Validators.required, Validators.minLength(5)]],
      name: ['',[Validators.required, Validators.minLength(2)]],
      birthday:['',[Validators.required, Validators.minLength(5)]],
    })
  }
  isIdPatternCorrect(): boolean{
    const id = this.addPersonForm.get('id').value;
    console.log('id ', id);
    return this.personValidation.isIdPatternCorrect(id);
  
  }

  isIdLengthValid(): boolean{
    const id = this.addPersonForm.get('id').value;
    return this.personValidation.isIdLengthValid(id);
  }

  hiddenValidationMessage(): boolean {
    const id = this.addPersonForm.get('id').value;
    if (id.length === 0) {
      return true;
    }
    return id.length === 5 || /^[A-Za-z]\d{4}$/.test(id);
  }
}


